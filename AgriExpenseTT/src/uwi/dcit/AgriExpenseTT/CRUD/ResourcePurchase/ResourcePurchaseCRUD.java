package uwi.dcit.AgriExpenseTT.CRUD.ResourcePurchase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import uwi.dcit.AgriExpenseTT.CRUD.DBOperations;
import uwi.dcit.AgriExpenseTT.CRUD.ObjectTypeMapper;
import uwi.dcit.AgriExpenseTT.helpers.DbHelper;

/**
 * Created by shivr on 12/4/2016.
 */

public class ResourcePurchaseCRUD extends ObjectTypeMapper{

    public ResourcePurchaseCRUD(Context context, SQLiteDatabase db, DbHelper dbh){
        super(context, ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, ResourcePurchaseContract.ResourcePurchaseEntry._ID, db, dbh);
    }

    @Override
    public ResourcePurchase getObjectFromDB(int id) {
        ResourcePurchase resourcePurchase = new ResourcePurchase();
        DBOperations dbOperations = new DBOperations(db);
        Cursor receivedData = dbOperations.getObject(tableName, idFieldName, id);
        if(receivedData.getCount()<1){
            resourcePurchase.setId(-1);
        }
        else{
            receivedData.moveToFirst();
            resourcePurchase.setCursorValues(receivedData);
        }
        receivedData.close();
        return resourcePurchase;
    }

    @Override
    public ArrayList getAllObjectsFromDB() {
        ArrayList<ResourcePurchase> list = new ArrayList();
        DBOperations dbOperations = new DBOperations(db);
        Cursor allObjectsCursor = dbOperations.getAllObjects(tableName);
        allObjectsCursor.moveToFirst();
        if(allObjectsCursor.getCount()>0){
            while(allObjectsCursor.moveToNext()){
                ResourcePurchase resourcePurchase = new ResourcePurchase();
                resourcePurchase.setCursorValues(allObjectsCursor);
                list.add(resourcePurchase);
                allObjectsCursor.moveToNext();
            }
        }
        allObjectsCursor.close();
        return list;
    }
}
