package uwi.dcit.AgriExpenseTT.CRUD;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.CRUD.Cycle.Cycle;
import uwi.dcit.AgriExpenseTT.CRUD.Cycle.CycleCRUD;
import uwi.dcit.AgriExpenseTT.CRUD.ResourcePurchase.ResourcePurchase;
import uwi.dcit.AgriExpenseTT.CRUD.ResourcePurchase.ResourcePurchaseCRUD;
import uwi.dcit.AgriExpenseTT.helpers.DbHelper;


/**
 * Created by shivr on 12/6/2016.
 */

public class CRUDManager {
    private ResourcePurchase resourcePurchase;
    private Cycle cycle;
    private CycleCRUD cycleCRUD;
    private ResourcePurchaseCRUD resourcePurchaseCRUD;
    private Context context;
    protected DbHelper dbh;
    protected SQLiteDatabase db;

    public CRUDManager(Context context){
        this.context = context;
        dbh= DbHelper.getInstance(context);
        db = dbh.getWritableDatabase();
        cycleCRUD = new CycleCRUD(context, db, dbh);
        resourcePurchaseCRUD = new ResourcePurchaseCRUD(context, db, dbh);
    }

    public int insertCycle(String landType, int resourceId, String harvestType, Double landAmount, Long date, String cycleName, int cropId){
        cycle = new Cycle(landType, getResourceName(resourceId), harvestType, landAmount, date, cycleName, cropId);
        return cycleCRUD.insertObject(cycle);
    }

    public int insertCycle(Cycle cycle){
        this.cycle = cycle;
        return cycleCRUD.insertObject(cycle);
    }

    public int insertResourcePurchase(int resId, Double quantity, String quantifier, String type, Double cost, Long date){
        resourcePurchase = new ResourcePurchase(resId, quantity, quantifier, type, cost, date, getResourceName(resId));
        return  resourcePurchaseCRUD.insertObject(resourcePurchase);
    }

    public int insertResourcePurchase(ResourcePurchase resourcePurchase){
        this.resourcePurchase = resourcePurchase;
        return  resourcePurchaseCRUD.insertObject(resourcePurchase);
    }

    public void updateCycle(Cycle cycle){
        cycleCRUD.updateObject(cycle);
    }

    public void updateResourcePurchase(ResourcePurchase resourcePurchase){
        resourcePurchaseCRUD.updateObject(resourcePurchase);
    }

    public void deleteCycle(Cycle cycle){
        cycleCRUD.deleteObject(cycle.getId());
    }

    public void deleteResourcePurchase(ResourcePurchase resourcePurchase){
        resourcePurchaseCRUD.deleteObject(resourcePurchase.getId());
    }

    public Cycle getCycle(int id){
        return cycleCRUD.getObjectFromDB(id);
    }

    public ResourcePurchase getResourcePurchase(int id){
        return resourcePurchaseCRUD.getObjectFromDB(id);
    }

    public ArrayList getAllCycles(){
        return cycleCRUD.getAllObjectsFromDB();
    }

    public ArrayList getAllPurchases(){
        return resourcePurchaseCRUD.getAllObjectsFromDB();
    }

    public int getResourceID(String name){
        return new DBOperations(db).getNameResourceId(name);
    }

    public String getResourceName(int id){
        return new DBOperations(db).findResourceName(id);
    }
}
