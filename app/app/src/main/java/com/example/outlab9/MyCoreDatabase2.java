package com.example.outlab9;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyCoreDatabase2 extends SQLiteOpenHelper {

    static final private String DB_NAME="Education2";
    static final private String DB_TABLE = "assignment";
    static final private int DB_VER = 1;

    Context ctx;
    SQLiteDatabase myDb;

    public MyCoreDatabase2(@Nullable Context ct) {
        super(ct, DB_NAME, null, DB_VER);
        ctx = ct;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table " + DB_TABLE+" (_id integer primary key autoincrement,title text,description text,date text,time text);");
        Log.i("Database", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DB_TABLE);
        onCreate(db);
    }

    public void deleteTask(String title, String description){
        myDb = this.getWritableDatabase();
        myDb.delete(DB_TABLE , "title=? and description=?" , new String[]{String.valueOf(title), String.valueOf(description)});
    }

    public void insertData(String s1, String s2, String s3, String s4){
        myDb = getWritableDatabase();
        myDb.execSQL("insert into " + DB_TABLE+ " (title,description,date,time) values('"+s1+"','"+s2+"','"+s3+"','"+s4+"');");
        String s = s1 + " " + s2 + " " + s3+ " " + s4;
        Toast.makeText(ctx, "Data Saved Successfully" + s,Toast.LENGTH_SHORT).show();
    }

    public void getAll(ArrayList<String> l1, ArrayList<String> l2, ArrayList<String> l3, ArrayList<String> l4){
        myDb = getReadableDatabase();
        StringBuilder str;
        try (Cursor cr = myDb.rawQuery("Select * from " + DB_TABLE, null)) {
            str = new StringBuilder();
            while (cr.moveToNext()) {
                String s1 = cr.getString(1);
                String s2 = cr.getString(2);
                String s3 = cr.getString(3);
                String s4 = cr.getString(4);
                str.append(s1 + " :1 " + s2 + " :2 " + s3 + " :3 " + s4 + " :4\n");
                l1.add(s1);
                l2.add(s2);
                l3.add(s3);
                l4.add(s4);
            }
        }
        //Toast.makeText(ctx,str.toString(),Toast.LENGTH_LONG).show();
    }

}
