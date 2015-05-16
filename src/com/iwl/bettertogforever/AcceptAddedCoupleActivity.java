package com.iwl.bettertogforever;

import com.iwl.bettertogforever.connections.utils.BetterTogForeverHttpConnectUtils;
import com.iwl.bettertogforever.constants.AddSpouseRequestStatusConstants;
import com.iwl.bettertogforever.model.UserIdCoupleIdPair;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class AcceptAddedCoupleActivity extends ActivityImpl {

	String coupleEmail;
	Integer coupleId;
	BetterTogForeverHttpConnectUtils httpUtils = new BetterTogForeverHttpConnectUtils();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accept_added_couple);
		
		TextView emailIdTryingToAddYou = (TextView)findViewById(R.id.emailTryingToAdd);
		Intent intent = getIntent();
		coupleEmail = intent.getStringExtra("coupleEmail");
		coupleId = intent.getIntExtra("coupleId", 0);
		
		emailIdTryingToAddYou.setText(coupleEmail);
	}
	
	public void acceptButtonClicked(View view) {
		UserIdCoupleIdPair usrIdCplId = getUserId();
		httpUtils.acceptSpouse(coupleId, usrIdCplId.getUserId());
		deleteEmailOfSpouseFromDb();
		updateDbWithAddSpouseAcceptStatus(AddSpouseRequestStatusConstants.ACCEPTED);
	}
	
	private void updateDbWithAddSpouseAcceptStatus(Integer status) {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		dbDao.insertAddSpouseRequestStatus(status);
		dbDao.close();
	}

	public UserIdCoupleIdPair getUserId() {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		UserIdCoupleIdPair usrIdCplId = dbDao.getUserIdCoupleIdPair();
		dbDao.close();
	    return usrIdCplId;
	}
	
	private void deleteEmailOfSpouseFromDb() {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		dbDao.deleteSpouseEmail();
		dbDao.close();
	}

	public void declineButtonClicked(View view) {
		httpUtils.declineSpouse(coupleId);
		deleteEmailOfSpouseFromDb();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accept_added_couple, menu);
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
