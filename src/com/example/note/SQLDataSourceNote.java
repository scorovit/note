package com.example.note;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SQLDataSourceNote {

	// Columns settings
	public static final String TABLE_NAME = "notes";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DESCRIPTION = "description";

	private String[] allNoteColumns = { COLUMN_ID, COLUMN_NAME, COLUMN_DESCRIPTION };

	protected SQLiteDatabase openReadableDb() throws SQLException {
		return NoteApplication.getInstance().getSQLiteHelper().getReadableDatabase();
	}

	/**
	 * Opens and returns a database that will be used for writing.
	 * 
	 * @throws java.sql.SQLException
	 */
	protected SQLiteDatabase openWritableDb() throws SQLException {
		return NoteApplication.getInstance().getSQLiteHelper().getWritableDatabase();
	}

	/**
	 * Closes opened database.
	 */
	protected void closeDb() {
		NoteApplication.getInstance().getSQLiteHelper().close();
	}

	/**
	 * Add note in database.
	 * 
	 * @param key
	 * @param value
	 * @return true if the setting was added.
	 */
	public boolean addNote(String name, String description) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, name);
		values.put(COLUMN_DESCRIPTION, description);
	
		try {
			SQLiteDatabase db = openWritableDb();

			long insertId = db.insert(TABLE_NAME, null, values);
			Cursor cursor = db.query(TABLE_NAME, allNoteColumns, COLUMN_ID + " = " + insertId, null, null, null,
					null);
			cursor.moveToFirst();
			cursor.close();

			closeDb();
		} catch (Exception e) {
			Log.e(Constant.APP_LOG_TAG, "Error add note: ", e);
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Update the selected note.
	 * 
	 * @param name
	 *            - unique identifier note
	 * @param description
	 *            - description for updating
	 * @return true if the note was updated.
	 */
	public boolean updateNote(String name, String description) {
		boolean isUpdated = false;

		ContentValues values = new ContentValues();
		values.put(COLUMN_DESCRIPTION, description);

		try {
			SQLiteDatabase db = openWritableDb();
			isUpdated = (db.update(TABLE_NAME, values, COLUMN_NAME + "=?", new String[] { name + "" })) > 0;
			closeDb();
		} catch (Exception e) {
			Log.e(Constant.APP_LOG_TAG, "Error update setting: ", e);
			e.printStackTrace();
		}

		return isUpdated;
	}

	/**
	 * Get note from database.
	 * 
	 * @param name
	 *            - unique identifier note
	 * @return - note
	 */
	public Note getNote(String name) {
		Note note = null;

		try {
			SQLiteDatabase db = openReadableDb();

			Cursor cursor = db.query(true, TABLE_NAME, allNoteColumns, COLUMN_NAME + "=" + "'" + name + "'", null,
					null, null, null, null);

			if ((cursor != null) && (cursor.getCount() != 0)) {
				cursor.moveToFirst();
				note = cursorToNote(cursor);
				cursor.close();
			}

			closeDb();
		} catch (Exception e) {
			Log.e(Constant.APP_LOG_TAG, "Error in get settings", e);
			e.printStackTrace();
		}

		return note;
	}

	/**
	 * Get a list of notes from database.
	 * 
	 * @return - list of notes
	 */
	public List<Note> getAllNotes() {
		List<Note> notes = new ArrayList<Note>();

		try {
			SQLiteDatabase db = openReadableDb();

			Cursor cursor = db.query(TABLE_NAME, allNoteColumns, null, null, null, null, null);

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				Note note = cursorToNote(cursor);
				notes.add(note);
				cursor.moveToNext();
			}
			// Make sure to closeDb the cursor
			cursor.close();

			closeDb();
		} catch (Exception e) {
			Log.e(Constant.APP_LOG_TAG, "Error get notes: ", e);
			e.printStackTrace();
		}

		return notes;
	}

	/**
	 * Remove all the records from the table notes.
	 */
	public void removeAllNote() {
		try {
			SQLiteDatabase db = openWritableDb();
			db.delete(TABLE_NAME, null, null);
			closeDb();
		} catch (Exception e) {
			Log.e(Constant.APP_LOG_TAG, "Error remove notes", e);
			e.printStackTrace();
		}
	}

	/**
	 * A method that return note from a query on a SQLiteDatabase.
	 * 
	 * @param cursor
	 *            - Cursor implementation that exposes results from a query on a
	 *            SQLiteDatabase.
	 * @return - note from DB.
	 */
	private Note cursorToNote(Cursor cursor) {
		Note note = new Note();
		note.id = cursor.getInt(0);
		note.name = cursor.getString(1);
		note.description = cursor.getString(2);

		return note;
	}

}
