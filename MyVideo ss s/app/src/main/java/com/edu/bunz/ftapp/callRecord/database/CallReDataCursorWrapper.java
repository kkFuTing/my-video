package com.edu.bunz.ftapp.callRecord.database;

import android.database.Cursor;
import android.database.CursorWrapper;


import com.edu.bunz.ftapp.callRecord.CallReData;
import com.edu.bunz.ftapp.callRecord.database.CallReDataDbSchema.callReDataTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by asuss on 2017/10/24.
 */
//    CursorWrapper 神奇的表数据处理工具 功能：封装数据表中的原始字段值
public class CallReDataCursorWrapper extends CursorWrapper{

//    创建一个cursor封装类，该类继承了Cursor类的全部方法
//      这样的封装类目的：定制新方法，以方便操作内部Cursor
    public CallReDataCursorWrapper(Cursor cursor){
        super(cursor);
    }

//    返回具有UUID 的数据模型 aa
    public CallReData getData(){
        String uuidString = getString(getColumnIndex(callReDataTable.Cols.UUID));
        String phone = getString(getColumnIndex(callReDataTable.Cols.PHONE));
        long date = getLong(getColumnIndex(callReDataTable.Cols.DATE));


        CallReData CallReData = new CallReData(UUID.fromString(uuidString));
        CallReData.setPhone(phone);
        CallReData.setDate(new Date(date));


        return CallReData;
    }
}
