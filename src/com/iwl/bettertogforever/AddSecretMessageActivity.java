package com.iwl.bettertogforever;

import com.iwl.bettertogforever.connections.utils.BetterTogForeverHttpConnectUtils;
import com.iwl.bettertogforever.model.UserIdCoupleIdPair;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AddSecretMessageActivity extends ActivityImpl {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_secret_message);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_secret_message, menu);
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
	
	public void addMsgClicked(View view) {
		EditText secretMessage = (EditText)findViewById(R.id.secretMsgAddText);
		String msgToUpdate = secretMessage.getText().toString();
		UserIdCoupleIdPair userIdCoupleId = getUserIdCoupleId();
		new BetterTogForeverHttpConnectUtils().addSecretMessage(userIdCoupleId.getUserId(), userIdCoupleId.getCoupleId(), msgToUpdate);
	}
	
	private UserIdCoupleIdPair getUserIdCoupleId() {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		UserIdCoupleIdPair userCoupleIdDetail = dbDao.getUserIdCoupleIdPair();
		dbDao.close();
		return userCoupleIdDetail;
	}
}
