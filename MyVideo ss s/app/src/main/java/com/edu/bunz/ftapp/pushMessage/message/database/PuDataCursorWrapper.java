package com.edu.bunz.ftapp.pushMessage.message.database;

import android.database.Cursor;
import android.database.CursorWrapper;


import com.edu.bunz.ftapp.pushMessage.message.MessageData;
import com.edu.bunz.ftapp.pushMessage.message.database.PuDataDbSchema.DataTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by asuss on 2017/10/24.
 */

public class PuDataCursorWrapper extends CursorWrapper{
    public PuDataCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public MessageData getData(){
        String uuidString = getString(getColumnIndex(DataTable.Cols.UUID));
        String title = getString(getColumnIndex(DataTable.Cols.TITLE));
        long date = getLong(getColumnIndex(DataTable.Cols.DATE));


        MessageData messageData = new MessageData(UUID.fromString(uuidString));
        messageData.setTtitle(title);
        messageData.setDate(new Date(date));


        return messageData;
    }
}
