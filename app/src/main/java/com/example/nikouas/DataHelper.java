package com.example.nikouas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="biodata.db";
    private  static final int DATABASE_VERSION=1;

    public DataHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String DataSQL="create table biodata(id text primary key ,nama text null,jk text null);";

//        String DataSQL="drop table biodata";
        Log.d("Data","onCreate:"+DataSQL);
        db.execSQL(DataSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){

    }

}
