package cse.usf.edu.android.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by adria on 3/3/2017.
 */

public class BlabbinDBManager {

    //DB fields for Users table
    private static final String USER_DB_TABLE = "UserWhales2";
    //private static final String USER_KEY_USERNAME = "username";
   // private static final String USER_KEY_PASSOWRD = "password";
    private static final String USER_KEY_WHALE = "whales";

    private Context context;
    private SQLiteDatabase database;
    private BlabbinDBHelper dbHelper;
    private SQLiteDatabase db;


    public BlabbinDBManager(Context context){
        this.context = context;
    }

    public void open() throws SQLException{
        dbHelper = new BlabbinDBHelper(context, "UserWhales2", null ,1 );
        database = dbHelper.getWritableDatabase();
        db = dbHelper.getReadableDatabase();
    }

    //Wraps reccord data passed in create, delete, update, and query commands
    private ContentValues createUsersValues(String whale){
        ContentValues values = new ContentValues();
        //values.put(USER_KEY_USERNAME, UserName);
        //values.put(USER_KEY_PASSOWRD, pw);
        values.put(USER_KEY_WHALE, whale);
        return values;
    }

    public long createUser(String whale){
        ContentValues initialValues = createUsersValues(whale);
        return database.insert(USER_DB_TABLE, null, initialValues);

    }

    public boolean updateWhale(String whal){

        ContentValues args = new ContentValues();
        args.put(USER_KEY_WHALE, whal);
        //return db.update(USER_DB_TABLE, args,"whale = ?", null);
        return true;
    }

    public boolean deleteUser(String UserName){
        //Also not going to be used
        return false;
    }

    public Cursor getUser() throws SQLException{

        String whereClause = USER_KEY_WHALE + "=" + "'" + "'" ;
        Cursor mCursor = db.query(USER_DB_TABLE, new String[] {USER_KEY_WHALE}, whereClause, null, null, null, null, null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void close() {
        dbHelper.close();
    }

    /*public Cursor validateCredentials(String userName, String pw){
        String whereClause = USER_KEY_USERNAME + "=" + "'" + userName + "'" + " AND " +
                USER_KEY_PASSOWRD + "=" + "'" + pw + "'";
        Cursor mCursor = db.query(USER_DB_TABLE, new String[] {USER_KEY_USERNAME, USER_KEY_PASSOWRD}, whereClause, null, null, null, null, null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }*/




}