package com.iwl.bettertogforever;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SecretMessageActivity extends FragmentActivity {

	 private FragmentTabHost mTabHost;

	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secret_message);
        
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Tab 1", null),
                SecretMessageTabFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Tab 2", null),
                WishlistTabFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Tab 3", null),
                SettingsTabFragment.class, null);
		/*
		 * setting up the tab host
		 */
//		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
//		tabHost.setup();
//
//		
//
//
//		TabSpec spec = tabHost.newTabSpec("Tab one");
//		spec.setContent(R.id.tab1);
//		spec.setIndicator("Tab One");
//		tabHost.addTab(spec);
//
//		spec = tabHost.newTabSpec("Tab Two");
//		spec.setContent(R.id.tab2);
//		spec.setIndicator("Tab Two");
//		tabHost.addTab(spec);
//		
//		spec = tabHost.newTabSpec("Tab three");
//		spec.setContent(R.id.tab3);
//		spec.setIndicator("Tab three");
//		tabHost.addTab(spec);
		
		
		
//		TextView secretMessage = (TextView)findViewById(R.id.secretMsg);
//		String secretMsg = getSecretMsg();
//		
//		if(secretMsg == null){
//			UserIdCoupleIdPair userIdCoupleId = getUserIdCoupleId(); 
//			secretMsg = new BetterTogForeverHttpConnectUtils().getSecretMessage(userIdCoupleId.getUserId(), userIdCoupleId.getCoupleId());
//			updateSecretMsgTodb(secretMsg);
//			if(secretMsg == null || secretMsg.equals("")){
//				secretMsg = "No secret message for you yet";
//			}
//		}
//		secretMessage.setText(secretMsg);
	}
	
	public void addSecretMsgClicked(View view) {
//		EditText secretMessage = (EditText)findViewById(R.id.secretMsgAddText);
//		String msgToUpdate = secretMessage.getText().toString();
//		UserIdCoupleIdPair userIdCoupleId = getUserIdCoupleId();
//		new BetterTogForeverHttpConnectUtils().addSecretMessage(userIdCoupleId.getUserId(), userIdCoupleId.getCoupleId(), msgToUpdate);
//		Toast.makeText(this, "Sent secret message to your loved one", Toast.LENGTH_LONG);
	}
	
//	private UserIdCoupleIdPair getUserIdCoupleId() {
//		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
//		dbDao.open();
//		UserIdCoupleIdPair userCoupleIdDetail = dbDao.getUserIdCoupleIdPair();
//		dbDao.close();
//		return userCoupleIdDetail;
//	}
//	
//	private void updateSecretMsgTodb(String msg) {
//		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
//		dbDao.open();
//		dbDao.insertSecretMsg(msg);
//		dbDao.close();
//	}
//
//	private String getSecretMsg() {
//		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
//		dbDao.open();
//		String secretMsg = dbDao.getSecretMsg();
//		dbDao.close();
//		return secretMsg;
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.secret_message, menu);
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
