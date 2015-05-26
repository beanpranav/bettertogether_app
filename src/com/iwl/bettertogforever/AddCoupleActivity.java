package com.iwl.bettertogforever;

import com.iwl.bettertogforever.connections.utils.BetterTogForeverHttpConnectUtils;
import com.iwl.bettertogforever.constants.AddSpouseRequestStatusConstants;
import com.iwl.bettertogforever.model.response.AddCoupleStatusMsg;
import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCoupleActivity extends ActivityImpl {

	Integer userId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_couple);
		Intent intent = getIntent();
		userId = intent.getIntExtra("userId", 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_couple, menu);
		return true;
	}
	
	public void addCoupleButtonClicked(View view) {
		EditText coupleEmail = (EditText)findViewById(R.id.coupleEmail);
		
		BetterTogForeverHttpConnectUtils httpUtils = new BetterTogForeverHttpConnectUtils();
		AddCoupleStatusMsg coupleAddedStatus = httpUtils.addCouple(userId, coupleEmail.getText().toString());
		
		if(coupleAddedStatus.isCoupleAdded() && coupleAddedStatus.getAcceptStatus().equals("ACCEPTED")){
			addCoupleIdToDb(coupleAddedStatus);
			Intent secretMessageIntent = new Intent(this, SecretMessageActivity.class);
			startActivity(secretMessageIntent);
		} else if(coupleAddedStatus.isCoupleAdded() && !coupleAddedStatus.getAcceptStatus().equals("ACCEPTED")) {
			Intent pendingAcceptanceActivity = new Intent(this, PendingAcceptanceActivity.class);
			startActivity(pendingAcceptanceActivity);
			insertAddSpouseRequestStatus(AddSpouseRequestStatusConstants.PENDING_ACCEPTANCE);
		} else{
			Toast.makeText(getApplicationContext(), coupleAddedStatus.getMsg(), Toast.LENGTH_LONG).show();
			insertAddSpouseRequestStatus(AddSpouseRequestStatusConstants.PENDING_ACCEPTANCE);
		}
	}

	private void insertAddSpouseRequestStatus(Integer pendingAcceptance) {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		dbDao.insertAddSpouseRequestStatus(pendingAcceptance);
		dbDao.close();
	}

	private void addCoupleIdToDb(AddCoupleStatusMsg coupleAddedStatus) {
		BetterTogForeverSqlliteDao dbDao = this.getDataSource();
		dbDao.open();
		dbDao.insertCoupleId(coupleAddedStatus.getCoupleId());
		dbDao.close();
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
