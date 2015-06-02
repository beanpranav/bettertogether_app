package com.iwl.bettertogforever;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class WishlistTabFragment extends Fragment implements OnClickListener{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wishlist_tab_fragment, container, false);
        Button addWishlistButton = (Button)v.findViewById(R.id.addNewList);
        addWishlistButton.setOnClickListener(this);
        return v;
    }
    
    public void addNewListClicked(View view) {
    	Intent pendingAcceptanceIntent = new Intent(view.getContext(), AddNewListActivity.class);
		startActivity(pendingAcceptanceIntent);
    }

	@Override
	public void onClick(View v) {
		addNewListClicked(v);
	}
}
