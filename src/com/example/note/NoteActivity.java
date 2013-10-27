package com.example.note;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class NoteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
		
		Bundle extras = getIntent().getExtras();
		if (extras == null) {
			return;
		}
		
		((TextView) this.findViewById(R.id.captionTextView)).setText(extras.getString("NAME_NOTE"));
		
	}
	
}
