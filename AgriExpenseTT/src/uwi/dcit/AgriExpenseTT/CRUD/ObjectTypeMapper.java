package uwi.dcit.AgriExpenseTT.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;


/**
 * Created by shivr on 12/4/2016.
 * Abstractions were done on the insertion, deletion and updating of objects with the use of this class.
 */

public abstract class ObjectTypeMapper {

    protected Context context;
    protected String tableName;
    protected  String idFieldName;

    protected SQLiteDatabase db;
    protected DbHelper dbh;

    public ObjectTypeMapper(Context context, String tableName, String idFieldName, SQLiteDatabase db, DbHelper dbh){
        this.context = context;
        this.tableName = tableName;
        this.idFieldName = idFieldName;
        this.dbh= dbh;
        this.db = db;
    }

    public int insertObject(ObjectMapper objectTypeMapper){
        ContentValues cv = objectTypeMapper.getContentValues();
        DBOperations dbOperations = new DBOperations(db);
        int rowId = dbOperations.insertObject(cv, tableName);
        return rowId;
    }

    public void updateObject(ObjectMapper objectMapper) {
        if(objectMapper.isValidObject()){
            DBOperations dbOperations = new DBOperations(db);
            ContentValues contentValues = objectMapper.getContentValues();
            dbOperations.updateObject(tableName, contentValues, idFieldName, objectMapper.getId());
        }
    }

    public void deleteObject(int id) {
        DBOperations dbOperations = new DBOperations(db);
        dbOperations.deleteObject(tableName, idFieldName, id);
    }

    public abstract ObjectMapper getObjectFromDB(int id);

    public abstract List getAllObjectsFromDB();

}
