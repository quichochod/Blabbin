package cse.usf.edu.android.db;

import android.content.Context;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by adria on 3/3/2017.
 */

public class BlabbinDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "UserWhales2";
    public static final int DB_VERSION = 1;
    public static BlabbinDBHelper sInstance;
    public static SQLiteDatabase db;
    public static Context context;
    public String workstring;

    public static synchronized BlabbinDBHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new BlabbinDBHelper(context, DB_NAME, null, DB_VERSION);
            db = sInstance.getWritableDatabase();

        }
        return sInstance;
    }


    //Creating the Database with sql statements
    private static final String CREATE_USERS_TABLE =
            "create table UserWhales2(" +
                    "whale text not null)";


    public BlabbinDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){

        super(context, DB_NAME, null, DB_VERSION);
        BlabbinDBHelper.context = context;
        workstring = "HEYYY";
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        database.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS UserWhales2");
        onCreate(database);
    }

    public String getString(){

        return this.workstring;
    }



}