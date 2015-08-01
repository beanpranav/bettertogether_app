package com.iwl.bettertogforever;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iwl.bettertogforever.adapters.WishListItemsActivityAdapter;
import com.iwl.bettertogforever.connections.utils.BetterTogForeverHttpConnectUtils;
import com.iwl.bettertogforever.model.UserIdCoupleIdPair;
import com.iwl.bettertogforever.model.WishList;
import com.iwl.bettertogforever.model.response.FullList;
import com.iwl.bettertogforever.model.response.WishListItem;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DeleteListItemsActivity extends ActivityImpl {

	Integer listId;
	ListView wishListView;
	WishListItemsActivityAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_list_items);
		Intent intent = getIntent();
		listId = intent.getIntExtra("listId", 0);
		
		List<WishListItem> wishListItems = getAllWishListItems(listId);
        adapter = new WishListItemsActivityAdapter(this, wishListItems);
        wishListView = (ListView)findViewById(R.id.wishListDeleteItemsListView);
        wishListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        wishListView.setAdapter(adapter);
	}
	
	public void doneSelectingDeleteItemsButtonClicked(View v) {
		SparseBooleanArray checked = wishListView.getCheckedItemPositions();
        ArrayList<WishListItem> selectedItems = new ArrayList<WishListItem>();
        for (int i = 0; i < checked.size(); i++) {
            // Item position in adapter
            int position = checked.keyAt(i);
            // Add sport if it is checked i.e.) == TRUE!
            if (checked.valueAt(i))
                selectedItems.add((WishListItem) adapter.getItem(position));
        }
        doDeleteItems(selectedItems);
	}
	
    @SuppressWarnings("unchecked")
	private void doDeleteItems(ArrayList<WishListItem> selectedItems) {
    	final List<Integer> itemsToDelIds = new ArrayList<Integer>();
    	for(WishListItem itm: selectedItems){
    		itemsToDelIds.add(itm.getItemId());
    		delListItem(listId, itm.getItemId());
    	}
    	final UserIdCoupleIdPair cplId = getUserIdCoupleId();

			new AsyncTask() {
	        	
	        	@Override
	            protected String doInBackground(Object... params) {
	        		try {
						new BetterTogForeverHttpConnectUtils().removeFromList(itemsToDelIds, cplId.getCoupleId(), listId);
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
	                return null;
	            }
	        }.execute(null, null, null);
	}
    
	private UserIdCoupleIdPair getUserIdCoupleId() {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		UserIdCoupleIdPair userCoupleIdDetail = dbDao.getUserIdCoupleIdPair();
		dbDao.close();
		return userCoupleIdDetail;
	}
	
	private void delListItem(Integer itemId, Integer listId) {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		dbDao.deleteOldWishlistItem(listId, itemId);
		dbDao.close();
	}

	public List<WishListItem> getAllWishListItems(Integer id){
    	BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		List<WishListItem> wishListItems = dbDao.getWishListItems(id);
		dbDao.close();
		return wishListItems;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.delete_list_items, menu);
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
