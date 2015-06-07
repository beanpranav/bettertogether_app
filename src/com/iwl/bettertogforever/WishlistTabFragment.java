package com.iwl.bettertogforever;

import java.util.List;

import com.iwl.bettertogforever.adapters.WishListTabFragmentAdapter;
import com.iwl.bettertogforever.model.WishList;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

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
        List<WishList> wishLists = getAllWishLists(v);
        WishListTabFragmentAdapter adapter = new WishListTabFragmentAdapter(v, wishLists);
        ListView wishListView = (ListView)v.findViewById(R.id.allWishListsListView);
        wishListView.setAdapter(adapter);
        return v;
    }
    
    public void addNewListClicked(View view) {
    	Intent pendingAcceptanceIntent = new Intent(view.getContext(), AddNewListActivity.class);
		startActivity(pendingAcceptanceIntent);
    }
    
    public List<WishList> getAllWishLists(View view){
    	BetterTogForeverSqlliteDao dbDao = new ActivityImpl().getDataSourceFromContext(view.getContext());
		dbDao.open();
		List<WishList> wishLists = dbDao.getAllWishList();
		dbDao.close();
		return wishLists;
    }

	@Override
	public void onClick(View v) {
		addNewListClicked(v);
	}
}
