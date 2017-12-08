package com.edu.bunz.ftapp.callRecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.edu.bunz.ftapp.callRecord.database.CallReDataBaseHelper;
import com.edu.bunz.ftapp.callRecord.database.CallReDataCursorWrapper;
import com.edu.bunz.ftapp.callRecord.database.CallReDataDbSchema.callReDataTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by asuss on 2017/9/27.
 */

public class CallReDataLab {
    private static CallReDataLab sCrimeLab;


    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CallReDataLab get(Context context){

        if (sCrimeLab == null){
            sCrimeLab = new CallReDataLab(context);
        }
        return sCrimeLab;
    }
    private CallReDataLab(Context context){

        //getApplicationContext() 被调用时，policeReDataBaseHelper打开类中定义的数据库
//        若是不存在，创建，首次创建调用类中onCreate()
//        若是已存在数据库，检测版本号，若在类中版本号更高就调用onUpgradle();
        mContext =context.getApplicationContext();
        mDatabase = new CallReDataBaseHelper(mContext)
                .getWritableDatabase();

    }

    //增加一条记录 d：data
    public void addData(CallReData d){
        ContentValues values = getContentValues(d);
//        参数：1：数据表名字 2：很少用到 3：要写入的数据  p234
        mDatabase.insert(callReDataTable.NAME,null,values);
    }

    public void deleteCrime(CallReData d) {
//
        String uuidString = d.getId().toString();
        mDatabase.delete(callReDataTable.NAME,
                callReDataTable.Cols.UUID + " = ?",
                new String[]{uuidString}
        );

    }

//    p238
    public  List<CallReData> getDatas(){
        List<CallReData> datas = new ArrayList<>();

        CallReDataCursorWrapper cursor = queryDatas(null,null);
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

    public CallReData getData(UUID id){
        CallReDataCursorWrapper cursor = queryDatas(
                callReDataTable.Cols.UUID  + " = ?",
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

    public File getPhotoFile(CallReData CallReData){
        File filesDir = mContext.getFilesDir();
        if (filesDir  == null) {
            return null;
        }
        return new File(filesDir, CallReData.getPhotoFilename());
    }

    public void updateData(CallReData CallReData){
        String uuidString = CallReData.getId().toString();
        ContentValues values = getContentValues(CallReData);
        mDatabase.update(callReDataTable.NAME,values,callReDataTable.Cols.UUID +  "= ?",new String[] {uuidString});
    }


//   查询记录
    private CallReDataCursorWrapper queryDatas(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                callReDataTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
//                null,
                null,
                null
        );
        return  new CallReDataCursorWrapper(cursor);
    }

    //ContentValues:负责处理数据库写入和更新操作的辅助类 键值储存类型 只能用于处理SQLite数据
//   将data记录转换为 ContentValues 实际就是在Lab中创建ContentValues实例
    public static ContentValues getContentValues(CallReData callReData){
        ContentValues values = new ContentValues();
        values.put(callReDataTable.Cols.UUID, callReData.getId().toString());
        values.put(callReDataTable.Cols.PHONE, callReData.getPhone());
        values.put(callReDataTable.Cols.DATE, callReData.getDate().getTime());



        return  values;
    }

}
