package com.imagineappdev.wallaholic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Anonymous on 03-05-2017.
 */

public class FavouriteDbHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "favourite.db";

    public FavouriteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_FAVOURITE_TABLE = "CREATE TABLE " +
                FavouriteContract.FavouriteEntry.TABLE_NAME + " (" +
                FavouriteContract.FavouriteEntry._ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FavouriteContract.FavouriteEntry.COLUMN_IMAGENAME+ " TEXT " +
                ");";
        db.execSQL(SQL_CREATE_FAVOURITE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + FavouriteContract.FavouriteEntry.TABLE_NAME);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean addData(String item1){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FavouriteContract.FavouriteEntry.COLUMN_IMAGENAME,item1);

        long result = db.insert(FavouriteContract.FavouriteEntry.TABLE_NAME,null,contentValues);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean CheckIsDataAlreadyInDBorNot(String field1) {
        SQLiteDatabase db = this.getWritableDatabase();
        String Query = "SELECT " +FavouriteContract.FavouriteEntry.COLUMN_IMAGENAME+" FROM " +
                    FavouriteContract.FavouriteEntry.TABLE_NAME + " WHERE " +
                    FavouriteContract.FavouriteEntry.COLUMN_IMAGENAME + " = '"+field1+"'";


        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public Cursor getImageName(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ FavouriteContract.FavouriteEntry.COLUMN_IMAGENAME+
                " FROM "+ FavouriteContract.FavouriteEntry.TABLE_NAME;
        Cursor data = db.rawQuery(query,null);

        return data;

    }

    public void removeData(String field1){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + FavouriteContract.FavouriteEntry.TABLE_NAME + " WHERE " +
                FavouriteContract.FavouriteEntry.COLUMN_IMAGENAME + " = '" + field1+"'";
        db.execSQL(query);
    }
}
