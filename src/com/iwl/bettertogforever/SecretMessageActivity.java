package com.iwl.bettertogforever;

import com.iwl.bettertogforever.connections.utils.BetterTogForeverHttpConnectUtils;
import com.iwl.bettertogforever.model.UserIdCoupleIdPair;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class SecretMessageActivity extends ActivityImpl {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secret_message);
		
		TextView secretMessage = (TextView)findViewById(R.id.secretMsg);
		String secretMsg = getSecretMsg();
		
		if(secretMsg == null){
			UserIdCoupleIdPair userIdCoupleId = getUserIdCoupleId(); 
			secretMsg = new BetterTogForeverHttpConnectUtils().getSecretMessage(userIdCoupleId.getUserId(), userIdCoupleId.getCoupleId());
			updateSecretMsgTodb(secretMsg);
			if(secretMsg == null || secretMsg.equals("")){
				secretMsg = "No secret message for you yet";
			}
		}
		secretMessage.setText(secretMsg);
	}
	
	private UserIdCoupleIdPair getUserIdCoupleId() {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		UserIdCoupleIdPair userCoupleIdDetail = dbDao.getUserIdCoupleIdPair();
		dbDao.close();
		return userCoupleIdDetail;
	}
	
	private void updateSecretMsgTodb(String msg) {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		dbDao.insertSecretMsg(msg);
		dbDao.close();
	}

	private String getSecretMsg() {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		String secretMsg = dbDao.getSecretMsg();
		dbDao.close();
		return secretMsg;
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
