package com.qinaqian.game.baby_login.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kuku on 2015/12/18.
 */
public class DatabaseAdapter {
    //表名
    private static final String LOGIN_TABLE = "user";
    //单一id录
    public static final String COL_ID = "id";
    //用户名和密码列的表
    public static final String COL_USERNAME = "username";
    public static final String COL_PASSWORD = "password";

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    /**
     * 适配器的构造函数。
     * @param context
     */
    public DatabaseAdapter(Context context) {
        this.context = context;
    }

    /**
     * 创建数据库辅助,数据库。
     *
     * @return
     * @throws SQLException
     */
    public DatabaseAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    /**
     * 关闭数据库。
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * 创建用户名和密码。
     *
     * @param username The username.
     * @param password The password.
     * @return
     */
    public long createUser(String username, String password) {
        ContentValues initialValues = createUserTableContentValues(username, password);
        return database.insert(LOGIN_TABLE, null, initialValues);
    }

    /**
     * 给定一个id删除用户的细节。
     *
     * @param rowId Column id.
     * @return
     */
    public boolean deleteUser(long rowId) {
        return database.delete(LOGIN_TABLE, COL_ID + "=" + rowId, null) > 0;
    }

    public boolean updateUserTable(long rowId, String username, String password) {
        ContentValues updateValues = createUserTableContentValues(username, password);
        return database.update(LOGIN_TABLE, updateValues, COL_ID + "=" + rowId, null) > 0;
    }

    /**
     * 获取所有用户的详细信息存储在登录表。
     *
     * @return
     */
    public Cursor fetchAllUsers() {
        return database.query(LOGIN_TABLE, new String[] { COL_ID, COL_USERNAME,
                COL_PASSWORD }, null, null, null, null, null);
    }

    /**
     * 检索一个特定用户的细节,给定一个用户名和密码
     *
     * @return
     */
    public Cursor fetchUser(String username, String password) {
        Cursor myCursor = database.query(LOGIN_TABLE,
                new String[] { COL_ID, COL_USERNAME, COL_PASSWORD },
                COL_USERNAME + "='" + username + "' AND " +
                        COL_PASSWORD + "='" + password + "'", null, null, null, null);

        if (myCursor != null) {
            myCursor.moveToFirst();
        }
        return myCursor;
    }

    /**
     * 返回给定的行id表细节。
     * @param rowId The table row id.
     * @return
     * @throws SQLException
     */
    public Cursor fetchUserById(long rowId) throws SQLException {
        Cursor myCursor = database.query(LOGIN_TABLE,
                new String[] { COL_ID, COL_USERNAME, COL_PASSWORD },
                COL_ID + "=" + rowId, null, null, null, null);
        if (myCursor != null) {
            myCursor.moveToFirst();
        }
        return myCursor;
    }

    /**
     * 存储用户名和密码在创建新的登录信息。
     * @param username The user name.
     * @param password The password.
     * @return The entered values.
     */
    private ContentValues createUserTableContentValues(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, username);
        values.put(COL_PASSWORD, password);
        return values;
    }
}
