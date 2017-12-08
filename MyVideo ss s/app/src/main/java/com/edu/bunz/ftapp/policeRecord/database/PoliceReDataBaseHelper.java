package com.edu.bunz.ftapp.policeRecord.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.edu.bunz.ftapp.policeRecord.database.PoliceReDataDbSchema.policeReDataTable;


/**
 * Created by asuss on 2017/10/9.
 */

public class PoliceReDataBaseHelper extends SQLiteOpenHelper {
    private static final  int VERSION = 1;
    //数据库名
    private static final String DATABASE_NAME = "recordBase.db";

    public PoliceReDataBaseHelper(Context context){
        super(context,DATABASE_NAME,null, VERSION);
    }

    //数据库不存在，则创建数据库
    @Override
    public void onCreate(SQLiteDatabase db){
   db.execSQL("create table " + policeReDataTable.NAME
                   + "(" +
                " _id integer primary key autoincrement, "+
                   policeReDataTable.Cols.UUID + ", " +
                   policeReDataTable.Cols.PHONE + ", " +
                   policeReDataTable.Cols.DATE  +")"
        );

    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
