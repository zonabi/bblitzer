package com.boredomblitzer.boredomblitzer;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class ShowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		
		
		Context urContext = this;
		DataAdapter mDbHelper = new DataAdapter(urContext);        
		mDbHelper.createDatabase();      
		mDbHelper.open();

		Cursor testdata = mDbHelper.getActivityFromID(2);
		
		String logTag = "DBCursor";
		Log.i(logTag, "testdata: " + testdata);

		mDbHelper.close();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_show, menu);
		return true;
	}

}
