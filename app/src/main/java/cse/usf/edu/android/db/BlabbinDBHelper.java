package cse.usf.edu.android.db;

import android.content.Context;
import android.database.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by adria on 3/3/2017.
 */

public class BlabbinDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "UserWhales";
    private static final int DB_VERSION = 1;
    private static BlabbinDBHelper sInstance;

    public static synchronized BlabbinDBHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new BlabbinDBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    //Creating the Database with sql statements
    private static final String CREATE_USERS_TABLE =
            "create table UserWhales(" +
                    "whale text not null)";


    public BlabbinDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        database.execSQL("DROP TABLE IF EXISTS UserWhales");
        onCreate(database);
    }


}