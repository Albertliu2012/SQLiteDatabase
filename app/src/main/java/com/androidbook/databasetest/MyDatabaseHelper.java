package com.androidbook.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/*
                                _oo8oo_
                               o8888888o
                               88" . "88
                               (| -_- |)
                               0\  =  /0
                             ___/'==='\___
                           .' \\|     |// '.
                          / \\|||  :  |||// \
                         / _||||| -:- |||||_ \
                        |   | \\\  -  /// |   |
                        | \_|  ''\---/''  |_/ |
                        \  .-\__  '-'  __/-.  /
                      ___'. .'  /--.--\  '. .'___
                   ."" '<  '.___\_<|>_/___.'  >' "".
                  | | :  `- \`.:`\ _ /`:.`/ -`  : | |
                  \  \ `-.   \_ __\ /__ _/   .-` /  /
              =====`-.____`.___ \_____/ ___.`____.-`=====
                                `=---=`
/**
* @time 2019/5/26 21:28 
* @version 1.00
* @author XingChen
*/
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String CREATE_BOOK = "Create table book ("
            +"id integer primary key autoincrement,"
            +"author text,"
            +"price real,"
            +"pages integer,"
            +"name text)";

    private static final String CREATE_CATEGORY = "create table Category ("
            +"id integer primary key autoincrement,"
            +"category_name text,"
            +"category_code integer)";
    private Context mContext;

    public MyDatabaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }
}
