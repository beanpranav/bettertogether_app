package com.iwl.bettertogforever.adapters;

import java.util.ArrayList;
import java.util.List;

import com.iwl.bettertogforever.R;
import com.iwl.bettertogforever.model.WishList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WishListTabFragmentAdapter extends BaseAdapter {

	View v;
	List<WishList> wishLists;
	
	public WishListTabFragmentAdapter(View v, List<WishList> wishlists){
		this.v = v;
		if(wishlists == null){
			this.wishLists = new ArrayList<WishList>();
		} else {
			this.wishLists = wishlists;
		}
		
	}
	
	@Override
	public int getCount() {
		return wishLists.size();
	}

	@Override
	public Object getItem(int position) {
		return wishLists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null)
	    {
	        LayoutInflater inflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        convertView = inflater.inflate(R.layout.wishlist_tab_fragment_row, parent,false);
	    }
		
		TextView chapterName = (TextView)convertView.findViewById(R.id.textView1);

        WishList chapter = wishLists.get(position);

        chapterName.setText(chapter.getDescription().toString());

        return convertView;
	}
}
