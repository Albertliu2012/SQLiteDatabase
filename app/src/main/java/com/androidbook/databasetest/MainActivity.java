package com.androidbook.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,2);

        Button queryButton = (Button)findViewById(R.id.query_data);   //查询
        Button deleteButton = (Button)findViewById(R.id.delete_data); //删除
        Button updateButton = (Button)findViewById(R.id.update_data); //更新
        Button addButton = (Button)findViewById(R.id.add_data);       //添加
        Button createDatabase = (Button)findViewById(R.id.create_database); //创建
        createDatabase.setOnClickListener(creDatabase);
        
        queryButton.setOnClickListener(queryDt);
        deleteButton.setOnClickListener(deleteDt);
        updateButton.setOnClickListener(updataDt);
        addButton.setOnClickListener(addDt);
    }

    View.OnClickListener queryDt = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            //查询BOOK表中所有的数据
            Cursor cursor = db.query("Book",null,null,null,null,null,null);
            if (cursor.moveToFirst()){
                do {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                    double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    Log.d(TAG, "Book name   is "+name);
                    Log.d(TAG, "Book author is "+author);
                    Log.d(TAG, "Book pages  is "+pages);
                    Log.d(TAG, "Book price  is "+price);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
    };
    View.OnClickListener deleteDt = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete("Book","pages > ?",new String[]{"500"
            });
        }
    };
    View.OnClickListener updataDt = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("price","10.99");
            db.update("Book",values,"name = ?",new String[]{"The Da Vinci Code"});

        }
    };
    View.OnClickListener addDt = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            //装入第一组数据
            values.put("name","The Da Vinci Code");
            values.put("author","Dan Brown");
            values.put("pages",454);
            values.put("price",16.96);
            db.insert("Book",null,values);
            values.clear();
            //装入第二组数据
            values.put("name","The lost Symbol");
            values.put("author","Dan Brown");
            values.put("pages","510");
            values.put("price",19.95);
            db.insert("Book",null,values);
            values.clear();
        }
    };
    View.OnClickListener creDatabase = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dbHelper.getWritableDatabase();
        }
    };
}
