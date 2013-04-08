package com.boredomblitzer.boredomblitzer;

import java.io.IOException;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataAdapter 
{
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DataBaseHelper mDbHelper;
    public String actTitle;
    public String catTitle;
    public String catID;
    public String catImage;

    public DataAdapter(Context context) 
    {
        this.mContext = context;
        mDbHelper = new DataBaseHelper(mContext);
    }

    public DataAdapter createDatabase() throws SQLException 
    {
        try 
        {
            mDbHelper.createDataBase();
        } 
        catch (IOException mIOException) 
        {
           // Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DataAdapter open() throws SQLException 
    {
        try 
        {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } 
        catch (SQLException mSQLException) 
        {
           // Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public Cursor getTestData()
     {
         try
         {
             //String sql ="SELECT * FROM Activities";
             String sql ="SELECT Title FROM Activities WHERE _id = 1";
             //String[] finalResultStr = null;
             Cursor mCur = mDb.rawQuery(sql, null);
             if (mCur!=null)
             {
            	 if(mCur.moveToFirst()){
            		 do {
            			 actTitle = mCur.getString(mCur.getColumnIndex("Title"));
            			// Log.i(TAG, "actTitle: " + actTitle);
            		 }while (mCur.moveToNext());
            	 } 
            	//String tstStr = mCur.getString(getColumnIndex(0)); 
            	//Log.i(TAG, "mCur toString: " + tstStr);
                //mCur.moveToNext();
             }
             return mCur;
         }
         catch (SQLException mSQLException) 
         {
            // Log.e(TAG, "getTestData >>"+ mSQLException.toString());
             throw mSQLException;
         }
     }
     
     public void close() 
	{
    	// mCur.close();
	    mDbHelper.close();
	}

	public Cursor getActivityFromID(int actID)
     {
         try
         {

            String sql ="SELECT Act_Title, Category FROM Activities WHERE _id = " + actID;
            
            Cursor mCur = mDb.rawQuery(sql, null);
             if (mCur!=null)
             {
            	 if(mCur.moveToFirst()){
            		 do {
            			 actTitle = mCur.getString(mCur.getColumnIndex("Act_Title"));
            			 catID = mCur.getString(mCur.getColumnIndex("Category"));
            			// Log.i(TAG, "actTitle: " + actTitle + " catID: " + catID);
            			 
            			
            			 getCategory(Integer.decode(catID));
            		 }while (mCur.moveToNext());
            	 } 

             }
             mCur.close();
             return mCur;
         }
         catch (SQLException mSQLException) 
         {
            // Log.e(TAG, "getTestData >>"+ mSQLException.toString());
             throw mSQLException;
         }
     }
     
     public Cursor getCategory(int catID)
     {
         try
         {
             
             String sql ="SELECT Cat_Title, Image FROM Categories WHERE _id = " + catID;
          
             Cursor mCur = mDb.rawQuery(sql, null);
             if (mCur!=null)
             {
            	 if(mCur.moveToFirst()){
            		 do {
            			 catTitle = mCur.getString(mCur.getColumnIndex("Cat_Title"));
            			 catImage = mCur.getString(mCur.getColumnIndex("Image"));
            			// Log.i(TAG, "actTitle: " + catTitle + " catID: " + catImage);
            		 }while (mCur.moveToNext());
            	 } 

             }
             mCur.close();
             return mCur;
         }
         catch (SQLException mSQLException) 
         {
            // Log.e(TAG, "getTestData >>"+ mSQLException.toString());
             throw mSQLException;
         }
     }
     
}