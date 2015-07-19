package com.iwl.bettertogforever;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.iwl.bettertogforever.adapters.WishListTabFragmentAdapter;
import com.iwl.bettertogforever.connections.utils.BetterTogForeverHttpConnectUtils;
import com.iwl.bettertogforever.model.UserIdCoupleIdPair;
import com.iwl.bettertogforever.model.WishList;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class WishlistTabFragment extends Fragment implements OnClickListener, OnItemClickListener{

	View view;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.wishlist_tab_fragment, container, false);
        view = v;
        
        Button addWishlistButton = (Button)v.findViewById(R.id.addNewList);
        addWishlistButton.setOnClickListener(this);
        List<WishList> wishLists = getAllWishLists(v);
        WishListTabFragmentAdapter adapter = new WishListTabFragmentAdapter(v, wishLists);
        ListView wishListView = (ListView)v.findViewById(R.id.allWishListsListView);
        wishListView.setAdapter(adapter);
        wishListView.setOnItemClickListener(this);
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
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		TextView listText = (TextView) view.findViewById(R.id.textView1);
        String text = listText.getText().toString();
        
        Intent intent = new Intent(view.getContext(), WishlistItemsActivity.class);
        // add the selected text item to our intent.
        intent.putExtra("listName", text);
        startActivity(intent); 
	}
}
