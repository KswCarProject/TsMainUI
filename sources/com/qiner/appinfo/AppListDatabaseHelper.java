package com.qiner.appinfo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppListDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_APPLIST = "create table applist(id integer primary key autoincrement, packagename text, ischecked integer)";
    private Context mContext;

    public AppListDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_APPLIST);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
