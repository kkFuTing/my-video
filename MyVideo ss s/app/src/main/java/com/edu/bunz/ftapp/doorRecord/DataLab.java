package com.edu.bunz.ftapp.doorRecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.edu.bunz.ftapp.doorRecord.database.DataBaseHelper;
import com.edu.bunz.ftapp.doorRecord.database.DataCursorWrapper;
import com.edu.bunz.ftapp.doorRecord.database.DataDbSchema.DataTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by asuss on 2017/9/27.
 */

public class DataLab {
    private static DataLab sCrimeLab;



    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static DataLab get(Context context){

        if (sCrimeLab == null){
            sCrimeLab = new DataLab(context);
        }
        return sCrimeLab;
    }
    private DataLab(Context context){

        mContext =context.getApplicationContext();
        mDatabase = new DataBaseHelper(mContext)
                .getWritableDatabase();

    }

    public void addData(Data d){
        ContentValues values = getContentValues(d);
        mDatabase.insert(DataTable.NAME,null,values);
    }

    public void deleteCrime(Data d) {

        String uuidString = d.getId().toString();
        mDatabase.delete(DataTable.NAME,
                DataTable.Cols.UUID + " = ?",
                new String[]{uuidString}
        );

    }

    public  List<Data> getDatas(){
        List<Data> crimes = new ArrayList<>();

        DataCursorWrapper cursor = queryCrimes(null,null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                crimes.add(cursor.getData());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return crimes;
    }

    public Data getData(UUID id){

        DataCursorWrapper cursor = queryCrimes(
                DataTable.Cols.UUID  + " = ?",
                new String[] {id.toString()}
        );
        try {
            if (cursor.getCount() == 0){
                return  null;
            }
            cursor.moveToFirst();
            return  cursor.getData();
        }finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Data data){
        File filesDir = mContext.getFilesDir();
        if (filesDir  == null) {
            return null;
        }
        return new File(filesDir,data.getPhotoFilename());
    }

    public void updateCrime(Data data){
        String uuidString = data.getId().toString();
        ContentValues values = getContentValues(data);
        mDatabase.update(DataTable.NAME,values,DataTable.Cols.UUID +  "= ?",new String[] {uuidString});
    }

    private DataCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                DataTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
//                null,
                null,
                null
        );
        return  new DataCursorWrapper(cursor);
    }

    public static ContentValues getContentValues(Data data){
        ContentValues values = new ContentValues();
        values.put(DataTable.Cols.UUID,data.getId().toString());
        values.put(DataTable.Cols.TITLE,data.getTtitle());
        values.put(DataTable.Cols.DATE,data.getDate().getTime());
//        values.put(CrimeTable.Cols.TIME,crime.getTime().getTime());
        values.put(DataTable.Cols.SOLVED,data.isSolved() ? 1 : 0);
        values.put(DataTable.Cols.SUSPECT,data.getSuspect());
        values.put(DataTable.Cols.SUSPECT_PN,data.getSuspectPN());

        return  values;
    }

}
