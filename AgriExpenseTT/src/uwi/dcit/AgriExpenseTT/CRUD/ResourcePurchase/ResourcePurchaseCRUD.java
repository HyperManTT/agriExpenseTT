package uwi.dcit.AgriExpenseTT.CRUD.ResourcePurchase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import uwi.dcit.AgriExpenseTT.CRUD.DBOperations;
import uwi.dcit.AgriExpenseTT.CRUD.ObjectTypeMapper;

/**
 * Created by shivr on 12/4/2016.
 */

public class ResourcePurchaseCRUD extends ObjectTypeMapper{

    public ResourcePurchaseCRUD(Context context){
        super(context, ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, ResourcePurchaseContract.ResourcePurchaseEntry._ID);
    }

    @Override
    public ResourcePurchase getObjectFromDB(int id) {
        ResourcePurchase resourcePurchase = new ResourcePurchase();
        DBOperations dbOperations = new DBOperations(context);
        Cursor receivedData = dbOperations.getObject(tableName, idFieldName, id);
        if(receivedData.getCount()<1){
            resourcePurchase.setId(-1);
        }
        else{
            resourcePurchase.setCursorValues(receivedData);
        }
        return resourcePurchase;
    }

    @Override
    public List getAllObjectsFromDB() {
        List<ResourcePurchase> list = new ArrayList();
        DBOperations dbOperations = new DBOperations(context);
        Cursor allObjectsCursor = dbOperations.getAllObjects(tableName);
        allObjectsCursor.moveToFirst();
        if(allObjectsCursor.getCount()>0){
            while(allObjectsCursor.moveToNext()){
                ResourcePurchase resourcePurchase = new ResourcePurchase();
                resourcePurchase.setCursorValues(allObjectsCursor);
                list.add(resourcePurchase);
            }
        }
        return list;
    }

    public String getResourceFromID(int id){
        DBOperations dbOperations = new DBOperations(context);
        return dbOperations.findResourceName(id);
    }
}
