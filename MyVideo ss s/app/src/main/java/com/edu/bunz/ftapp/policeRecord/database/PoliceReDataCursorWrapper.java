package com.edu.bunz.ftapp.policeRecord.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.edu.bunz.ftapp.policeRecord.PoliceReData;
import com.edu.bunz.ftapp.policeRecord.database.PoliceReDataDbSchema.policeReDataTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by asuss on 2017/10/24.
 */
//    CursorWrapper 神奇的表数据处理工具 功能：封装数据表中的原始字段值
public class PoliceReDataCursorWrapper extends CursorWrapper{

//    创建一个cursor封装类，该类继承了Cursor类的全部方法
//      这样的封装类目的：定制新方法，以方便操作内部Cursor
    public PoliceReDataCursorWrapper(Cursor cursor){
        super(cursor);
    }

//    返回具有UUID 的数据模型 PoliceReData
    public PoliceReData getData(){
        String uuidString = getString(getColumnIndex(policeReDataTable.Cols.UUID));
        String phone = getString(getColumnIndex(policeReDataTable.Cols.PHONE));
        long date = getLong(getColumnIndex(policeReDataTable.Cols.DATE));


        PoliceReData PoliceReData = new PoliceReData(UUID.fromString(uuidString));
        PoliceReData.setPhone(phone);
        PoliceReData.setDate(new Date(date));


        return PoliceReData;
    }
}
