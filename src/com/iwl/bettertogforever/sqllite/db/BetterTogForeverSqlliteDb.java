package com.iwl.bettertogforever.sqllite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BetterTogForeverSqlliteDb extends SQLiteOpenHelper {
	
	public BetterTogForeverSqlliteDb(Context context) {
		super(context, SqlQueries.DATABASE_NAME, null, SqlQueries.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SqlQueries.USER_ID_COUPLE_ID_TABLE_CREATE_QUERY);
		db.execSQL(SqlQueries.USER_REG_ID_TABLE_CREATE_QUERY);
		db.execSQL(SqlQueries.SECRET_MSG_TABLE_CREATE_QUERY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO on every version upgrade of the app
		db.execSQL(SqlQueries.USER_ID_COUPLE_ID_TABLE_DROP_QUERY); 
		db.execSQL(SqlQueries.USER_REG_ID_TABLE_DROP_QUERY); 
		db.execSQL(SqlQueries.SECRET_MSG_TABLE_DROP_QUERY); 
		onCreate(db);
	}
}
