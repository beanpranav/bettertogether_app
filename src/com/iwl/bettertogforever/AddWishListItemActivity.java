package com.iwl.bettertogforever;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.iwl.bettertogforever.connections.utils.BetterTogForeverHttpConnectUtils;
import com.iwl.bettertogforever.model.UserIdCoupleIdPair;
import com.iwl.bettertogforever.model.response.FullList;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class AddWishListItemActivity extends ActivityImpl {

	Map<String, String> itemsToAdd = new HashMap<String, String>();
	Integer listId;
	LinearLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_wish_list_item);
		Intent intent = getIntent();
		listId = intent.getIntExtra("listId", 0);
		//listView = (ListView) findViewById(R.id.listView);
		//layout = (LinearLayout) findViewById(R.id.progressBarView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_wish_list_item, menu);
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
	
    public void itemAddMoreClicked(View view) {
    	EditText itemName = (EditText)this.findViewById(R.id.itemName);
    	EditText itemDescription = (EditText)this.findViewById(R.id.itemDescription);
    	
    	itemsToAdd.put(itemName.getText().toString(), itemDescription.getText().toString());
    	itemName.setText(""); itemDescription.setText("");
    	TableLayout table = (TableLayout)this.findViewById(R.id.itemsToAddList);
    	for(String b : itemsToAdd.keySet())
    	{
    	    // Inflate your row "template" and fill out the fields.
    	    TableRow row = (TableRow)LayoutInflater.from(this).inflate(R.layout.wishlist_add_item_row, null);
    	    ((TextView)row.findViewById(R.id.attrib_name)).setText(b);
    	    ((TextView)row.findViewById(R.id.attrib_value)).setText(itemsToAdd.get(b));
    	    table.addView(row);
    	}
    	table.requestLayout(); 
    }
    
    public void doneAddingClicked(View view) {
    	updateNewItemsToBackend();
    	Intent secretMessageIntent = new Intent(this, SecretMessageActivity.class);
		startActivity(secretMessageIntent); 
    }
    
    @SuppressWarnings("unchecked")
	private void updateNewItemsToBackend() {
        new AsyncTask<Object, Integer, Boolean>() {
        	
        	 @Override
        	    protected void onPreExecute() {
        	       // layout.setVisibility(View.VISIBLE);
        	        //listView.setVisibility(View.GONE);
        	        super.onPreExecute();
        	    }

        	    @Override
        	    protected void onPostExecute(Boolean result) {
        	      //  layout.setVisibility(View.GONE);
        	       // listView.setVisibility(View.VISIBLE);
        	       // adapter.notifyDataSetChanged();
        	        super.onPostExecute(result);
        	    }
        	
        	@Override
            protected Boolean doInBackground(Object... params) {
                try {
                	UserIdCoupleIdPair cplId = getUserIdCoupleId();
						new BetterTogForeverHttpConnectUtils().addToList(itemsToAdd, cplId.getCoupleId(), listId);
						FullList itemList = new BetterTogForeverHttpConnectUtils().getFullList(cplId.getCoupleId(), listId);
                    	updateListItems(itemList);

                }catch(Exception e){
                	e.printStackTrace();
                }
				return null;
        	}
        }.execute(null, null, null);
    }
    
	protected void updateListItems(FullList itemList) {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		dbDao.updateWishListItems(itemList.getListId(), itemList.getWishListItems());
		dbDao.close();
	}
    
	private UserIdCoupleIdPair getUserIdCoupleId() {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		UserIdCoupleIdPair userCoupleIdDetail = dbDao.getUserIdCoupleIdPair();
		dbDao.close();
		return userCoupleIdDetail;
	}
}
