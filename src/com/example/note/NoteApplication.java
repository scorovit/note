package com.example.note;

import android.app.Application;

public class NoteApplication extends Application {
	private static NoteApplication sSingleton;

	private SQLiteHelper mSqLiteHelper;

	/**
	 * Initialize singleton HypnosisApplication.
	 */
	public static NoteApplication getInstance() {
		return sSingleton;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		sSingleton = this;
	}
	
	public SQLiteHelper getSQLiteHelper() {
		if (mSqLiteHelper == null) {
			mSqLiteHelper = new SQLiteHelper(getApplicationContext());
		}

		return mSqLiteHelper;
	}
}
