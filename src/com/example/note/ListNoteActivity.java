package com.example.note;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListNoteActivity extends Activity {

	private ListView mNoteListView;
	private Button mAddNoteButton;
	private DBHelper dbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_note);
		
		dbHelper = new DBHelper(this);
		
		final String[] names = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис",
			      "Костя", "Игорь", "Анна", "Денис", "Андрей" };
		
		mNoteListView = (ListView) this.findViewById(R.id.noteListView);
		mAddNoteButton = (Button) this.findViewById(R.id.addNoteButton);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, names);

		    // присваиваем адаптер списку
		mNoteListView.setAdapter(adapter);
		
		mNoteListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getBaseContext(), NoteActivity.class);
				intent.putExtra("NAME_NOTE", names[position]);
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

}
