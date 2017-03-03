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
    private static final String USER_DB_TABLE = "users";
    private static final String USER_KEY_USERNAME = "_id";
    private static final String USER_KEY_PASSOWRD = "password";

    private Context context;
    private SQLiteDatabase database;
    private BlabbinDBHelper dbHelper;
    private SQLiteDatabase db;


    public BlabbinDBManager(Context context){
        this.context = context;
    }

    public void open() throws SQLException{
        dbHelper = new BlabbinDBHelper(context);
        database = dbHelper.getWritableDatabase();
        db = dbHelper.getReadableDatabase();
    }

    //Wraps reccord data passed in create, delete, update, and query commands
    private ContentValues createUsersValues(String UserName, String pw){
        ContentValues values = new ContentValues();
        values.put(USER_KEY_USERNAME, UserName);
        values.put(USER_KEY_PASSOWRD, pw);
        return values;
    }

    public long createUser(String UserName, String pw){
        ContentValues initialValues = createUsersValues(UserName, pw);
        return database.insert(USER_DB_TABLE, null, initialValues);
    }

    public boolean updateUsers(String UserName, String pw){
        //We do not need this table to update Users information.. ever. For now...
        //This function will not be used.
        return false;
    }

    public boolean deleteUser(String UserName){
        //Also not going to be used
        return false;
    }

    public Cursor getUser(String UserName) throws SQLException{

        String whereClause = USER_KEY_USERNAME + "=" + UserName;
        Cursor mCursor = db.query(USER_DB_TABLE, new String[] {USER_KEY_USERNAME, USER_KEY_PASSOWRD}, whereClause, null, null, null, null, null);
        if(mCursor != null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }




}
