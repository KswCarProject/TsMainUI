package com.ts.bt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BtDatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_BONED_DEVICE = "create table boned_device(id integer primary key autoincrement, addr text, name text)";
    private static final String CREATE_DIALLOG = "create table diallog(id integer primary key autoincrement, addr text, name text, num text , time text,type text,calltime integer)";
    private static final String CREATE_PHONEBOOK = "create table phonebook(id integer primary key autoincrement, addr text, name text, num text, collect integer, pinyin text, first_name text, middle_name text, given_name text)";
    private Context mContext;

    public BtDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PHONEBOOK);
        db.execSQL(CREATE_DIALLOG);
        db.execSQL(CREATE_BONED_DEVICE);
    }

    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
    }
}
