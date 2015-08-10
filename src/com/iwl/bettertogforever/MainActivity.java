package com.iwl.bettertogforever;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.iwl.bettertogforever.gcm.GCMRegistrationHelper;
import com.iwl.bettertogforever.connections.utils.BetterTogForeverHttpConnectUtils;
import com.iwl.bettertogforever.constants.AddSpouseRequestStatusConstants;
import com.iwl.bettertogforever.model.UserIdCoupleIdPair;
import com.iwl.bettertogforever.model.WishList;
import com.iwl.bettertogforever.model.response.AuthUserIdStatus;
import com.iwl.bettertogforever.model.response.FullList;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActivityImpl {

	Context context;
	GoogleCloudMessaging gcm;
    String regid;
    Integer userId;
    GCMRegistrationHelper helper;
    BetterTogForeverHttpConnectUtils httpConnect = new BetterTogForeverHttpConnectUtils();
    
    /**
     * Substitute you own sender ID here. This is the project number you got
     * from the API Console, as described in "Getting Started."
     */
    String SENDER_ID = "150325667771";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    
		if(isOnline()){
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy); 
	        
	        context = getApplicationContext();
	        
	        UserIdCoupleIdPair userIdCoupleId = getUserIdCoupleId(); 
	        
	        //Its a signed in user but might not have added a couple
	        if(userIdCoupleId != null){
	    		this.userId = userIdCoupleId.getUserId();
	    		doGCMRegistraction();
	    		
	    		String hasReceivedCoupleAddRequest = hasCoupleAddedRequest();
	    		Integer addSpouseRequestStatus = getAddSpouseRequestStatus();
	    		
	    		if(addSpouseRequestStatus == AddSpouseRequestStatusConstants.PENDING_ACCEPTANCE){
	    			Intent pendingAcceptanceIntent = new Intent(this, PendingAcceptanceActivity.class);
	    			startActivity(pendingAcceptanceIntent);
	    		} else if(addSpouseRequestStatus == AddSpouseRequestStatusConstants.ACCEPTED){
	    			getCoupleListsInBackground();
	    			Intent letsBeginIntent = new Intent(this, LetsBeginActivity.class);
	    			startActivity(letsBeginIntent);
	    		} else
	    		//If the user has received adding couple notification earlier then we show the accept couple view.
	    		//Once accepted or declined the hasReceivedCoupleAddRequest will be wiped out. This happens when 
	    		//someone else is trying to add you as a spouse
	    		if(hasReceivedCoupleAddRequest != null){
	    			Intent acceptAddedCoupleIntent = new Intent(this, AcceptAddedCoupleActivity.class);
	    			acceptAddedCoupleIntent.putExtra("coupleEmail", hasReceivedCoupleAddRequest);
	    			acceptAddedCoupleIntent.putExtra("coupleId", userIdCoupleId.getCoupleId());
					startActivity(acceptAddedCoupleIntent);
	    		} 
	        	//user hasnt added the couple
	    		else if(userIdCoupleId.getCoupleId() == null || userIdCoupleId.getCoupleId() == 0){
	        		Intent addCoupleIntent = new Intent(this, AddCoupleActivity.class);
	
					addCoupleIntent.putExtra("userId", userIdCoupleId.getUserId());
					startActivity(addCoupleIntent);
	        	} else {
	        		getCoupleListsInBackground();
	        		//The user has already created a couple if we reach this point
	        		Intent secretMessageIntent = new Intent(this, SecretMessageActivity.class);
					startActivity(secretMessageIntent);
	        	}
	        }
		} else{
			Toast.makeText(context, "check your internet connection. The app doesnt work without a net connection", Toast.LENGTH_LONG).show();
		}
	}
	
	
    private Integer getAddSpouseRequestStatus() {
    	BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		Integer addSpouseStatus = dbDao.getAddSpouseRequestStatus();
		dbDao.close();
	    return addSpouseStatus;
	}


	private String hasCoupleAddedRequest() {
    	BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		String coupleTryingToAddEmail = dbDao.getReceivedAddRequestId();
		dbDao.close();
	    return coupleTryingToAddEmail;
	}


	private void doGCMRegistraction() {
        GCMRegistrationHelper helper = new GCMRegistrationHelper();
        // Check device for Play Services APK.
        if (helper.checkPlayServices(this)) {
        	gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId();

            if (regid == null || regid.isEmpty()) {
                registerInBackground();
            }
        }
	}
    
    
	
	/**
	 * Gets the current registration ID for application on GCM service.
	 * <p>
	 * If result is empty, the app needs to register.
	 *
	 * @return registration ID, or empty string if there is no existing
	 *         registration ID.
	 */
	public String getRegistrationId() {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		String regId = dbDao.getRegId();
		dbDao.close();
	    return regId;
	}

	/**
     * Registers the application with GCM servers asynchronously.
     * <p>
     * Stores the registration ID and app versionCode in the application's
     * shared preferences.
     */
	@SuppressWarnings("unchecked")
	private void registerInBackground() {
        new AsyncTask() {
        	
        	@Override
            protected String doInBackground(Object... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }

                    for(int i=0; i<10; i++){
                    	try{
                    		regid = gcm.register(SENDER_ID);
                    		if(regid != null){
                    			break;
                    		}
                    	}catch(IOException e){
                    		Thread.sleep(i * 2000);
                    	}
                    }
                    
                    msg = "Device registered, registration ID=" + regid;

                    // You should send the registration ID to your server over HTTP,
                    // so it can use GCM/HTTP or CCS to send messages to your app.
                    // The request to your server should be authenticated if your app
                    // is using accounts.
                    boolean saveRegIdStatus = sendRegistrationIdToBackend(regid);

                    // For this demo: we don't need to send it because the device
                    // will send upstream messages to a server that echo back the
                    // message using the 'from' address in the message.

                    // Persist the regID - no need to register again.
                    if(saveRegIdStatus){
                    	storeRegistrationId(regid);
                    }
                } catch (Exception ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }
        }.execute(null, null, null);
    }
	
		@SuppressWarnings("unchecked")
		public void getCoupleListsInBackground() {
			new AsyncTask() {
	        	
	        	@Override
	            protected String doInBackground(Object... params) {
	                String msg = "";
	                
	                	UserIdCoupleIdPair usrCpl = getUserIdCoupleId();
	                    List<WishList> allWishLists = null;
						try {
							allWishLists = new BetterTogForeverHttpConnectUtils().getCoupleLists(usrCpl.getCoupleId());
							updateAllWishLists(allWishLists);
		                    
		                    for(WishList list: allWishLists){
		                    	FullList itemList = new BetterTogForeverHttpConnectUtils().getFullList(usrCpl.getCoupleId(), list.getId());
		                    	updateListItems(itemList);
		                    }
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                
	                return msg;
	            }
	        }.execute(null, null, null);
		}
 

		protected void updateListItems(FullList itemList) {
			BetterTogForeverSqlliteDao dbDao = this.getDataSource();
			dbDao.open();
			dbDao.updateWishListItems(itemList.getListId(), itemList.getWishListItems());
			dbDao.close();
		}


		private void updateAllWishLists(List<WishList> allWishLists) {
			BetterTogForeverSqlliteDao dbDao = this.getDataSource();
			dbDao.open();
			dbDao.updateWishLists(allWishLists);
			dbDao.close();
		}
	
	/**
	 * Stores the registration ID and app versionCode in the application's
	 * {@code SharedPreferences}.
	 *
	 * @param context application's context.
	 * @param regId registration ID
	 */
	public void storeRegistrationId(String regId) {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		long regIdInsertStatus = dbDao.insertRegId(regId);
		dbDao.close();
	}
    
    /**
     * Sends the registration ID to your server over HTTP, so it can use GCM/HTTP
     * or CCS to send messages to your app. Not needed for this demo since the
     * device sends upstream messages to a server that echoes back the message
     * using the 'from' address in the message.
     */
    private boolean sendRegistrationIdToBackend(String regId) {
    	TelephonyManager tMgr = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
    	String mPhoneNumber = tMgr.getLine1Number();
    	return httpConnect.addRegid(userId, regId, mPhoneNumber);
    }
	
public void signInButtonClicked(View view) {
		
		if(isOnline()){
			EditText email = (EditText)findViewById(R.id.email);
			EditText passwd   = (EditText)findViewById(R.id.passwd);
			
			AuthUserIdStatus isAuthenticatedUserStatus = httpConnect.authenticateUser(email.getText().toString(), passwd.getText().toString());
			
			if(isAuthenticatedUserStatus.isAuthorized()){
				if(isAuthenticatedUserStatus.getCplId() != null && isAuthenticatedUserStatus.getCplStatus() == AddSpouseRequestStatusConstants.PENDING_ACCEPTANCE){
					insertUserIdToDb(isAuthenticatedUserStatus.getUserId());
					addCoupleIdToDb(isAuthenticatedUserStatus.getCplId());
					insertUsrNameCplName(isAuthenticatedUserStatus.getUserName(), isAuthenticatedUserStatus.getSpouseName());
					this.userId = isAuthenticatedUserStatus.getUserId();
					doGCMRegistraction();
					Intent acceptAddedCouple = new Intent(context, AcceptAddedCoupleActivity.class);
					acceptAddedCouple.putExtra("coupleEmail", isAuthenticatedUserStatus.getSpouseEmail());
					acceptAddedCouple.putExtra("coupleId", isAuthenticatedUserStatus.getCplId());
					startActivity(acceptAddedCouple);
				} else if(isAuthenticatedUserStatus.getCplId() != null && isAuthenticatedUserStatus.getCplStatus() == AddSpouseRequestStatusConstants.ACCEPTED){
					insertUserIdToDb(isAuthenticatedUserStatus.getUserId());
					addCoupleIdToDb(isAuthenticatedUserStatus.getCplId());
					insertUsrNameCplName(isAuthenticatedUserStatus.getUserName(), isAuthenticatedUserStatus.getSpouseName());
					this.userId = isAuthenticatedUserStatus.getUserId();
					doGCMRegistraction();
					Intent letsBeginIntent = new Intent(context, LetsBeginActivity.class);
					startActivity(letsBeginIntent);
				} else {
					Integer currentUserId = isAuthenticatedUserStatus.getUserId();
					Long userIdInsertStatus = insertUserIdToDb(currentUserId);
					this.userId = currentUserId;
					doGCMRegistraction();
					Intent addCoupleIntent = new Intent(this, AddCoupleActivity.class);
					addCoupleIntent.putExtra("userId", currentUserId);
					startActivity(addCoupleIntent);
				}
			}else {
				Toast.makeText(context, "Could not login. Please check username and passwd", Toast.LENGTH_LONG).show();
			}
		} else {
			//redirect to error page
		}
	 }

	private void insertUsrNameCplName(String usrName, String cplName) {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		dbDao.insertCplNameUsrName(usrName, cplName);
		dbDao.close();
	}

	private void addCoupleIdToDb(Integer cplId) {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		dbDao.insertCoupleId(cplId);
		dbDao.close();
	}
	
	public void signUpButtonClicked(View view) {
		Intent iwlIntent = new Intent("android.intent.action.VIEW",
		Uri.parse("http://www.iwilllearn.org/login"));
		startActivity(iwlIntent);
	}
	
	private UserIdCoupleIdPair getUserIdCoupleId() {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		UserIdCoupleIdPair userCoupleIdDetail = dbDao.getUserIdCoupleIdPair();
		dbDao.close();
		return userCoupleIdDetail;
	}

	public void signOutButtonClicked(View view){
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		dbDao.deleteUserSignedIn();
		dbDao.close();
	}
	
	private Long insertUserIdToDb(Integer currentUserId) {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		Long insertStatus = dbDao.insertUserId(currentUserId);
		dbDao.close();
		return insertStatus;
	}

	/*
	 * check if device is connected to internet
	 */
	public boolean isOnline() {
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    return netInfo != null && netInfo.isConnectedOrConnecting();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
