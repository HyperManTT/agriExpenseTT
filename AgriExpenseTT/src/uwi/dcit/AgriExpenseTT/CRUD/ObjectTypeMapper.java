package uwi.dcit.AgriExpenseTT.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.util.List;

import uwi.dcit.AgriExpenseTT.CRUD.Cycle.CycleContract;

/**
 * Created by shivr on 12/4/2016.
 */

public abstract class ObjectTypeMapper {

    protected Context context;
    protected String tableName;

    public ObjectTypeMapper(Context context, String tableName){
        this.context = context;
        this.tableName = tableName;
    }

    public int insertObject(ObjectMapper objectTypeMapper){
        Log.i("WOOOO","INSERTING!!!");
        ContentValues cv = objectTypeMapper.getContentValues();
        DBOperations dbOperations = new DBOperations(context);
        int rowId = dbOperations.insertObject(cv, tableName);
        return rowId;
    }

    public void updateObject(ObjectMapper objectMapper) {
        if(objectMapper.isValidObject()){
            DBOperations dbOperations = new DBOperations(context);
            ContentValues contentValues = objectMapper.getContentValues();
            dbOperations.updateObject(tableName, contentValues, CycleContract.CycleEntry.CROPCYCLE_CROPID, objectMapper.getId());
        }
    }

    public void deleteObject(int id, String IDFieldName) {
        DBOperations dbOperations = new DBOperations(context);
        dbOperations.deleteObject(tableName, IDFieldName, id);
    }

    public abstract void getObjectFromDB(int id);

    public abstract List getAllObjectsFromDB();

}
