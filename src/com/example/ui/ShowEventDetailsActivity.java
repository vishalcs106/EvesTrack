package com.example.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.example.database.DataAdapter;
import com.example.objects.EventObj;
import com.example.trackit.R;

public class ShowEventDetailsActivity extends SherlockActivity {
	
	public EventObj mEventObj;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_event_details);
		Intent intent = getIntent();
		mEventObj = intent.getParcelableExtra("Event");
		TextView eventName = (TextView) findViewById(R.id.eventName);
		TextView eventPlace = (TextView) findViewById(R.id.eventPlace);
		TextView eventType = (TextView) findViewById(R.id.eventType);
		TextView eventContact = (TextView) findViewById(R.id.eventContact);
		ImageView eventImage = (ImageView) findViewById(R.id.eventImageView);
		eventName.setText(mEventObj.getEventName());
		eventPlace.setText(mEventObj.getEventPlace());
		eventType.setText(mEventObj.getEventType());
		eventContact.setText(mEventObj.getEventContact());
		Bitmap mBitmap = BitmapFactory.decodeByteArray(mEventObj.getImage(), 0 ,mEventObj.getImage().length);
		eventImage.setImageBitmap(mBitmap);
		
	}
	
	@Override
	  public boolean onCreateOptionsMenu(Menu menu) {
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		   inflater.inflate(R.menu.actionbar_add_menu, (com.actionbarsherlock.view.Menu) menu);
		   
		  
		   
		   return super.onCreateOptionsMenu(menu);
	  } 
	 @Override
	   public boolean onOptionsItemSelected(MenuItem item) {
	           switch (item.getItemId()) 
	           {
	           		case R.id.action_addBookmark: 
	           			DataAdapter mDataBaseHelper = new DataAdapter(this);
	           			mDataBaseHelper.createDatabase();
	           			mDataBaseHelper.insertBookmark(mEventObj.getID());
	           			Toast.makeText(getApplicationContext(), "Bookmark Added", Toast.LENGTH_LONG).show();
	           			break;
	           		case R.id.action_showBookmarks: 
	           			Intent intent = new Intent(getApplicationContext(),EventsListActivity.class);
	           			intent.putExtra(GetStarted.TAG_ISBOOKMARK, true);
	 		    	    startActivity(intent); 
	           			break;
	          }
	           return true;
	       }
	

}
