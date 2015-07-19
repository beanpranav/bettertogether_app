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
	
	public static final String WISHLIST_TABLE = "WISHLIST";
	public static final String WISHLIST_ID_COLUMN = "WISHLIST_ID";
	public static final String WISHLIST_DESCRIPTION_COLUMN = "WISHLIST_DESC";
	

	public static final String WISHLIST_TABLE_CREATE_QUERY = "create table " + WISHLIST_TABLE +
																"(" + WISHLIST_ID_COLUMN
																+ " integer," + WISHLIST_DESCRIPTION_COLUMN
																+ " text);";
	
	public static final String WISHLIST_TABLE_DROP_QUERY = "drop table " + WISHLIST_TABLE + ";";
	
	public static final String WISHLIST_ITEMS_TABLE = "WISHLIST_ITEMS_TABLE";
	public static final String WISHLIST_ID_WI_COLUMN = "WISHLIST_ID";
	public static final String WISHLIST_ITEM_ID_COLUMN = "WISHLIST_ITEM_ID";
	public static final String WISHLIST_ITEM_NAME_COLUMN = "WISHLIST_ITEM_NAME";
	public static final String WISHLIST_ITEM_DESCRIPTION_COLUMN = "WISHLIST_ITEM_DESC";
	public static final String WISHLIST_ITEM_STATUS_COLUMN = "WISHLIST_ITEM_STATUS";
	

	public static final String WISHLIST_ITEMS_TABLE_CREATE_QUERY = "create table " + WISHLIST_ITEMS_TABLE +
																"(" + WISHLIST_ID_WI_COLUMN
																+ " integer," + WISHLIST_ITEM_ID_COLUMN
																+ " integer," + WISHLIST_ITEM_NAME_COLUMN
																+ " text," + WISHLIST_ITEM_DESCRIPTION_COLUMN
																+ " text," + WISHLIST_ITEM_STATUS_COLUMN
																+ " text);";
	
	public static final String WISHLIST_ITEMS_TABLE_DROP_QUERY = "drop table " + WISHLIST_ITEMS_TABLE + ";";
}
