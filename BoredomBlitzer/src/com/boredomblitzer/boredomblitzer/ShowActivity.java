package com.boredomblitzer.boredomblitzer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
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
