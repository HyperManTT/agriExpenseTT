package uwi.dcit.AgriExpenseTT.CRUD.Cycle;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.CRUD.DBOperations;
import uwi.dcit.AgriExpenseTT.CRUD.ObjectTypeMapper;
import uwi.dcit.AgriExpenseTT.helpers.DbHelper;

/**
 * Created by shivr on 12/1/2016.
 */


public class CycleCRUD extends ObjectTypeMapper {

    public CycleCRUD(Context context, SQLiteDatabase db, DbHelper dbh){
        super(context, CycleContract.CycleEntry.TABLE_NAME, CycleContract.CycleEntry._ID, db, dbh);
    }

    @Override
    public Cycle getObjectFromDB(int id) {
        Cycle cycle = new Cycle();
        DBOperations dbOperations = new DBOperations(db);
        Cursor receivedData = dbOperations.getObject(tableName, idFieldName, id);
        if(receivedData.getCount()<1){
            cycle.setCropId(-1);
        }
        else{
            receivedData.moveToFirst();
            cycle.setCursorValues(receivedData);
        }
        receivedData.close();
        return cycle;
    }

    @Override
    public ArrayList getAllObjectsFromDB() {
        ArrayList<Cycle> list = new ArrayList();
        DBOperations dbOperations = new DBOperations(db);
        Cursor allObjectsCursor = dbOperations.getAllObjects(CycleContract.CycleEntry.TABLE_NAME);
        allObjectsCursor.moveToFirst();
        if (allObjectsCursor.getCount() > 0) {
            while (allObjectsCursor.moveToNext()) {
                Cycle cycleObject = new Cycle();
                cycleObject.setCursorValues(allObjectsCursor);
                list.add(cycleObject);
                allObjectsCursor.moveToNext();
            }
        }
        allObjectsCursor.close();
        return list;
    }
}
