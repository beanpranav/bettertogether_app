package com.iwl.bettertogforever;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.iwl.bettertogforever.adapters.WishListItemsActivityAdapter;
import com.iwl.bettertogforever.adapters.WishListTabFragmentAdapter;
import com.iwl.bettertogforever.connections.utils.BetterTogForeverHttpConnectUtils;
import com.iwl.bettertogforever.constants.StatusConstants;
import com.iwl.bettertogforever.model.UserIdCoupleIdPair;
import com.iwl.bettertogforever.model.WishList;
import com.iwl.bettertogforever.model.response.FullList;
import com.iwl.bettertogforever.model.response.WishListItem;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class WishlistItemsActivity extends ActivityImpl implements OnItemClickListener, OnItemLongClickListener{

	Integer id;
	WishListItemsActivityAdapter adapter; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wishlist_items);
		
		Intent intent = getIntent();
		String listDesc = intent.getStringExtra("listName");
		
		List<WishListItem> wishListItems = getAllWishListItems(listDesc);
        adapter = new WishListItemsActivityAdapter(this, wishListItems);
        ListView wishListView = (ListView)findViewById(R.id.wishListItemsListView);
        wishListView.setAdapter(adapter);
        wishListView.setOnItemClickListener(this);
        wishListView.setOnItemLongClickListener(this);
	}
	
    public List<WishListItem> getAllWishListItems(String desc){
    	BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		Integer id = dbDao.getIdFromDesc(desc);
		this.id = id;
		List<WishListItem> wishListItems = dbDao.getWishListItems(id);
		dbDao.close();
		return wishListItems;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wishlist_items, menu);
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
	
    public void addNewListItemClicked(View view) {

    	Intent addWishListItemActivity = new Intent(view.getContext(), AddWishListItemActivity.class);
    	addWishListItemActivity.putExtra("listId", id);
		startActivity(addWishListItemActivity);
    }
    
    public void delListItemsClicked(View view) {
    	Intent delWishListItemActivity = new Intent(view.getContext(), DeleteListItemsActivity.class);
    	delWishListItemActivity.putExtra("listId", id);
		startActivity(delWishListItemActivity);
    }

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		WishListItem itemClicked = (WishListItem)adapter.getItem(position);
		
		Integer itemId = itemClicked.getItemId();
		String status = itemClicked.getItemStatus();
		String name = itemClicked.getItemName();
		String desc = itemClicked.getItemDescription();
		
		Intent editWishListItemActivity = new Intent(view.getContext(), EditWishlistItemActivity.class);
		editWishListItemActivity.putExtra("listId", id);
		editWishListItemActivity.putExtra("itemId", itemId);
		editWishListItemActivity.putExtra("status", status);
		editWishListItemActivity.putExtra("name", name);
		editWishListItemActivity.putExtra("desc", desc);
		startActivity(editWishListItemActivity);
		
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		WishListItem itemClicked = (WishListItem)adapter.getItem(position);
		Integer itemId = itemClicked.getItemId();
		String status = itemClicked.getItemStatus();
		
		int itemIdCopy = itemId;
        String statusCopy = new String(status);
        String listNameCopy = new String(itemClicked.getItemName());
        String descCopy = new String(itemClicked.getItemDescription());
        
		if(status.equals(StatusConstants.PENDING_STATUS)){
			changeItemStatus(itemId, StatusConstants.COMPLETED_STATUS);
			itemClicked.setItemStatus(StatusConstants.COMPLETED_STATUS);
			statusCopy = StatusConstants.COMPLETED_STATUS;
		} else{
			changeItemStatus(itemId, StatusConstants.PENDING_STATUS);
			itemClicked.setItemStatus(StatusConstants.PENDING_STATUS);
			statusCopy = StatusConstants.PENDING_STATUS;
		}
		updateStatusToService(itemIdCopy, statusCopy, listNameCopy, descCopy );
		adapter.notifyDataSetChanged();
	}
	
    public void changeItemStatus(Integer itemId, String status){
    	BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		dbDao.toggleItemStatus(id, itemId, status);
		dbDao.close();
    }
    
    @SuppressWarnings("unchecked")
	public void updateStatusToService(final Integer itemIdCopy, final String statusCopy, final String listNameCopy, final String descCopy) {
		new AsyncTask() {
        	
        	@Override
            protected String doInBackground(Object... params) {
                String msg = "";
                
				try {
					boolean updateStatus = new BetterTogForeverHttpConnectUtils().editListItem(itemIdCopy, id, listNameCopy, descCopy, statusCopy);
					if(!updateStatus){
						if(statusCopy.equals(StatusConstants.PENDING_STATUS)){
							changeItemStatus(itemIdCopy, StatusConstants.COMPLETED_STATUS);
						} else{
							changeItemStatus(itemIdCopy, StatusConstants.PENDING_STATUS);
						}
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

}
