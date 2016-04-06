package com.qinaqian.game.baby_login.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kuku on 2015/12/18.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // 数据库名称和版本
    private static final String DB_NAME = "login";
    private static final int DB_VERSION = 1;
    // 数据库用户表
    private static final String DB_TABLE = "create table user (id integer primary key autoincrement, "
            + "username text not null, password text not null);";
    /**
     * 数据库辅助构造函数。
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    /**
     * 创建数据库表。
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DB_TABLE);
    }
    /**
     * 处理表版本和的表。
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        Log.w(DatabaseHelper.class.getName(),
                "Upgrading databse from version" + oldVersion + "to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS user");
        onCreate(database);
    }

}
