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
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
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
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() 
    {
        mDbHelper.close();
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
            			 String actTitle = mCur.getString(mCur.getColumnIndex("Title"));
            			 Log.i(TAG, "actTitle: " + actTitle);
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
             Log.e(TAG, "getTestData >>"+ mSQLException.toString());
             throw mSQLException;
         }
     }
     
     public Cursor getActivityFromID(int actID)
     {
         try
         {
             //String sql ="SELECT * FROM Activities";
             String sql ="SELECT Title FROM Activities WHERE _id = " + actID;
             //String[] finalResultStr = null;
             Cursor mCur = mDb.rawQuery(sql, null);
             if (mCur!=null)
             {
            	 if(mCur.moveToFirst()){
            		 do {
            			 String actTitle = mCur.getString(mCur.getColumnIndex("Title"));
            			 Log.i(TAG, "actTitle: " + actTitle);
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
             Log.e(TAG, "getTestData >>"+ mSQLException.toString());
             throw mSQLException;
         }
     }
     
}