package com.edu.bunz.ftapp.policeRecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.edu.bunz.ftapp.policeRecord.database.PoliceReDataBaseHelper;
import com.edu.bunz.ftapp.policeRecord.database.PoliceReDataCursorWrapper;
import com.edu.bunz.ftapp.policeRecord.database.PoliceReDataDbSchema.policeReDataTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by asuss on 2017/9/27.
 */

public class PoliceReDataLab {
    private static PoliceReDataLab sCrimeLab;


    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static PoliceReDataLab get(Context context){

        if (sCrimeLab == null){
            sCrimeLab = new PoliceReDataLab(context);
        }
        return sCrimeLab;
    }
    private PoliceReDataLab(Context context){

        //getApplicationContext() 被调用时，policeReDataBaseHelper打开类中定义的数据库
//        若是不存在，创建，首次创建调用类中onCreate()
//        若是已存在数据库，检测版本号，若在类中版本号更高就调用onUpgradle();
        mContext =context.getApplicationContext();
        mDatabase = new PoliceReDataBaseHelper(mContext)
                .getWritableDatabase();

    }

    //增加一条记录 d：data
    public void addData(PoliceReData d){
        ContentValues values = getContentValues(d);
//        参数：1：数据表名字 2：很少用到 3：要写入的数据  p234
        mDatabase.insert(policeReDataTable.NAME,null,values);
    }

    public void deleteCrime(PoliceReData d) {
//
        String uuidString = d.getId().toString();
        mDatabase.delete(policeReDataTable.NAME,
                policeReDataTable.Cols.UUID + " = ?",
                new String[]{uuidString}
        );

    }

//    p238
    public  List<PoliceReData> getDatas(){
        List<PoliceReData> datas = new ArrayList<>();

        PoliceReDataCursorWrapper cursor = queryDatas(null,null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                datas.add(cursor.getData());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return datas;
    }

    public PoliceReData getData(UUID id){
        PoliceReDataCursorWrapper cursor = queryDatas(
                policeReDataTable.Cols.UUID  + " = ?",
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

    public File getPhotoFile(PoliceReData PoliceReData){
        File filesDir = mContext.getFilesDir();
        if (filesDir  == null) {
            return null;
        }
        return new File(filesDir, PoliceReData.getPhotoFilename());
    }

    public void updateData(PoliceReData PoliceReData){
        String uuidString = PoliceReData.getId().toString();
        ContentValues values = getContentValues(PoliceReData);
        mDatabase.update(policeReDataTable.NAME,values,policeReDataTable.Cols.UUID +  "= ?",new String[] {uuidString});
    }


//   查询记录
    private PoliceReDataCursorWrapper queryDatas(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                policeReDataTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
//                null,
                null,
                null
        );
        return  new PoliceReDataCursorWrapper(cursor);
    }

    //ContentValues:负责处理数据库写入和更新操作的辅助类 键值储存类型 只能用于处理SQLite数据
//   将data记录转换为 ContentValues 实际就是在Lab中创建ContentValues实例
    public static ContentValues getContentValues(PoliceReData policeReData){
        ContentValues values = new ContentValues();
        values.put(policeReDataTable.Cols.UUID, policeReData.getId().toString());
        values.put(policeReDataTable.Cols.PHONE, policeReData.getPhone());
        values.put(policeReDataTable.Cols.DATE, policeReData.getDate().getTime());



        return  values;
    }

}
