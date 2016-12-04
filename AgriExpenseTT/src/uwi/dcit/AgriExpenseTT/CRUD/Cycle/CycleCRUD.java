package uwi.dcit.AgriExpenseTT.CRUD.Cycle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import uwi.dcit.AgriExpenseTT.CRUD.DBOperations;
import uwi.dcit.AgriExpenseTT.CRUD.ObjectCRUD;

/**
 * Created by shivr on 12/1/2016.
 */

// TODO: 12/4/2016 This class must also be linked with the transaction log code!

public class CycleCRUD implements ObjectCRUD {

    private Cycle cycle;
    private Context context;


    public CycleCRUD(Cycle cycle, Context context){
        this.cycle = cycle;
        this.context = context;
    }


    @Override
    public int insertObject() {
        ContentValues cv = new ContentValues();
        cv = cycle.getContentValues();
        DBOperations dbcrud = new DBOperations(context);
        // TODO: 12/4/2016 Map the properties of the cycle to content values.

        int rowId = dbcrud.insertObject(cv,CycleContract.CycleEntry.TABLE_NAME);
        return rowId;
    }

    @Override
    public void deleteObject() {
        DBOperations dbOperations = new DBOperations(context);
        dbOperations.deleteObject(CycleContract.CycleEntry.TABLE_NAME, CycleContract.CycleEntry.CROPCYCLE_CROPID, cycle.getCropId());
    }

    @Override
    public void updateObject() {
        if(cycle.isValidObject()){
            DBOperations dbOperations = new DBOperations(context);
            ContentValues contentValues = new ContentValues();
            // TODO: 12/4/2016 Get content values of object via function.

            dbOperations.updateObject(CycleContract.CycleEntry.TABLE_NAME, contentValues, CycleContract.CycleEntry.CROPCYCLE_CROPID, cycle.getCropId());
        }
    }

    @Override
    public void getObjectFromDB() {
        ContentValues cv = new ContentValues();
        DBOperations dbOperations = new DBOperations(context);
        Cursor receivedData = dbOperations.getObject(CycleContract.CycleEntry.TABLE_NAME, "Fred", 24);
        if(receivedData.getCount()<1){
            cycle.setCropId(-1);
        }
        else{
            // TODO: 12/4/2016 Take cursor data and populate cycle object.

        }
    }

    @Override
    public List getAllObjects() {
        List list = new ArrayList();
        DBOperations dbOperations = new DBOperations(context);
        Cursor allObjects = dbOperations.getAllObjects(CycleContract.CycleEntry.TABLE_NAME);
        allObjects.moveToFirst();
        if(allObjects.getCount()>0){
            while(allObjects.moveToNext()){
                // TODO: 12/4/2016 Build list of objects from cursor and store to ArrayList of Cycles.
            }
        }
        return list;
    }
}
