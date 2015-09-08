package com.iwl.bettertogforever;

import com.iwl.bettertogforever.connections.utils.BetterTogForeverHttpConnectUtils;
import com.iwl.bettertogforever.model.UserIdCoupleIdPair;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecretMessageActivity extends FragmentActivity {

	 private FragmentTabHost mTabHost;

	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secret_message);
        
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("Secret msg").setIndicator("Love-Note", null),
                SecretMessageTabFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Wishlist").setIndicator("Wishlists", null),
                WishlistTabFragment.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("Settings").setIndicator("Settings", null),
                SettingsTabFragment.class, null);
        
        Intent currentIntent = getIntent();
        int defaultTabValue = currentIntent.getIntExtra("defaultTab", 0);
        
        if(defaultTabValue != 0){
        	mTabHost.setCurrentTab(defaultTabValue - 1);
        }
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.secret_message, menu);
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
