package net.easyconn.platform.wrc.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: WrcSQLiteOpenHelper */
public class k extends SQLiteOpenHelper {
    private static SQLiteOpenHelper a;

    static SQLiteOpenHelper a(Context context) {
        if (a == null) {
            synchronized (k.class) {
                if (a == null) {
                    a = new k(context, "wrc.db", (SQLiteDatabase.CursorFactory) null, 1);
                }
            }
        }
        return a;
    }

    k(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS uuids(_id integer primary key autoincrement, uuid text)");
    }
}
