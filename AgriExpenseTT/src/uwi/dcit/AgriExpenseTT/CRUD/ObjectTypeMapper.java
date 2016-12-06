package uwi.dcit.AgriExpenseTT.CRUD;

import android.content.ContentValues;
import android.content.Context;
import java.util.List;


/**
 * Created by shivr on 12/4/2016.
 */

public abstract class ObjectTypeMapper {

    protected Context context;
    protected String tableName;
    protected  String idFieldName;

    public ObjectTypeMapper(Context context, String tableName, String idFieldName){
        this.context = context;
        this.tableName = tableName;
        this.idFieldName = idFieldName;
    }

    public int insertObject(ObjectMapper objectTypeMapper){
        ContentValues cv = objectTypeMapper.getContentValues();
        DBOperations dbOperations = new DBOperations(context);
        int rowId = dbOperations.insertObject(cv, tableName);
        return rowId;
    }

    public void updateObject(ObjectMapper objectMapper) {
        if(objectMapper.isValidObject()){
            DBOperations dbOperations = new DBOperations(context);
            ContentValues contentValues = objectMapper.getContentValues();
            dbOperations.updateObject(tableName, contentValues, idFieldName, objectMapper.getId());
        }
    }

    public void deleteObject(int id) {
        DBOperations dbOperations = new DBOperations(context);
        dbOperations.deleteObject(tableName, idFieldName, id);
    }

    public abstract ObjectMapper getObjectFromDB(int id);

    public abstract List getAllObjectsFromDB();

}
