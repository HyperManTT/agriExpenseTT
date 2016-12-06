package uwi.dcit.AgriExpenseTT.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.AgriExpenseTT.models.ResourceContract;

/**
 * Created by shivr on 12/1/2016.
 * This class allows the purchase and cycle CRUD classes to interact with the database.
 */

public class DBOperations {

    protected SQLiteDatabase db;


    public DBOperations(SQLiteDatabase db){
        this.db = db;
    }

    public int insertObject(ContentValues cv, String tableName){
        Long rowId = db.insert(tableName, null, cv);
        return rowId.intValue();
    }

    public void updateObject(String tableName, ContentValues contentValues, String fieldName, int id){
        db.update(tableName, contentValues, fieldName+"="+id, null);
    }

    public void deleteObject(String tableName, String fieldName, int id){
        db.delete(tableName, fieldName+"="+id, null);
    }

    //The following two methods are not bound to the object itself in the respective classes.

    public Cursor getObject(String tableName, String fieldName, int id){
        Cursor cursor = db.rawQuery("select * from "+tableName+" where "+fieldName+"="+id+";", null);
        return cursor;
    }

    public Cursor getAllObjects(String tableName){
        List list = new ArrayList();
        Cursor cursor =db.rawQuery("select * from "+tableName, null);
        return cursor;
    }

    public String findResourceName(int id){
        String code="select name from "+ ResourceContract.ResourceEntry.TABLE_NAME+" where "+ ResourceContract.ResourceEntry._ID +"="+id+";";
        String res = null;
        Cursor cursor = db.rawQuery(code, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            res = cursor.getString(cursor.getColumnIndex("name"));
        }
        cursor.close();
        return res;
    }
}
