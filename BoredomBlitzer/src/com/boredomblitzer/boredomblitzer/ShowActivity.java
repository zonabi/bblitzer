package com.boredomblitzer.boredomblitzer;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ShowActivity extends Activity {
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		
		Intent intent = getIntent();
		String act_txt = intent.getStringExtra(MainScreen.ACT_TITLE);
		String cat_id = intent.getStringExtra(MainScreen.CAT_ID);
		String cat_txt = intent.getStringExtra(MainScreen.CAT_TITLE);
		String cat_img = intent.getStringExtra(MainScreen.CAT_IMAGE);
		
		
		//MyHelperClass MyHelperClass = new MyHelperClass(this, "test");
		
		/*
		Context urContext = this;
		DataAdapter mDbHelper = new DataAdapter(urContext);        
		mDbHelper.createDatabase();      
		mDbHelper.open();

		Cursor testdata = mDbHelper.getActivityFromID(2);
		
		String logTag = "DBCursor";
		Log.i(logTag, "testdata: " + testdata);

		mDbHelper.close();
		
		
		// TextView tv = (TextView) findViewById(R.id.activityTitle);
            			// tv.setText(actTitle);
		
		*/
		
		setActTextField(act_txt, cat_txt, cat_id);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_show, menu);
		return true;
	}
	
	public void setActTextField(String txtStr, String txtCat, String intCat){
		//TODO something
		TextView tv_act = (TextView) findViewById(R.id.activityTitle);
		tv_act.setText(txtStr);
		
		TextView tv_cat = (TextView) findViewById(R.id.activityCategory);
		tv_cat.setText(txtCat);
		
		setCatImage(intCat);
	}
	
	public void setCatImage(String catID){
		
	}

}
