package com.edu.bunz.ftapp.doorRecord.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.edu.bunz.ftapp.doorRecord.database.DataDbSchema.DataTable;


/**
 * Created by asuss on 2017/10/9.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final  int VERSION = 1;
    private static final String DATABASE_NAME = "doorBase.db";

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
   db.execSQL("create table " + DataTable.NAME
                   + "(" +
                " _id integer primary key autoincrement, "+
        DataTable.Cols.UUID + ", " +
        DataTable.Cols.TITLE + ", " +
        DataTable.Cols.DATE + ", " +
//        CrimeTable.Cols.TIME + ", " +
        DataTable.Cols.SOLVED +", "+
                   DataTable.Cols.SUSPECT + "," +
                   DataTable.Cols.SUSPECT_PN +")"
        );

    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
