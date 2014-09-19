package com.example.database;

import java.io.IOException; 

import android.content.ContentValues;
import android.content.Context; 
import android.database.Cursor; 
import android.database.SQLException; 
import android.database.sqlite.SQLiteDatabase; 
import android.util.Log; 
 
public class DataAdapter  
{ 
    protected static final String TAG = "DataAdapter"; 
    private static String TABLE_EVENTS = "EVENTS";
    private static String TABLE_BOOKMARKS = "BOOKMARKS";
    private static String KEY_ID = "_id";
    private static String KEY_EVENT_ID = "EVENTS_ID";
    public static String KEY_NAME = "NAME";
    public static String KEY_PLACE = "PLACE";
    public static String KEY_TYPE = "TYPE";
    public static String KEY_CONTACT = "CONTACT";
    public static String KEY_IMAGE = "IMAGE";
 
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
 
   /* public DataAdapter open(boolean forRead) throws SQLException  
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
    } */
 
    public Cursor getEvents() 
    { 
    	try 
        { 
    		mDb = mDbHelper.getReadableDatabase();
            Cursor mCur = mDb.query(TABLE_EVENTS,new String[] { KEY_ID,KEY_NAME, KEY_PLACE,KEY_TYPE,KEY_CONTACT,KEY_IMAGE},null,null,null,null,null,null);
            if (mCur!=null) 
            { 
               mCur.moveToNext(); 
            } 
            return mCur; 
        } 
        catch (SQLException mSQLException)  
        { 
            Log.e(TAG, "getEventsData >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
    }
    
    public Cursor getBookmarks()
    {
    	try 
        { 
    		mDb = mDbHelper.getReadableDatabase();
    		String query = "SELECT _ID,NAME,PLACE,TYPE,CONTACT,IMAGE FROM EVENTS WHERE _ID IN(SELECT EVENTS_ID FROM BOOKMARKS)";
            Cursor mCur = mDb.rawQuery(query, null);
            if (mCur!=null) 
            { 
               mCur.moveToNext(); 
            } 
            return mCur; 
        } 
        catch (SQLException mSQLException)  
        { 
            Log.e(TAG, "getEventsData >>"+ mSQLException.toString()); 
            throw mSQLException; 
        } 
    }
     
    public long insertBookmark(int eventID)
    {
    	ContentValues values = new ContentValues();
    	values.put(KEY_EVENT_ID, eventID);
    	mDb = mDbHelper.getWritableDatabase();
    	long bookmarkID = mDb.insert(TABLE_BOOKMARKS, null, values);
    	return bookmarkID;
    	
    }
} 

