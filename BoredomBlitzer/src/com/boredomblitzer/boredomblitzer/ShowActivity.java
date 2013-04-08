package com.boredomblitzer.boredomblitzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowActivity extends Activity {
	
	private DataAdapter mDbHelper; 
	
	String act_txt;

	public final static String ACT_TITLE = "com.example.myfirstapp.ACT_TITLE";
	public final static String CAT_ID = "com.example.myfirstapp.CAT_ID";
	public final static String CAT_TITLE = "com.example.myfirstapp.CAT_TITLE";
	//public final static String CAT_IMAGE = "com.example.myfirstapp.CAT_IMAGE";
	
	protected static final String TAG = "ShowActivity";
	
	//shake gesture 
	private SensorManager mSensorManager;
	private ShakeEventListener mSensorListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		
		//set custom fonts
		Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Action_Man_Bold.ttf"); 
        TextView txt_cat = (TextView) findViewById(R.id.activityCategory);  
        txt_cat.setTypeface(font);  
        TextView txt_act = (TextView) findViewById(R.id.activityTitle);  
        txt_act.setTypeface(font);  
		
		Intent intent = getIntent();
		act_txt = intent.getStringExtra(MainScreen.ACT_TITLE);
		String cat_id = intent.getStringExtra(MainScreen.CAT_ID);
		String cat_txt = intent.getStringExtra(MainScreen.CAT_TITLE);
		//String cat_img = intent.getStringExtra(MainScreen.CAT_IMAGE);
		
		
		setActTextField(act_txt, cat_txt, cat_id);
		
		Context urContext = this;
		mDbHelper = new DataAdapter(urContext);
		
		
		//shake
		 mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		    mSensorListener = new ShakeEventListener();   
		
		    mSensorListener.setOnShakeListener(new ShakeEventListener.OnShakeListener() {
		
		      @Override
			public void onShake() {
		    	  restartRandomActivity(getCurrentFocus());
		      }
		    });
		
	}
	
	public void restartRandomActivity(View view){
		Intent intent = getIntent();
  	  
  	  	mDbHelper.open();
    	
      	Random ran = new Random();
      	int randomNum = ran.nextInt(539)+1;
      	//int randomNum = 251;  //testing
      	
      	Cursor testdata = mDbHelper.getActivityFromID(randomNum);
      	
      	//String actTitle = testdata.getString(testdata.getColumnIndex("Act_Title"));
  		// String catID = testdata.getString(testdata.getColumnIndex("Category"));
  		// Log.i(TAG, "in activity actTitle: " + mDbHelper.actTitle + " catID: " + mDbHelper.catID);
  		 intent.putExtra(ACT_TITLE, mDbHelper.actTitle);
  		 intent.putExtra(CAT_ID, mDbHelper.catID);
  		 intent.putExtra(CAT_TITLE, mDbHelper.catTitle);
  		 //intent.putExtra(CAT_IMAGE, mDbHelper.catImage);
  		 testdata.close();
		 
    	mDbHelper.close();
  	  
  	  finish();
  	  startActivity(intent);
  	 // Log.i(TAG, "SHAKE IT!");
	}
	
	public void shareIt(View view){
		//sharing implementation
		List<Intent> targetedShareIntents = new ArrayList<Intent>();
		Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
		sharingIntent.setType("text/plain");
		String shareBody = "Bored? Come join me! Let's " + act_txt + " - suggested by the Boredom Blitzer app http://BoredomBlitzer.com";

		PackageManager pm = view.getContext().getPackageManager();
		List<ResolveInfo> activityList = pm.queryIntentActivities(sharingIntent, 0);
		for(final ResolveInfo app : activityList) {
			
			 String packageName = app.activityInfo.packageName;
			 Intent targetedShareIntent = new Intent(android.content.Intent.ACTION_SEND);
			 targetedShareIntent.setType("text/plain");
			 targetedShareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Boredom Blitzer Idea!");
			 if(TextUtils.equals(packageName, "com.facebook.katana")){
				 targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "http://www.BoredomBlitzer.com");
			 } else {
				 targetedShareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
			 }
			 
			 targetedShareIntent.setPackage(packageName);
			 targetedShareIntents.add(targetedShareIntent);
			 
		}
		
		Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), "Share Idea");
		
		chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));
		startActivity(chooserIntent);
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_show, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
    	//respond to menu item
    	switch(item.getItemId()){
    	case R.id.menu_settings:
    		startActivity(new Intent(this, AboutApp.class));
    		return true;
    		default:
    			return super.onOptionsItemSelected(item);
    			
    	}
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
	
	public void setActTextField(String txtStr, String txtCat, String intCat){
		//TODO something
		TextView tv_act = (TextView) findViewById(R.id.activityTitle);
		tv_act.setText(txtStr);
		
		TextView tv_cat = (TextView) findViewById(R.id.activityCategory);
		tv_cat.setText(txtCat);
		
		setCatImage(intCat);
	}
	
	public void setCatImage(String catID){
		int catInt = Integer.decode(catID);
		ImageView cat_img = (ImageView) findViewById(R.id.categoryImage);
		switch(catInt) {
			case 1:
				//load img
				cat_img.setImageResource(R.drawable.bblizter_cat1);
				break;
			case 2:
				cat_img.setImageResource(R.drawable.bblizter_cat2);
				break;
			case 3:
				cat_img.setImageResource(R.drawable.bblizter_cat3);
				break;
			case 4:
				cat_img.setImageResource(R.drawable.bblizter_cat4);
				break;
			case 5:
				cat_img.setImageResource(R.drawable.bblizter_cat5);
				break;
			case 6:
				cat_img.setImageResource(R.drawable.bblizter_cat6);
				break;
			case 7:
				cat_img.setImageResource(R.drawable.bblizter_cat7);
				break;
			case 8:
				cat_img.setImageResource(R.drawable.bblizter_cat8);
				break;
			case 9:
				cat_img.setImageResource(R.drawable.bblizter_cat9);
				break;
			case 10:
				cat_img.setImageResource(R.drawable.bblizter_cat10);
				break;
			case 11:
				cat_img.setImageResource(R.drawable.bblizter_cat11);
				break;
			case 12:
				cat_img.setImageResource(R.drawable.bblizter_cat12);
				break;	
			case 13:
				cat_img.setImageResource(R.drawable.bblizter_cat13);
				break;
			case 14:
				cat_img.setImageResource(R.drawable.bblizter_cat14);
				break;
			case 15:
				cat_img.setImageResource(R.drawable.bblizter_cat15);
				break;
			case 16:
				cat_img.setImageResource(R.drawable.bblizter_cat16);
				break;
			case 17:
				cat_img.setImageResource(R.drawable.bblizter_cat17);
				break;
			case 18:
				cat_img.setImageResource(R.drawable.bblizter_cat18);
				break;
			case 19:
				cat_img.setImageResource(R.drawable.bblizter_cat19);
				break;
			case 20:
				cat_img.setImageResource(R.drawable.bblizter_cat20);
				break;	
		}
	}
	
	

}
