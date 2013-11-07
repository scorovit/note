package com.example.note;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class ListNoteActivity extends Activity {

	private ListView mNoteListView;
	private ImageButton mAddNoteButton;
	private SQLDataSourceNote mSqlDataSourceNote;
	private ArrayList<Note> mNoteList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_note);

		mNoteListView = (ListView) this.findViewById(R.id.noteListView);
		mAddNoteButton = (ImageButton) this.findViewById(R.id.addNoteButton);

		mNoteListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getBaseContext(), NoteActivity.class);
				intent.putExtra("ID_NOTE", String.valueOf(mNoteList.get(position).id));
				startActivity(intent);
			}
		});

		mAddNoteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(), NoteActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSqlDataSourceNote = new SQLDataSourceNote();
		mNoteList = (ArrayList<Note>) mSqlDataSourceNote.getAllNotes();
		NoteListAdapter mAdapter = new NoteListAdapter(this, R.layout.note_listing_row, mNoteList);
		mNoteListView.setAdapter(mAdapter);
	}

}
