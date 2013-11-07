package com.example.note;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class NoteListAdapter extends ArrayAdapter<Note> {

	private Context mContext;
	private int mLayoutResourceId;
	private ArrayList<Note> mNoteList;


	public NoteListAdapter(Context context, int layoutResourceId, ArrayList<Note> noteList) {
		super(context, layoutResourceId, noteList);

		mContext = context;
		mLayoutResourceId = layoutResourceId;
		mNoteList = noteList;
    }

    /**
	 * Adapter method to create a view for each row
	 * Creates a holder object and assigns all data to it, after that sets holder to row
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View row = convertView;
		NoteHolder holder = null;

		if (row == null) {
			LayoutInflater mInflater = ((Activity) this.mContext).getLayoutInflater();
			row = mInflater.inflate(this.mLayoutResourceId, parent, false);

			holder = new NoteHolder();
			
			holder.id = -1;
			
			holder.nameTextView = (TextView) row.findViewById(R.id.note_listing_row_name_text_view);
            
            row.setTag(holder);
		} else {
			holder = (NoteHolder) row.getTag();
		}

		Note noteItem = mNoteList.get(position);
		holder.nameTextView.setText(noteItem.name.toUpperCase());
		holder.id = noteItem.id;
		return row;
	}
	
	private class NoteHolder {
	    public int id;
	    public TextView nameTextView;
	}

}
