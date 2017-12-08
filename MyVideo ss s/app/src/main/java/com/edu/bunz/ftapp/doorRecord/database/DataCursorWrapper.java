package com.edu.bunz.ftapp.doorRecord.database;

import android.database.Cursor;
import android.database.CursorWrapper;


import com.edu.bunz.ftapp.doorRecord.Data;
import com.edu.bunz.ftapp.doorRecord.database.DataDbSchema.DataTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by asuss on 2017/10/24.
 */

public class DataCursorWrapper extends CursorWrapper{
    public DataCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public Data getData(){
        String uuidString = getString(getColumnIndex(DataTable.Cols.UUID));
        String title = getString(getColumnIndex(DataTable.Cols.TITLE));
        long date = getLong(getColumnIndex(DataTable.Cols.DATE));
//        long time = getLong(getColumnIndex(CrimeTable.Cols.TIME));
        int isSolved = getInt(getColumnIndex(DataTable.Cols.SOLVED));
        String suspect = getString(getColumnIndex(DataTable.Cols.SUSPECT));
        String suspect_pn = getString(getColumnIndex(DataTable.Cols.SUSPECT_PN));

        Data data = new Data(UUID.fromString(uuidString));
        data.setTtitle(title);
        data.setDate(new Date(date));
//        crime.setTime(new Date(time));
        data.setSolved(isSolved != 0);

        data.setSuspect(suspect);
        data.setSuspectPN(suspect_pn);

        return data;
    }
}
