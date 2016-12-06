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
        this.dm = dm;
        this.dm.attach(this);
    }

    @Override
    public void update() {
        Log.v("Purchase Observer", "Notified");

        CloudModState state = dm.getState();

        if(state.getTableName().equals(ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME)) {

            //dbh= new DbHelper(dm.getContext());
            dbh= DbHelper.getInstance(dm.getContext());
            db = dbh.getWritableDatabase();

            UpAcc acc = DbQuery.getUpAcc(db);

            if(acc != null){
                Log.v("acc check", "not null");
                if(DbQuery.insertRedoLog(db, dbh, state.getTableName(), state.getId(), state.getOperation()) != -1)
                    Log.v("Redo log insert", "purchase record");

                if(acc.getSignedIn() == 1){
                    Log.v("Signed in?", "YES");

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
        CloudInterface cloud= new CloudInterface(dm.getContext(),db,dbh);// new CloudInterface(context);
        cloud.updatePurchase();
    }

    private void insertPurchase(){
        CloudInterface c= new CloudInterface(dm.getContext(),db,dbh);//new CloudInterface(context);
        c.insertPurchase();
    }

    private void deletePurchase(){
        CloudInterface c= new CloudInterface(dm.getContext(),db,dbh);//new CloudInterface(context);
        c.deletePurchase();
    }
}