package com.example.note;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends Activity implements OnClickListener {

	private EditText mNameNoteEditText, mDescriptionNoteEditText;
	private Button mDeleteNoteButton, mSaveNoteButton;
	private String id;
	private SQLDataSourceNote mSqlDataSourceNote;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		
		mSqlDataSourceNote = new SQLDataSourceNote();
		
		mNameNoteEditText = (EditText) this.findViewById(R.id.nameNoteEditText);
		mDescriptionNoteEditText = (EditText) this.findViewById(R.id.descriptionNoteEditText);
		
		mDeleteNoteButton = (Button) this.findViewById(R.id.deleteNoteButton);
		mDeleteNoteButton.setOnClickListener(this);
		mSaveNoteButton = (Button) this.findViewById(R.id.saveNoteButton);
		mSaveNoteButton.setOnClickListener(this);
		
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}

		id = extras.getString("ID_NOTE");
		
		if (id != null) {
			try {
				Note note = mSqlDataSourceNote.getNote(id);
				mNameNoteEditText.setText(note.name);
				mDescriptionNoteEditText.setText(note.description);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
		case R.id.deleteNoteButton:
			
			break;
		case R.id.saveNoteButton:
			
			String nameNote = mNameNoteEditText.getText().toString().trim();
			if (nameNote.length() == 0) {
				Toast.makeText(NoteActivity.this, "Input name note", Toast.LENGTH_LONG).show();
				return;
			}
			
			String descriptionNote = mDescriptionNoteEditText.getText().toString().trim();
			if (mSqlDataSourceNote.addNote(nameNote, descriptionNote)) {
				Toast.makeText(NoteActivity.this, "Note added in DB", Toast.LENGTH_LONG).show();
			}
			
			break;
		}
		
	}
	
}
