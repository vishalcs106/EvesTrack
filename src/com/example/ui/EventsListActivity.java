package com.example.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.example.database.DataAdapter;
import com.example.objects.EventObj;
import com.example.trackit.R;

public class EventsListActivity extends SherlockActivity {
	
	SharedPreferences mSharedPreferences;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_list_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mSharedPreferences = getSharedPreferences(GetStarted.PREFERENCES, Context.MODE_PRIVATE);
        setTitle("Welcome "+mSharedPreferences.getString("Name", ""));
        DataAdapter mDataBaseHelper = new DataAdapter(this);
		mDataBaseHelper.createDatabase();  
		Cursor mCursor  = null;
		if(!getIntent().getBooleanExtra(GetStarted.TAG_ISBOOKMARK, false))
			mCursor = mDataBaseHelper.getEvents();
		else
			mCursor =  mDataBaseHelper.getBookmarks();
		EventsCustomAdapter adapter = new EventsCustomAdapter(this,mCursor);
    	final ListView lv = (ListView) findViewById(R.id.eventsList);
    	lv.setAdapter(adapter);
    	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    	    @Override
    	    public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
    	       Cursor cursor =  (Cursor) lv.getItemAtPosition(pos);
    	       EventObj mEventObj = new EventObj(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getBlob(5));
    	       Intent intent = new Intent(getApplicationContext(),ShowEventDetailsActivity.class);
    	       intent.putExtra("Event", mEventObj);
	    	   startActivity(intent);
    	       
    	    }
    	});

    }
	
	@Override
	  public boolean onCreateOptionsMenu(Menu menu) {
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		   inflater.inflate(R.menu.actionbar_show_menu, (com.actionbarsherlock.view.Menu) menu);
		   return super.onCreateOptionsMenu(menu);
	  } 
	
	 @Override
	   public boolean onOptionsItemSelected(MenuItem item) {
	           switch (item.getItemId()) 
	           {
	           		case R.id.action_showBookmarks: 
	           			Intent intent = new Intent(getApplicationContext(),EventsListActivity.class);
	           			intent.putExtra(GetStarted.TAG_ISBOOKMARK, true);
	 		    	    startActivity(intent); 
	           			break;
	          }
	           return true;
	       }

}
