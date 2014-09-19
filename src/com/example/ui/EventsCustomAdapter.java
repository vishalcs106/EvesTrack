package com.example.ui;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trackit.R;

public class EventsCustomAdapter extends CursorAdapter  {
	
	LayoutInflater inflater;
	public EventsCustomAdapter(Context context, Cursor c) {
	super(context, c);
	inflater = LayoutInflater.from(context);
	}
	 
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
	// TODO Auto-generated method stub
	TextView eventName = (TextView)view.findViewById(R.id.eventNameText);
	TextView eventPlace = (TextView)view.findViewById(R.id.eventPlaceText);
	TextView eventType = (TextView)view.findViewById(R.id.eventTypeText);
	ImageView eventThumbnail = (ImageView) view.findViewById(R.id.eventThumbnail);
	Bitmap mBitmap = BitmapFactory.decodeByteArray(cursor.getBlob(5),0,cursor.getBlob(5).length);
	eventThumbnail.setImageBitmap(mBitmap);
	eventName.setText(cursor.getString(1));
	eventPlace.setText(cursor.getString(2));
	eventType.setText(cursor.getString(3));
	}
	 
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
	return inflater.inflate(R.layout.events_row, parent, false);
	}
}


