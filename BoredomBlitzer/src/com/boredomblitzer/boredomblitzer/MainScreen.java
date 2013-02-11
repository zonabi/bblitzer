package com.boredomblitzer.boredomblitzer;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainScreen extends Activity {
	
	private DataAdapter mDbHelper; 
	
	protected static final String TAG = "MainScreen";
	
	public final static String ACT_TITLE = "com.example.myfirstapp.ACT_TITLE";
	public final static String CAT_ID = "com.example.myfirstapp.CAT_ID";
	public final static String CAT_TITLE = "com.example.myfirstapp.CAT_TITLE";
	public final static String CAT_IMAGE = "com.example.myfirstapp.CAT_IMAGE";
	
	//shake gesture 
	private SensorManager mSensorManager;
	private ShakeEventListener mSensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        
        //set custom fonts
        TextView txt = (TextView) findViewById(R.id.txt_main_top_instruct); 
        TextView txt2 = (TextView) findViewById(R.id.txtMainBottomInfo); 
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Action_Man_Bold.ttf"); 
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/Action_Man.ttf"); 
        txt.setTypeface(font);
        txt2.setTypeface(font2);
        
        Context urContext = this;
		mDbHelper = new DataAdapter(urContext);        
		mDbHelper.createDatabase();      
		mDbHelper.open();

		//Cursor testdata = mDbHelper.getActivityFromID(2);

		mDbHelper.close();
		
		//shake
		 mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		    mSensorListener = new ShakeEventListener();   
		
		    mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
		
		      public void onShake() {
		    	 blitzBtnPress(getCurrentFocus());
		    	 // Log.i(TAG, "SHAKE IT!");
		      }
		    });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_screen, menu);
        return true;
    }
    
    @Override
    protected void onResume() {
      super.onResume();
      mSensorManager.registerListener(mSensorListener,
          mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
          SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
      mSensorManager.unregisterListener(mSensorListener);
      super.onStop();
    }
    
    public void blitzBtnPress(View view){
    	Intent intent = new Intent(this, ShowActivity.class);
    	mDbHelper.open();
    	
    	Random ran = new Random();
    	int randomNum = ran.nextInt(539)+1;
    	
    	Cursor testdata = mDbHelper.getActivityFromID(randomNum);
    	
    	//String actTitle = testdata.getString(testdata.getColumnIndex("Act_Title"));
		// String catID = testdata.getString(testdata.getColumnIndex("Category"));
		 Log.i(TAG, "actTitle: " + mDbHelper.actTitle + " catID: " + mDbHelper.catID);
		 intent.putExtra(ACT_TITLE, mDbHelper.actTitle);
		 intent.putExtra(CAT_ID, mDbHelper.catID);
		 intent.putExtra(CAT_TITLE, mDbHelper.catTitle);
		 intent.putExtra(CAT_IMAGE, mDbHelper.catImage);
		 testdata.close();
		 
    	mDbHelper.close();
		//Log.i(logTag, "testdata: " + testdata);
    	startActivity(intent);
    }
    
}
