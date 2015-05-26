package com.iwl.bettertogforever;

import com.iwl.bettertogforever.sqllite.db.BetterTogForeverSqlliteDao;

import android.app.Activity;
import android.content.Context;

public class ActivityImpl extends Activity{

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
