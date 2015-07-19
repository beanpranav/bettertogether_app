package com.iwl.bettertogforever.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.iwl.bettertogforever.R;
import com.iwl.bettertogforever.model.WishList;
import com.iwl.bettertogforever.model.response.WishListItem;

public class WishListItemsActivityAdapter extends BaseAdapter{
	Context v;
	List<WishListItem> wishListItems;
	
	public WishListItemsActivityAdapter(Context t, List<WishListItem> wishlistItems){
		this.v = t;
		if(wishlistItems == null){
			this.wishListItems = new ArrayList<WishListItem>();
		} else {
			this.wishListItems = wishlistItems;
		}
		
	}
	
	@Override
	public int getCount() {
		return wishListItems.size();
	}

	@Override
	public Object getItem(int position) {
		return wishListItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null)
	    {
	        LayoutInflater inflater = (LayoutInflater) v.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.wishlist_item_row, parent,false);
	    }
		
		TextView itemName = (TextView)convertView.findViewById(R.id.itemName);
		TextView itemDescription = (TextView)convertView.findViewById(R.id.itemDescription);
		TextView status = (TextView)convertView.findViewById(R.id.status);

		WishListItem item = wishListItems.get(position);

		itemName.setText(item.getItemName());
		itemDescription.setText(item.getItemDescription());
		status.setText(item.getItemStatus());

        return convertView;
	}
}
