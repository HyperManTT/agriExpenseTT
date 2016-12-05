package uwi.dcit.AgriExpenseTT.TransactionLogOperations;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.AgriExpenseTT.models.CycleContract;
import uwi.dcit.agriexpensesvr.cycleApi.model.Cycle;
import uwi.dcit.agriexpensesvr.translogApi.model.TransLog;

/**
 * Created by ander on 12/2/2016.
 */
public class CycleItem extends Application implements Item {

    private DbHelper dbh;
    private SQLiteDatabase db;

    public CycleItem(DbHelper dbh, SQLiteDatabase db){
        this.dbh = dbh;
        this.db = db;
    }

    /* this function inserts a cycle fetched from the end point to the local database. */
    public void insert(TransLog log, String namespace){

        Cycle c = getCycle(namespace, log.getKeyrep());

        ContentValues cv = new ContentValues();

        cv.put(CycleContract.CycleEntry._ID, log.getRowId());
        cv.put(CycleContract.CycleEntry.CROPCYCLE_CROPID, c.getCropId());
        cv.put(CycleContract.CycleEntry.CROPCYCLE_LAND_TYPE, c.getLandType());
        cv.put(CycleContract.CycleEntry.CROPCYCLE_LAND_AMOUNT, c.getLandQty());
        cv.put(CycleContract.CycleEntry.CROPCYCLE_TOTALSPENT, c.getTotalSpent());
        cv.put(CycleContract.CycleEntry.CROPCYCLE_HARVEST_TYPE, c.getHarvestType());
        cv.put(CycleContract.CycleEntry.CROPCYCLE_HARVEST_AMT, c.getHarvestAmt());
        cv.put(CycleContract.CycleEntry.CROPCYCLE_COSTPER, c.getCostPer());

        db.insert(CycleContract.CycleEntry.TABLE_NAME, null, cv);
        Log.i("DESIGN UPDATE 1", "INSERTED INTO CYCLE TABLE.");
    }

    /* this function updates a cycle fetched from the end point in the local database. */
    public void update(TransLog log, String namespace){

        Cycle c=getCycle(namespace,log.getKeyrep());

        ContentValues cv = new ContentValues();

        cv.put(CycleContract.CycleEntry._ID, log.getRowId());
        cv.put(CycleContract.CycleEntry.CROPCYCLE_CROPID, c.getCropId());
        cv.put(CycleContract.CycleEntry.CROPCYCLE_LAND_TYPE, c.getLandType());
        cv.put(CycleContract.CycleEntry.CROPCYCLE_LAND_AMOUNT, c.getLandQty());
        cv.put(CycleContract.CycleEntry.CROPCYCLE_TOTALSPENT, c.getTotalSpent());
        cv.put(CycleContract.CycleEntry.CROPCYCLE_HARVEST_TYPE, c.getHarvestType());
        cv.put(CycleContract.CycleEntry.CROPCYCLE_HARVEST_AMT, c.getHarvestAmt());
        cv.put(CycleContract.CycleEntry.CROPCYCLE_COSTPER, c.getCostPer());

        db.update(CycleContract.CycleEntry.TABLE_NAME, cv, CycleContract.CycleEntry._ID+"="+log.getRowId(), null);

        Log.i("DESIGN UPDATE 1", "UPDATED ENTRY INTO CYCLE TABLE.");
    }

    /* delete function not implemented since transaction log delete function is already decoupled. */
    public void delete(TransLog log, String namespace){

    }

    /* this function fetches a cycle from the endpoint, demo code used from demonstration. */
    public Cycle getCycle(String namespace, String keyrep){
        /*
        CycleApi.Builder builder = new CycleApi.Builder(
                AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
                null);
        builder = CloudEndpointUtils.updateBuilder(builder);
        CycleApi endpoint = builder.build();
        Cycle c = null;
        try {
            c=endpoint.getCycle(namespace,keyrep).execute();
        } catch (IOException e) {e.printStackTrace();}
        return c;
        */

        return new CloudDemo().getCycle(namespace, keyrep);
    }

    /* this function uploads all local cycle to the cloud for a particular user. */
    public boolean createCloud(String namespace){

        /* this block of code connects to the endpoint, reads the local database and send the data to the cloud. */

        /*
        DbHelper dbh = new DbHelper();
        SQLiteDatabase db = dbh.getWritableDatabase();

        CycleApi.Builder builder = new CycleApi.Builder(
                AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
                null);

        builder = CloudEndpointUtils.updateBuilder(builder);

        CycleApi endpointCyc = builder.build();


        String code="select * from "+ CycleContract.CycleEntry.TABLE_NAME;

        Cursor cursor = db.rawQuery(code, null);

        while(cursor.moveToNext()){
            Cycle c=new Cycle();
            c.setId(cursor.getInt(cursor.getColumnIndex(CycleContract.CycleEntry._ID)));
            c.setCropId(cursor.getInt(cursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_CROPID)));
            c.setLandQty(cursor.getDouble(cursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_LAND_AMOUNT)));
            c.setLandType(cursor.getString(cursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_LAND_TYPE)));
            c.setTotalSpent(cursor.getDouble(cursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_TOTALSPENT)));
            c.setHarvestType(cursor.getString(cursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_HARVEST_TYPE)));
            c.setHarvestAmt(cursor.getDouble(cursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_HARVEST_AMT)));
            c.setCostPer(cursor.getDouble(cursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_COSTPER)));
            c.setAccount(namespace);
            try {
                c=endpointCyc.insertCycle(c).execute();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            DbQuery.insertCloudKey(db, dbh, CycleContract.CycleEntry.TABLE_NAME, c.getKeyrep(), c.getId());
        }

        cursor.close();
        */

        Log.i("DESIGN UPDATE 1", "CREATING CLOUD, UPLOADING CYCLES TO THE CLOUD.");

        return true;
    }

    /* this function removes all cycle entries in the cloud related to a particular account. */
    public void removeNamespace(String namespace){

        /* this block of code, connects to the cycle endpoint, and deletes all entries related to a particular account. */

        /*
        CycleApi.Builder builder = new CycleApi.Builder(
                AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
                null);
        builder = CloudEndpointUtils.updateBuilder(builder);
        CycleApi endpoint = builder.build();
        try {
            endpoint.deleteAll(namespace).execute();
        } catch (IOException e) {

            e.printStackTrace();
        }
        */

        Log.i("DESIGN UPDATE 1", "REMOVING ENTRIES IN CLOUD FOR CYCLES.");
    }


}
