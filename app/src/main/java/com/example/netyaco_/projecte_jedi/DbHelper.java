package com.example.netyaco_.projecte_jedi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by netyaco_ on 29/01/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    //Declaracion del nombre de la base de datos
    public static final int DATABASE_VERSION = 1;

    //Declaracion global de la version de la base de datos
    public static final String DATABASE_NAME = "database.sqlite";

    //Declaracion del nombre de la tabla
    public static final String USER_TABLE ="User";

    public static final String CN_USER = "user";
    public static final String CN_PASS = "pass";
    public static final String CN_POINTS = "points";
    public static final String CN_RANK = "rank";
    //public static final String CN_POINTS = "points";

    //sentencia global de cracion de la base de datos
    public static final String USER_TABLE_CREATE = "CREATE TABLE " + USER_TABLE + "( " +
            CN_USER + " TEXT PRIMARY KEY UNIQUE, " +
            CN_PASS + " TEXT, " +
            CN_POINTS + " INTEGER, " +
            CN_RANK + " INTEGER);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void newUser (ContentValues values, String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(
                tableName,
                null,
                values);
    }

    public Cursor getUser (String user) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"user", "pass", "points", "rank"};
        String[] where = {user};
        Cursor c = db.query(
                USER_TABLE,  // The table to query
                columns,                                    // The columns to return
                "user=?",                                   // The columns for the WHERE clause
                where,                                      // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                null                                        // The sort order
        );
        return c;
    }

    //obtener una lista de coches
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"user", "points", "rank"};
        Cursor c = db.query(
                USER_TABLE,          // The table to query
                columns,            // The columns to return
                null,               // The columns for the WHERE clause
                null,               // The values for the WHERE clause
                null,               // don't group the rows
                null,               // don't filter by row groups
                null                // The sort order
        );
        return c;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
