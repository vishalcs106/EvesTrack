package com.example.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DataBaseHelper extends SQLiteOpenHelper 
{ 
private static String DB_PATH = "";  
private static String DB_NAME ="EventDB.db";
private SQLiteDatabase mDataBase;  
private final Context mContext; 
 
public DataBaseHelper(Context context)  
{ 
    super(context, DB_NAME, null, 1);
    DB_PATH = "/data/data/" + context.getPackageName() + "/databases/"; 
    this.mContext = context; 
    
}    
 
public void createDataBase() throws IOException 
{ 
   boolean mDataBaseExist = checkDataBase(); 
    if(!mDataBaseExist) 
    { 
        this.getReadableDatabase(); 
        this.close(); 
        try  
        { 
            copyDataBase(); 
            
        }  
        catch (IOException mIOException)  
        { 
        	mIOException.printStackTrace();
        } 
    } 
} 
    
    private boolean checkDataBase() 
    { 
        File dbFile = new File(DB_PATH + DB_NAME); 
        return dbFile.exists(); 
    } 
 
    private void copyDataBase() throws IOException 
    { 
        InputStream mInput = mContext.getAssets().open(DB_NAME); 
        String outFileName = DB_PATH + DB_NAME; 
        OutputStream mOutput = new FileOutputStream(outFileName); 
        byte[] mBuffer = new byte[1024]; 
        int mLength; 
        while ((mLength = mInput.read(mBuffer))>0) 
        { 
            mOutput.write(mBuffer, 0, mLength); 
        } 
        mOutput.flush(); 
        mOutput.close(); 
        mInput.close(); 
    } 
 
    public boolean openDataBase() throws SQLException 
    { 
        String mPath = DB_PATH + DB_NAME; 
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY); 
        return mDataBase != null; 
    } 
    
   
 
    @Override 
    public synchronized void close()  
    { 
        if(mDataBase != null) 
            mDataBase.close(); 
        super.close(); 
    }

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	} 
 
} 

 
