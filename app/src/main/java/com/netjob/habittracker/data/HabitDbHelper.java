package com.netjob.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.netjob.habittracker.data.HabitDataContract.HabitEntry;

/**
 * Created by root on 1/26/17.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HabitTracker.db";
    public static final int DATABASE_VERSION = 1;
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            HabitEntry.TABLE_NAME + " (" +
            HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HabitEntry.COLUMN_NAME_RAN + " INTEGER NOT NULL DEFAULT " +
            HabitEntry.RAN_DEFAULT + ", " +
            HabitEntry.COLUMN_NAME_MILES + " INTEGER NOT NULL DEFAULT " +
            HabitEntry.MILES_DEFAULT + ", " +
            HabitEntry.COLUMN_NAME_PUSHUPS + " INTEGER NOT NULL DEFAULT " +
            HabitEntry.PUSHUPS_DEFAULT + ", " +
            HabitEntry.COLUMN_NAME_WATERCUPS + " INTEGER NOT NULL DEFAULT " +
            HabitEntry.WATERCUPS_DEFAULT + ", " +
            HabitEntry.COLUMN_NAME_SYMPTOMS + " TEXT NOT NULL DEFAULT" +
            " \'" + HabitEntry.SYMPTOMS_DEFAULT + "\')";



    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
