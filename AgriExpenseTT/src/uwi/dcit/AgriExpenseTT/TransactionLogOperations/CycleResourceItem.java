package uwi.dcit.AgriExpenseTT.TransactionLogOperations;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.AgriExpenseTT.models.CycleResourceContract;
import uwi.dcit.AgriExpenseTT.models.CycleResourceContract.CycleResourceEntry;
import uwi.dcit.agriexpensesvr.cycleUseApi.model.CycleUse;
import uwi.dcit.agriexpensesvr.translogApi.model.TransLog;

/**
 * Created by ander on 12/4/2016.
 */
public class CycleResourceItem implements Item{

    private DbHelper dbh;
    private SQLiteDatabase db;

    public CycleResourceItem(DbHelper dbh, SQLiteDatabase db){
        this.dbh = dbh;
        this.db = db;
    }

    /* this function inserts a cycle use fetched from the end point to the local database. */
    public void insert(TransLog log, String namespace){

        CycleUse u =getCycleUse(namespace, log.getKeyrep());

        ContentValues cv = new ContentValues();

        cv.put(CycleResourceEntry._ID, log.getRowId());
        cv.put(CycleResourceEntry.CYCLE_RESOURCE_CYCLEID, u.getCycleid());
        cv.put(CycleResourceEntry.CYCLE_RESOURCE_PURCHASE_ID, u.getPurchaseId());
        cv.put(CycleResourceEntry.CYCLE_RESOURCE_QTY, u.getAmount());
        cv.put(CycleResourceEntry.CYCLE_RESOURCE_USECOST, u.getCost());

        db.insert(CycleResourceContract.CycleResourceEntry.TABLE_NAME, null, cv);
        Log.i("DESIGN UPDATE 1", "INSERTED INTO CYCLE RESOURCE TABLE.");
    }

    /* this function updates a cycle use fetched from the end point in the local database. */
    public void update(TransLog log, String namespace){

        CycleUse c= getCycleUse(namespace,log.getKeyrep());

        ContentValues cv = new ContentValues();

        cv.put(CycleResourceEntry._ID, log.getRowId());
        cv.put(CycleResourceEntry.CYCLE_RESOURCE_TYPE,c.getResource());
        cv.put(CycleResourceEntry.CYCLE_RESOURCE_CYCLEID, c.getCycleid());
        cv.put(CycleResourceEntry.CYCLE_RESOURCE_PURCHASE_ID, c.getPurchaseId());
        cv.put(CycleResourceEntry.CYCLE_RESOURCE_QTY, c.getAmount());
        cv.put(CycleResourceEntry.CYCLE_RESOURCE_USECOST, c.getCost());

        db.update(CycleResourceContract.CycleResourceEntry.TABLE_NAME, cv, CycleResourceContract.CycleResourceEntry._ID+"="+log.getRowId(), null);
        Log.i("DESIGN UPDATE 1", "UPDATED ENTRY INTO CYCLE RESOURCE TABLE.");
    }

    /* delete function not implemented since transaction log delete function is already decoupled. */
    public void delete(TransLog log, String namespace){

    }

    /* this function fetches a cycle use from the endpoint, demo code used from demonstration. */
    public CycleUse getCycleUse(String namespace, String keyrep){

        /*
        CycleUseApi.Builder builder = new CycleUseApi.Builder(
                AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
                null);
        builder = CloudEndpointUtils.updateBuilder(builder);
        CycleUseApi endpoint = builder.build();
        CycleUse c=null;
        try {
            c=endpoint.getCycleUse(namespace, keyrep).execute();
        } catch (IOException e) {e.printStackTrace();}
        return c;
        */

        return new CloudDemo().getCycleUse(namespace, keyrep);
    }

    /* this function uploads all local cycle uses to the cloud for a particular user. */
    public boolean createCloud(String namespace){

        /* this block of code connects to the endpoint, reads the local database and send the data to the cloud. */

        /*
		CycleUseApi.Builder builderUse = new CycleUseApi.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);
		builderUse = CloudEndpointUtils.updateBuilder(builderUse);
        CycleUseApi endpointUse = builderUse.build();

        String code="select * from "+ CycleResourceEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(code, null);
        while(cursor.moveToNext()){
            CycleUse c=new CycleUse();
            c.setId(cursor.getInt(cursor.getColumnIndex(CycleResourceEntry._ID)));
            c.setAmount(cursor.getDouble(cursor.getColumnIndex(CycleResourceEntry.CYCLE_RESOURCE_QTY)));
            c.setCycleid(cursor.getInt(cursor.getColumnIndex(CycleResourceEntry.CYCLE_RESOURCE_CYCLEID)));
            c.setPurchaseId(cursor.getInt(cursor.getColumnIndex(CycleResourceEntry.CYCLE_RESOURCE_PURCHASE_ID)));
            c.setResource(cursor.getString(cursor.getColumnIndex(CycleResourceEntry.CYCLE_RESOURCE_TYPE)));
            c.setCost(cursor.getDouble(cursor.getColumnIndex(CycleResourceEntry.CYCLE_RESOURCE_USECOST)));
            //c.setQuantifier(cursor.getString(cursor.getColumnIndex(DbHelper.CYCLE_RESOURCE_QUANTIFIER)));
            c.setAccount(namespace);
            try {
                c=endpointUse.insertCycleUse(c).execute();
            } catch (IOException e) {e.printStackTrace();
                return false;}
            DbQuery.insertCloudKey(db, dbh, CycleResourceEntry.TABLE_NAME, c.getKeyrep(), c.getId());
        }
        cursor.close();
        */

        /* CODE TO DEMO WHAT WILL BE PUSHED TO CLOUD. */
        Log.i("DESIGN UPDATE 1", "CREATING CLOUD, UPLOADING CYCLE RESOURCES TO THE CLOUD.");

        Cursor rows = db.rawQuery("SELECT * FROM cycleResources", null);
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


    /* this function removes all cycle resource entries in the cloud related to a particular account. */
    public void removeNamespace(String namespace){

        /* this block of code, connects to the cycle use endpoint, and deletes all entries related to a particular account. */

        /*
        CycleUseApi.Builder builderUse = new CycleUseApi.Builder(
                AndroidHttp.newCompatibleTransport(), new JacksonFactory(), null);
        builderUse = CloudEndpointUtils.updateBuilder(builderUse);
        CycleUseApi endpointUse = builderUse.build();
        try {

            endpointUse.deleteAll(namespace).execute();
        } catch (IOException e) {

            e.printStackTrace();
        }
        */

        Log.i("DESIGN UPDATE 1", "REMOVING ENTRIES IN CLOUD FOR CYCLE RESOURCES.");
    }
}
