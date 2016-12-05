package uwi.dcit.AgriExpenseTT.TransactionLogOperations;

import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.AgriExpenseTT.models.CycleContract;
import uwi.dcit.AgriExpenseTT.models.CycleResourceContract;
import uwi.dcit.AgriExpenseTT.models.ResourcePurchaseContract;

/**
 * Created by ander on 12/2/2016.
 */
public class ItemFactory {

    private static Map<String, Item> items = new HashMap<>();
    private DbHelper dbh;
    private SQLiteDatabase db;

    public ItemFactory(DbHelper dbh, SQLiteDatabase db){

        this.dbh = dbh;
        this.db = db;

        items.put(CycleContract.CycleEntry.TABLE_NAME, new CycleItem(dbh, db));
        items.put(ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, new ResourcePurchaseItem(dbh, db));
        items.put(CycleResourceContract.CycleResourceEntry.TABLE_NAME, new CycleResourceItem(dbh, db));
    }



    public Map<String, Item> getItemList(){
        return items;
    }

    /* this function returns an appropriate instance of the class needed. */
    public Item GetItem(String itemType){
        if(itemType == null){
            return null;
        }

        return items.get(itemType);

    }
}
