package com.yhpark.githubex.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by ppyh0 on 2017-04-06.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int version = 1;

    private static DBHelper helper = null;

    public static DBHelper getInstance(Context context) {
        return helper == null ? new DBHelper(context) : helper;
    }

    public DBHelper(Context context) {
        super(context, "GithubEx_DB", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table TBL_USER (" +
                "userData text" +
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public synchronized void insertUserData(UserResult result) {
        if (result == null) {
            throw new NullPointerException("result is null.");
        }

        Gson gson = new Gson();
        String toStringData = gson.toJson(result);

        SQLiteDatabase db = getWritableDatabase();
        try {
            db.beginTransaction();
            db.execSQL("insert into TBL_USER value (" +
                    "userData=" + toStringData + ");");
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public synchronized void insertUserData(List<UserResult> resultList) {
        if (resultList == null) {
            throw new NullPointerException("resultList is null.");
        }

        SQLiteDatabase db = getWritableDatabase();
        try {
            for (UserResult userResult : resultList) {
                Gson gson = new Gson();
                String toStringData = gson.toJson(userResult);

                db.beginTransaction();
                db.execSQL("insert into TBL_USER (userData) values ('" +
                        toStringData +
                        "');");
                db.setTransactionSuccessful();
                db.endTransaction();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
