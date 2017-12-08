package com.edu.bunz.ftapp.pushMessage.message.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.edu.bunz.ftapp.pushMessage.message.database.PuDataDbSchema.DataTable;


/**
 * Created by asuss on 2017/10/9.
 */

public class PuDataBaseHelper extends SQLiteOpenHelper {
    private static final  int VERSION = 1;
    private static final String DATABASE_NAME = "messageBase.db";

    public PuDataBaseHelper(Context context){
        super(context,DATABASE_NAME,null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
   db.execSQL("create table " + DataTable.NAME
                   + "(" +
                " _id integer primary key autoincrement, "+
        DataTable.Cols.UUID + ", " +
        DataTable.Cols.TITLE + ", " +
        DataTable.Cols.DATE +")"
        );

    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
