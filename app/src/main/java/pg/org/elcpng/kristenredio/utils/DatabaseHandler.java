package pg.org.elcpng.kristenredio.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import pg.org.elcpng.kristenredio.models.Notice;
import pg.org.elcpng.kristenredio.models.Stream;

public class DatabaseHandler extends SQLiteOpenHelper {

	// Database Version
	private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "kristenredio.db";
 
    // Data Tables
    public static final String NOTICES_TABLE = "notifications";
	public static final String STREAMS_TABLE = "streams";

    public DatabaseHandler(Context context) 
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }    

	@Override
	public void onCreate(SQLiteDatabase db) {
    			
    	StringBuilder sql;
	    
	    sql = new StringBuilder();
	    sql.append("create table ").append(NOTICES_TABLE)
	        .append("( ")
	        .append(" noticeid integer primary key autoincrement not null,")
	        .append(" noticetype integer,")
	        .append(" noticefrom integer,")
	        .append(" title text,")
	        .append(" message text,")
	        .append(" imageurl text,")
	        .append(" videourl text,")
	        .append(" status integer,")
	        .append(" timestamp integer")
	        .append("); ");
	    db.execSQL(sql.toString());

		sql = new StringBuilder();
		sql.append("create table ").append(STREAMS_TABLE)
				.append("( ")
				.append(" noticeid integer primary key autoincrement not null,")
				.append(" noticetype integer,")
				.append(" noticefrom integer,")
				.append(" title text,")
				.append(" message text,")
				.append(" imageurl text,")
				.append(" videourl text,")
				.append(" status integer,")
				.append(" timestamp integer")
				.append("); ");
		db.execSQL(sql.toString());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS " + NOTICES_TABLE);
		db.execSQL("DROP TABLE IF EXISTS " + STREAMS_TABLE);
        // Create tables again
        onCreate(db);
	}

    public long AddNotificationEntry(
    		int noticetype, 
    		int from, 
    		String title, 
    		String message, 
    		String imageurl,
    		String videourl,
    		int status,
    		long timestamp){
    	    	    	    	
    	SQLiteDatabase db = this.getWritableDatabase();
        	
    	long lastInsertID = -1;
		    	
        ContentValues values = new ContentValues();
        values.put("noticetype", noticetype);
        values.put("noticefrom", from);
        values.put("title", title);
        values.put("message", message);
        values.put("imageurl", imageurl);
        values.put("videourl", videourl);
        values.put("status", status);
        values.put("timestamp", timestamp);
                
        try
        {
        	lastInsertID = db.insert(NOTICES_TABLE, null, values);
        }
        catch(SQLiteConstraintException e)
        {
        	
        } 
        
        return lastInsertID;
        
    }
    
    /**
     * Getting Messages List from the database
     * */
    public List<Notice> GetNoticesList()
    {
		List<Notice> Notices = new ArrayList<>();
        
        String selectQuery = "SELECT * FROM " + NOTICES_TABLE + " ORDER BY noticeid DESC;";
          
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
                
        if(cursor.moveToFirst())
        {
        	do
        	{
        		Notice notice = new Notice();
        		        		
        		notice.setNoticeID(cursor.getInt(0));
        		notice.setNoticeType(cursor.getInt(1));
        		notice.setNoticeFrom(cursor.getInt(2));
        		notice.setTitle(cursor.getString(3));
        		notice.setMessage(cursor.getString(4));        		
        		notice.setImageURL(cursor.getString(5));
        		notice.setVideoURL(cursor.getString(6)); 
        		notice.setStatus(cursor.getInt(7)); 
        		notice.setTimeStamp(cursor.getLong(8));
                
        		Notices.add(notice);
                
        	}
        	while (cursor.moveToNext());
        }
        
        cursor.close();
        db.close(); 
        
        return Notices;
    }
    
    /**
     * Deletes Notices from the database
     * */
    public int DeleteNotice(long noticeID)
    {
    	int Response = -1;
    	SQLiteDatabase db = this.getWritableDatabase();	 
        
        try
        {
        	 long id = db.delete(NOTICES_TABLE, "noticeid = '" + noticeID + "'", null);
        	             
             if(id > 0)
             {
            	 Response = 0;
             }
             else if(id == -1)
             {
            	 Response = 1;
             }            
        }
        catch(Exception e)
        {
        	Response = 1;
        }
        
        if(db.isOpen())
    	{
    		db.close();
    	}
        
        return Response;
    }

	//////////////////////////////////////////////

	public long AddStreamEntry(
			int noticetype,
			int from,
			String title,
			String message,
			String imageurl,
			String videourl,
			int status,
			long timestamp){

		SQLiteDatabase db = this.getWritableDatabase();

		long lastInsertID = -1;

		ContentValues values = new ContentValues();
		values.put("noticetype", noticetype);
		values.put("noticefrom", from);
		values.put("title", title);
		values.put("message", message);
		values.put("imageurl", imageurl);
		values.put("videourl", videourl);
		values.put("status", status);
		values.put("timestamp", timestamp);

		try
		{
			lastInsertID = db.insert(STREAMS_TABLE, null, values);
		}
		catch(SQLiteConstraintException e)
		{

		}

		return lastInsertID;

	}

	/**
	 * Getting Messages List from the database
	 * */
	public List<Stream> GetStreamsList()
	{
		List<Stream> Streams = new ArrayList<>();

		String selectQuery = "SELECT * FROM " + STREAMS_TABLE + " ORDER BY noticeid DESC;";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if(cursor.moveToFirst())
		{
			do
			{
				Stream item = new Stream();

				item.setNoticeID(cursor.getInt(0));
				item.setNoticeType(cursor.getInt(1));
				item.setNoticeFrom(cursor.getInt(2));
				item.setTitle(cursor.getString(3));
				item.setMessage(cursor.getString(4));
				item.setImageURL(cursor.getString(5));
				item.setVideoURL(cursor.getString(6));
				item.setStatus(cursor.getInt(7));
				item.setTimeStamp(cursor.getLong(8));

				Streams.add(item);

			}
			while (cursor.moveToNext());
		}

		cursor.close();
		db.close();

		return Streams;
	}

	/**
	 * Deletes Notices from the database
	 * */
	public int DeleteStream(long noticeID)
	{
		int Response = -1;
		SQLiteDatabase db = this.getWritableDatabase();

		try
		{
			long id = db.delete(STREAMS_TABLE, "noticeid = '" + noticeID + "'", null);

			if(id > 0)
			{
				Response = 0;
			}
			else if(id == -1)
			{
				Response = 1;
			}
		}
		catch(Exception e)
		{
			Response = 1;
		}

		if(db.isOpen())
		{
			db.close();
		}

		return Response;
	}

	//////////////////////////////////////////////
}
