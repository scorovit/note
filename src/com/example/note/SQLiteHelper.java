package com.example.note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

	private static String NAME_DB = "noteDB";
	private static int VERSION_DB = 1;

	private static String tableNote = "create table " + SQLDataSourceNote.TABLE_NAME + " ("
			+ SQLDataSourceNote.COLUMN_ID + " integer primary key autoincrement," + SQLDataSourceNote.COLUMN_NAME
			+ " text," + SQLDataSourceNote.COLUMN_DESCRIPTION + " text" + ");";

	public SQLiteHelper(Context context) {
		super(context, NAME_DB, null, VERSION_DB);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(tableNote);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
