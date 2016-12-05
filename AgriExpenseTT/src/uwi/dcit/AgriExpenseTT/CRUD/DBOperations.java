package uwi.dcit.AgriExpenseTT.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;

/**
 * Created by shivr on 12/1/2016.
 */

public class DBOperations {

    private SQLiteDatabase db;
    private DbHelper dbh;
    private Context context;

    public DBOperations(Context context){
        dbh= new DbHelper(context);
        db = dbh.getWritableDatabase();
        this.context = context;
    }

    public int insertObject(ContentValues cv, String tableName){
        Long rowId = db.insert(tableName, null, cv);
        db.close();
        return rowId.intValue();
    }

    public void updateObject(String tableName, ContentValues contentValues, String fieldName, int id){
        db.update(tableName, contentValues, fieldName+"="+id, null);
        db.close();
    }

    public void deleteObject(String tableName, String fieldName, int id){
        db.delete(tableName, fieldName+"="+id, null);
        db.close();
    }

    //The following two methods are not bound to the object itself in the respective classes.

    public Cursor getObject(String tableName, String fieldName, int id){
        Cursor cursor = db.rawQuery("select * from "+tableName+" where "+fieldName+"="+id+";", null);
        db.close();
        return cursor;
    }

    public Cursor getAllObjects(String tableName){
        List list = new ArrayList();
        Cursor cursor =db.rawQuery("select * from "+tableName, null);
        return cursor;
    }
}
