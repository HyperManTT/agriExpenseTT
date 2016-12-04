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

public class CycleCRUD implements ObjectCRUD {

    private Cycle cycle;
    private Context context;


    public CycleCRUD(Cycle cycle, Context context){
        this.cycle = cycle;
        this.context = context;
    }


    @Override
    public void insert() {
        ContentValues cv = new ContentValues();
        DBOperations dbcrud = new DBOperations(context);
        //Map Cycle Object's Properties to a context value.

        dbcrud.insertObject(cv,CycleContract.CycleEntry.TABLE_NAME);
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {
        if(cycle.isValidObject()){
            DBOperations dbcrud = new DBOperations(context);
            dbcrud.deleteObject(CycleContract.CycleEntry.TABLE_NAME, CycleContract.CycleEntry.CROPCYCLE_CROPID, cycle.getCropId());
        }
    }

    @Override
    public void getObject() {
        ContentValues cv = new ContentValues();
        DBOperations dbcrud = new DBOperations(context);
        Cursor receivedData = dbcrud.getObject(CycleContract.CycleEntry.TABLE_NAME, "Fred", 24);
        if(receivedData.getCount()<1){
            cycle.setCropId(-1);
        }
        else{
            //Populate entire object with the content values!
        }
    }

    @Override
    public List getAllObjects() {
        List list = new ArrayList();
        DBOperations dbcrud = new DBOperations(context);
        Cursor allObjects = dbcrud.getAllObjects(CycleContract.CycleEntry.TABLE_NAME);
        //Build List From Cursor.
        return list;
    }
}
