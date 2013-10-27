package com.example.note;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
          super(context, Constant.NAME_DB, null, 1);
      }

	@Override
	public void onCreate(SQLiteDatabase db) {
	      Log.d(Constant.APP_LOG_TAG, "--- onCreate database ---");

	      db.execSQL("create table mytable ("
	          + "id integer primary key autoincrement," 
	          + "name text,"
	          + "description text" + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
