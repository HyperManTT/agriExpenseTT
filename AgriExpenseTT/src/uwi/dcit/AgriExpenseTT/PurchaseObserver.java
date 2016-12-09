package uwi.dcit.AgriExpenseTT;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import uwi.dcit.AgriExpenseTT.cloud.CloudInterface;
import uwi.dcit.AgriExpenseTT.helpers.DataManager;
import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.AgriExpenseTT.helpers.DbQuery;
import uwi.dcit.AgriExpenseTT.helpers.TransactionLog;
import uwi.dcit.AgriExpenseTT.models.CycleContract;
import uwi.dcit.AgriExpenseTT.models.ResourcePurchaseContract;
import uwi.dcit.agriexpensesvr.upAccApi.model.UpAcc;

/**
 * Created by mdls8 on 12/2/2016.
 */

public class PurchaseObserver extends TransLogObserver {

    private DbHelper dbh;
    private SQLiteDatabase db;

    public PurchaseObserver(DataManager dm){
        // when instance of this is created, it is immediately attached as an observer to the subject (DataManager)
        this.dm = dm;
        this.dm.attach(this);
    }

    @Override
    public void update() {
        Log.v("Purchase Observer", "Notified");

        // get the current state of the Subject
        CloudModState state = dm.getState();

        if(state.getTableName().equals(ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME)) {
            // the state change is related to this observer

            dbh= DbHelper.getInstance(dm.getContext());
            db = dbh.getWritableDatabase();

            UpAcc acc = DbQuery.getUpAcc(db);

            if(acc != null){
                Log.v("acc check", "not null");
                if(DbQuery.insertRedoLog(db, dbh, state.getTableName(), state.getId(), state.getOperation()) != -1)
                    Log.v("Purchase Observer", "Purchase record inserted into redo log");

                // for testing to simulate that the cloud is online
                acc.setSignedIn(1);

                if(acc.getSignedIn() == 1){
                    Log.v("Signed in?", "YES");

                    // determine which CRUD operation is necessary and execute the required update to the cloud
                    switch(state.getOperation()){
                        case TransactionLog.TL_UPDATE:
                            updatePurchase();
                            break;
                        case TransactionLog.TL_INS:
                            insertPurchase();
                            break;
                        case TransactionLog.TL_DEL:
                            deletePurchase();
                            break;
                    }
                }
            }
        }
    }

    private void updatePurchase(){
        Log.v("Cloud update", "Updating Purchase");
        CloudInterface cloud= new CloudInterface(dm.getContext(),db,dbh);// new CloudInterface(context);
        //cloud.updatePurchase();
    }

    private void insertPurchase(){
        Log.v("Cloud update", "Inserting Purchase");
        CloudInterface c= new CloudInterface(dm.getContext(),db,dbh);//new CloudInterface(context);
        //c.insertPurchase();
    }

    private void deletePurchase(){
        Log.v("Cloud update", "Deleting Purchase");
        CloudInterface c= new CloudInterface(dm.getContext(),db,dbh);//new CloudInterface(context);
        //c.deletePurchase();
    }
}