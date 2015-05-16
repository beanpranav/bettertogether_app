package com.iwl.bettertogforever;

import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;

public class ActivityImpl extends ActionBarActivity{

	private BetterTogForeverSqlliteDao dbDao;
	
	protected BetterTogForeverSqlliteDao getDataSource(){
		if(dbDao == null){
			dbDao =  new BetterTogForeverSqlliteDao(getApplicationContext());
		}
		return dbDao;
	}
	
	protected BetterTogForeverSqlliteDao getDataSourceFromContext(Context context){
		if(dbDao == null){
			dbDao =  new BetterTogForeverSqlliteDao(context);
		}
		return dbDao;
	}
}
