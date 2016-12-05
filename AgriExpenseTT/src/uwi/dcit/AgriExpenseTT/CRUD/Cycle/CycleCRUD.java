package uwi.dcit.AgriExpenseTT.CRUD.Cycle;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import uwi.dcit.AgriExpenseTT.CRUD.DBOperations;
import uwi.dcit.AgriExpenseTT.CRUD.ObjectTypeMapper;

/**
 * Created by shivr on 12/1/2016.
 */

// TODO: 12/4/2016 This class must also be linked with the transaction log code!

public class CycleCRUD extends ObjectTypeMapper {

    public CycleCRUD(Context context){
        super(context, CycleContract.CycleEntry.TABLE_NAME);
    }

    @Override
    public void getObjectFromDB(int id) {
        Cycle cycle = new Cycle();
        DBOperations dbOperations = new DBOperations(context);
        Cursor receivedData = dbOperations.getObject(tableName, CycleContract.CycleEntry.TABLE_NAME, id);
        if(receivedData.getCount()<1){
            cycle.setCropId(-1);
        }
        else{
            cycle.setCursorValues(receivedData);
        }
    }

    @Override
    public List getAllObjectsFromDB() {
        List list = new ArrayList();
        DBOperations dbOperations = new DBOperations(context);
        Cursor allObjectsCursor = dbOperations.getAllObjects(CycleContract.CycleEntry.TABLE_NAME);
        allObjectsCursor.moveToFirst();
        if(allObjectsCursor.getCount()>0){
            while(allObjectsCursor.moveToNext()){
                Cycle cycleObject = new Cycle();
                cycleObject.setCursorValues(allObjectsCursor);
            }
        }
        return list;
    }
}
