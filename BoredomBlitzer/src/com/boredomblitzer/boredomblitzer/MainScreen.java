package com.boredomblitzer.boredomblitzer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_screen, menu);
        return true;
    }
    
    public void blitzBtnPress(View view){
    	Intent intent = new Intent(this, ShowActivity.class);
    	startActivity(intent);
    }
    
}
