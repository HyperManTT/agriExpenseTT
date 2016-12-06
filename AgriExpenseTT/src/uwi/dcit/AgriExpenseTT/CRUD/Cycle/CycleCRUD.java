package uwi.dcit.AgriExpenseTT.CRUD.Cycle;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.CRUD.DBOperations;
import uwi.dcit.AgriExpenseTT.CRUD.ObjectTypeMapper;

/**
 * Created by shivr on 12/1/2016.
 */

// TODO: 12/4/2016 This class must also be linked with the transaction log code!

public class CycleCRUD extends ObjectTypeMapper {

    public CycleCRUD(Context context){
        super(context, CycleContract.CycleEntry.TABLE_NAME, CycleContract.CycleEntry._ID);
    }

    @Override
    public Cycle getObjectFromDB(int id) {
        Cycle cycle = new Cycle();
        DBOperations dbOperations = new DBOperations(db, dbh);
        Cursor receivedData = dbOperations.getObject(tableName, idFieldName, id);
        if(receivedData.getCount()<1){
            cycle.setCropId(-1);
        }
        else{
            cycle.setCursorValues(receivedData);
        }
        receivedData.close();
        return cycle;
    }

    @Override
    public ArrayList getAllObjectsFromDB() {
        ArrayList<Cycle> list = new ArrayList();
        DBOperations dbOperations = new DBOperations(db, dbh);
        Cursor allObjectsCursor = dbOperations.getAllObjects(CycleContract.CycleEntry.TABLE_NAME);
        allObjectsCursor.moveToFirst();
        if(allObjectsCursor.getCount()>0){
            while(allObjectsCursor.moveToNext()){
                Cycle cycleObject = new Cycle();
                cycleObject.setCursorValues(allObjectsCursor);
                list.add(cycleObject);
                allObjectsCursor.moveToNext();
            }
        }
        allObjectsCursor.close();
        return list;
    }

    public String getCropNameFromID(int id){
        DBOperations dbOperations = new DBOperations(db, dbh);
        return dbOperations.findResourceName(id);
    }
}
