package com.iwl.bettertogforever;

import java.io.IOException;
import java.sql.SQLException;

import com.iwl.bettertogforever.connections.utils.BetterTogForeverHttpConnectUtils;
import com.iwl.bettertogforever.constants.StatusConstants;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EditWishlistItemActivity extends ActivityImpl {

	Integer listId;
	Integer itemId;
	String status;
	LinearLayout mainLayout;
	LinearLayout progresLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_wishlist_item);
		
		Intent intent = getIntent();
		listId = intent.getIntExtra("listId", 0);
		
		itemId = intent.getIntExtra("itemId", 0);
		String name = intent.getStringExtra("name");
		String desc = intent.getStringExtra("desc");
		status = intent.getStringExtra("status");
		
		TextView listName = (TextView) findViewById(R.id.listName);
		EditText itemNameComponent = (EditText) findViewById(R.id.itemName);
		EditText itemDescComponent = (EditText) findViewById(R.id.itemDescription);
		listName.setText(getListName(listId));
		itemNameComponent.setText(name);
		itemDescComponent.setText(desc);
		
		mainLayout = (LinearLayout) findViewById(R.id.listLayout);
		progresLayout = (LinearLayout) findViewById(R.id.linlaHeaderProgress);
	}
	
	public void updateItemClicked(View view) {
		
		EditText itemNameComponent = (EditText) findViewById(R.id.itemName);
		EditText itemDescComponent = (EditText) findViewById(R.id.itemDescription);
		
		String itemName = itemNameComponent.getText().toString();
		String itemDesc = itemDescComponent.getText().toString();
		
		updateItemInBackend(this, listId, itemId, status, itemName, itemDesc);
	}
	
    @SuppressWarnings("unchecked")
	public void updateItemInBackend(final Activity act, final Integer listId, final Integer itemIdCopy, final String statusCopy, final String listNameCopy, final String descCopy) {
		new AsyncTask<Object, Object, String>() {
			@Override
		    protected void onPreExecute() {
				progresLayout.setVisibility(View.VISIBLE);
		        mainLayout.setVisibility(View.GONE);
		        super.onPreExecute();
		    }

		    @Override
		    protected void onPostExecute(String result) {
		        progresLayout.setVisibility(View.GONE);
		        mainLayout.setVisibility(View.VISIBLE);

				Intent wishListItemsActivity = new Intent(act, WishlistItemsActivity.class);
				wishListItemsActivity.putExtra("listName", getListName(listId));
				startActivity(wishListItemsActivity);
		        super.onPostExecute(result);
		    }
			
			
        	@Override
            protected String doInBackground(Object... params) {
                String msg = "";
                
				try {
					boolean updateStatus = new BetterTogForeverHttpConnectUtils().editListItem(itemIdCopy, listId, listNameCopy, descCopy, statusCopy);
					if(updateStatus){
						updateListItemLocalDb(itemIdCopy, listId, listNameCopy, descCopy, statusCopy);
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


	private void updateListItemLocalDb(Integer itemIdCopy,
			Integer listId, String listNameCopy, String descCopy,
			String statusCopy) {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		dbDao.updateListItem(itemIdCopy, listId, listNameCopy, descCopy, statusCopy);
		dbDao.close();
	}
	
    private String getListName(Integer listId) {
    	BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		String listName = dbDao.getWishListName(listId);
		dbDao.close();
	    return listName;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_wishlist_item, menu);
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
