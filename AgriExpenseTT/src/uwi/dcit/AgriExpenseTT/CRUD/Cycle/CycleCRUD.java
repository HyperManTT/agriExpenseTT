package uwi.dcit.AgriExpenseTT.CRUD.Cycle;

import android.content.ContentValues;
import android.content.Context;

import uwi.dcit.AgriExpenseTT.CRUD.DBCRUD;
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
        DBCRUD dbcrud = new DBCRUD(context);
        //Map Cycle Object's Properties to a context value.

        dbcrud.insertObject(cv,CycleContract.CycleEntry.TABLE_NAME);
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void getAll() {

    }

    @Override
    public void getByID() {

    }
}
