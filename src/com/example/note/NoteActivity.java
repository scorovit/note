package com.example.note;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NoteActivity extends Activity implements OnClickListener {

	private EditText mNameNoteEditText, mDescriptionNoteEditText;
	private Button mDeleteNoteButton, mSaveNoteButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		
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
		
		
		
//		((TextView) this.findViewById(R.id.captionTextView)).setText(extras.getString("NAME_NOTE"));
		
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
			SQLDataSourceNote sqlDataSourceNote = new SQLDataSourceNote();
			if (sqlDataSourceNote.addNote(nameNote, descriptionNote)) {
				Toast.makeText(NoteActivity.this, "Note added in DB", Toast.LENGTH_LONG).show();
			}
			
			break;
		}
		
	}
	
}
