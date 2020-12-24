package com.ts.bt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PhoneBookDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_PHONEBOOK = "create table phonebook(id integer primary key autoincrement, phonebook_name text, phonebook_number text,phonebook_mac text)";
    private Context mContext;

    public PhoneBookDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PHONEBOOK);
    }

    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
    }
}
