package uwi.dcit.AgriExpenseTT.TransactionLogOperations;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.AgriExpenseTT.models.ResourcePurchaseContract;
import uwi.dcit.agriexpensesvr.rPurchaseApi.model.RPurchase;
import uwi.dcit.agriexpensesvr.translogApi.model.TransLog;

/**
 * Created by ander on 12/3/2016.
 */
public class ResourcePurchaseItem implements Item {

    private DbHelper dbh;
    private SQLiteDatabase db;

    public ResourcePurchaseItem(DbHelper dbh, SQLiteDatabase db){
        this.dbh = dbh;
        this.db = db;
    }

    /* this function inserts a purchase fetched from the end point to the local database. */
    public void insert(TransLog log, String namespace){

        RPurchase p = getPurchase(namespace, log.getKeyrep());

        ContentValues cv = new ContentValues();

        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry._ID, log.getRowId());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_TYPE, p.getType());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_RESID, p.getResourceId());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QTY, p.getQty());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QUANTIFIER, p.getQuantifier());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_COST, p.getCost());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_REMAINING, p.getQtyRemaining());

        db.insert(ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, null, cv);
        Log.i("DESIGN UPDATE 1", "INSERTED INTO RESOURCE PURCHASE TABLE.");
    }

    /* this function updates a purchase fetched from the end point in the local database. */
    public void update(TransLog log, String namespace){

        RPurchase p=getPurchase(namespace,log.getKeyrep());

        ContentValues cv = new ContentValues();

        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry._ID, log.getRowId());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_TYPE, p.getType());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_RESID, p.getResourceId());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QTY, p.getQty());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QUANTIFIER, p.getQuantifier());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_COST, p.getCost());
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_REMAINING, p.getQtyRemaining());

        db.update(ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, cv, ResourcePurchaseContract.ResourcePurchaseEntry._ID+"="+log.getRowId(), null);
        Log.i("DESIGN UPDATE 1", "UPDATED ENTRY IN THE RESOURCE PURCHASE TABLE");

    }

    /* delete function not implemented since transaction log delete function is already decoupled. */
    public void delete(TransLog log, String namespace){

    }

    /* this function fetches a purchase from the endpoint, demo code used from demonstration. */
    public RPurchase getPurchase(String namespace, String keyrep){

        /*
        RPurchaseApi.Builder builder = new RPurchaseApi.Builder(
                AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
                null);
        builder = CloudEndpointUtils.updateBuilder(builder);
        RPurchaseApi endpoint = builder.build();
        RPurchase p = null;
        try {
            p=endpoint.getRPurchase(namespace, keyrep).execute();
        } catch (IOException e) {e.printStackTrace();}
        return p;
        */

        return new CloudDemo().getPurchase(namespace, keyrep);
    }

    /* this function uploads all local purchases to the cloud for a particular user. */
    public boolean createCloud(String namespace){

        /* this block of code connects to the endpoint, reads the local database and send the data to the cloud. */

        /*
        RPurchaseApi.Builder builderPurchase = new RPurchaseApi.Builder(
                AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
                null);
        builderPurchase = CloudEndpointUtils.updateBuilder(builderPurchase);
        RPurchaseApi endpointPurchase = builderPurchase.build();


        String code="select * from "+ ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(code, null);
        while(cursor.moveToNext()){
            RPurchase p=new RPurchase();
            p.setPId(cursor.getInt(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry._ID)));
            p.setResourceId(cursor.getInt(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_RESID)));
            p.setType(cursor.getString(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_TYPE)));
            p.setQuantifier(cursor.getString(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QUANTIFIER)));
            p.setQty(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QTY)));
            p.setCost(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_COST)));
            p.setQtyRemaining(cursor.getDouble(cursor.getColumnIndex( ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_REMAINING)));
            p.setAccount(namespace);
            try {
                p=endpointPurchase.insertRPurchase(p).execute();
            } catch (IOException e) {e.printStackTrace();
                return false;}
            DbQuery.insertCloudKey(db, dbh, ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME, p.getKeyrep(), p.getPId());
        }
        cursor.close();
        */

        /* DEMO CODE TO SHOW WHAT WILL BE PUSHED TO CLOUD. */
        Log.i("DESIGN UPDATE 1", "CREATING CLOUD, UPLOADING PURCHASES TO THE CLOUD.");

        Cursor rows = db.rawQuery("SELECT * FROM resPurchases", null);
        String tableString = "";
        if (rows.moveToFirst() ){
            String[] columnNames = rows.getColumnNames();
            do {
                for (String name: columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            rows.getString(rows.getColumnIndex(name)));
                }
                tableString += "\n";

            } while (rows.moveToNext());
        }
        Log.d("DESIGN UPDATE 1"," " + tableString);

        return true;
    }

    /* this function removes all purchase entries in the cloud related to a particular account.*/
    public void removeNamespace(String namespace){

        /* this block of code, connects to the resource purchase endpoint, and deletes all entries related to a particular account */

        /*
        RPurchaseApi.Builder builderPurchase = new RPurchaseApi.Builder(
                AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
                null);
        builderPurchase = CloudEndpointUtils.updateBuilder(builderPurchase);
        RPurchaseApi endpointPurchase = builderPurchase.build();
        try {
            endpointPurchase.deleteAll(namespace).execute();
        } catch (IOException e) {

            e.printStackTrace();
        }
        */

        Log.i("DESIGN UPDATE 1", "REMOVING ENTRIES IN CLOUD FOR RESOURCE PURCHASES.");
    }
}
