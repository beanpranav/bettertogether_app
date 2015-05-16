package com.iwl.bettertogforever;

import com.iwl.bettertogforever.connections.utils.BetterTogForeverHttpConnectUtils;
import com.iwl.bettertogforever.model.UserIdCoupleIdPair;
import com.iwl.bettertogforever.model.response.AuthUserIdStatus;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PendingAcceptanceActivity extends ActivityImpl {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pending_acceptance);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pending_acceptance, menu);
		return true;
	}
	
	public void proceedToAppClicked(View view) {
		Intent secretMsgIntent = new Intent(this, SecretMessageActivity.class);
		startActivity(secretMsgIntent);
	 }
	
	public void sendReinviteClicked(View view) {
		Toast.makeText(this, "Sending a reinvite to spouse ", Toast.LENGTH_LONG).show();
		UserIdCoupleIdPair usrCplId = getUserId();
		new BetterTogForeverHttpConnectUtils().resendAddSpouseNotification(usrCplId.getUserId(), usrCplId.getCoupleId());
	}
	
	public UserIdCoupleIdPair getUserId() {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		UserIdCoupleIdPair usrIdCplId = dbDao.getUserIdCoupleIdPair();
		dbDao.close();
	    return usrIdCplId;
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
