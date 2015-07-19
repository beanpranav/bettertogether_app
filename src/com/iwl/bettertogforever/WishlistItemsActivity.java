package com.iwl.bettertogforever;

import java.util.List;

import com.iwl.bettertogforever.adapters.WishListItemsActivityAdapter;
import com.iwl.bettertogforever.adapters.WishListTabFragmentAdapter;
import com.iwl.bettertogforever.model.WishList;
import com.iwl.bettertogforever.model.response.WishListItem;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

public class WishlistItemsActivity extends ActivityImpl {

	Integer id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wishlist_items);
		
		Intent intent = getIntent();
		String listDesc = intent.getStringExtra("listName");
		
		List<WishListItem> wishListItems = getAllWishListItems(listDesc);
        WishListItemsActivityAdapter adapter = new WishListItemsActivityAdapter(this, wishListItems);
        ListView wishListView = (ListView)findViewById(R.id.wishListItemsListView);
        wishListView.setAdapter(adapter);
       // wishListView.setOnItemClickListener(this);
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
}
