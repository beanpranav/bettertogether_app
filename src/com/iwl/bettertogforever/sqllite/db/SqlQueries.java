package com.iwl.bettertogforever.sqllite.db;

public class SqlQueries {

	public static final String DATABASE_NAME = "bettertogforever.db";
	public static final int DATABASE_VERSION = 1;
	
	public static final String USER_ID_COUPLE_ID_TABLE = "USER_COUPLE_ID";
	public static final String USER_ID_COLUMN = "USER_ID";
	public static final String COUPLE_ID_COLUMN = "COUPLE_ID";
	public static final String COUPLE_EMAIL_COLUMN = "COUPLE_EMAIL";
	public static final String COUPLE_ADD_STATUS_COLUMN = "COUPLE_ADD_STATUS_COLUMN";
	
	public static final String USER_ID_COUPLE_ID_TABLE_CREATE_QUERY = "create table " + USER_ID_COUPLE_ID_TABLE +
																	"(" + USER_ID_COLUMN
																	+ " integer, " + COUPLE_ID_COLUMN
																	+ " integer, " + COUPLE_ADD_STATUS_COLUMN
																	+ " integer," + COUPLE_EMAIL_COLUMN
																	+ " text);";
	
	public static final String USER_REG_ID_TABLE = "USER_REG_ID";
	public static final String USER_REG_ID_COLUMN = "REG_ID";
	
	public static final String USER_REG_ID_TABLE_CREATE_QUERY = "create table " + USER_REG_ID_TABLE +
																"(" + USER_REG_ID_COLUMN
																+ " text);";
														
	public static final String USER_ID_COUPLE_ID_TABLE_DROP_QUERY = "drop table " + USER_ID_COUPLE_ID_TABLE + ";";
	public static final String USER_REG_ID_TABLE_DROP_QUERY = "drop table " + USER_REG_ID_TABLE + ";";
	
	public static final String SECRET_MSG_TABLE = "SECRET_MSG";
	public static final String SECRET_MSG_COLUMN = "MSG";

	public static final String SECRET_MSG_TABLE_CREATE_QUERY = "create table " + SECRET_MSG_TABLE +
																"(" + SECRET_MSG_COLUMN
																+ " text);";
	
	public static final String SECRET_MSG_TABLE_DROP_QUERY = "drop table " + SECRET_MSG_TABLE + ";";
}
