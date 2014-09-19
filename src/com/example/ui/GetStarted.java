package com.example.ui;


import com.example.trackit.R;
import com.example.trackit.R.id;
import com.example.trackit.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class GetStarted extends Activity {
	
	EditText mNameEditText;
	SharedPreferences mSharedPreferences;
	public static final String PREFERENCES = "Prefs" ;
	public static String TAG_ISBOOKMARK = "IsBookmark";
	Context mContext = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.get_started);
		mNameEditText = (EditText) findViewById(R.id.nameEditText);
		Button getStartedButton = (Button) findViewById(R.id.getStartedButton);
		
		getStartedButton.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		       if(!mNameEditText.getText().toString().equals(""))
		       {
		    	   mSharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
		    	   Editor editor = mSharedPreferences.edit();
		    	   editor.putString("Name", mNameEditText.getText().toString());
		    	   editor.commit();
		    	   Intent intent = new Intent(getApplicationContext(),EventsListActivity.class);
		    	   intent.putExtra(TAG_ISBOOKMARK, false);
		    	   startActivity(intent);
		       }
		       else
		       {
		    	   final AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();
		    	   alertDialog.setTitle("Invalid name");
		    	   alertDialog.setMessage("Please Enter A Name To Get Started");
		    	   alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		    	   public void onClick(DialogInterface dialog, int which) {
		    			alertDialog.dismiss();
		    		   }
		    	   });	   
		    	   alertDialog.show();
		       }
		    }
		});
		
		   
	}
}
