package zan.jie.lee.rssreader.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import zan.jie.lee.commons.db.IDB;


public abstract class DB<T> extends SQLiteOpenHelper implements IDB<T> {

    public static final String DB_NAME = "rss.db";
    public static final String ID_COL = "id";

    protected DB(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(getDbCreate());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase db() {
        return getWritableDatabase();
    }

    protected void insert(ContentValues values) {
        try {
            long insertId = db().insert(getTableName(), null,
                    values);
            Log.i("", Long.toString(insertId));
        } catch (SQLiteConstraintException e) {
            Log.w("", e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public List<T> getAll() {
        List<T> list = new ArrayList<T>();
        try {
            Cursor cursor = db().query(getTableName(), getAllColums(), null, null, null, null, null);
            while (cursor.moveToNext()) {
                T object = getFromCursor(cursor);
                list.add(object);
            }
            cursor.close();
        } catch (SQLException e) {
            list.clear();
        } catch (RuntimeException e) {
            list.clear();
        }
        return list;
    }

    public abstract void create(T object);

    public abstract T getFromCursor(Cursor cursor);

    public abstract String[] getAllColums();

    public abstract String getTableName();

    public abstract String getDbCreate();

    public abstract void clear();


}
