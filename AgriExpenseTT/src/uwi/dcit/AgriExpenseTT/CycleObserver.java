package uwi.dcit.AgriExpenseTT;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import uwi.dcit.AgriExpenseTT.cloud.CloudInterface;
import uwi.dcit.AgriExpenseTT.helpers.DataManager;
import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.AgriExpenseTT.helpers.DbQuery;
import uwi.dcit.AgriExpenseTT.helpers.TransactionLog;
import uwi.dcit.AgriExpenseTT.models.CycleContract;
import uwi.dcit.agriexpensesvr.upAccApi.model.UpAcc;

/**
 * Created by mdls8 on 12/2/2016.
 */

public class CycleObserver extends TransLogObserver {

    private DbHelper dbh;
    private SQLiteDatabase db;

    public CycleObserver(DataManager dm){
        // when instance of this is created, it is immediately attached as an observer to the subject (DataManager)
        this.dm = dm;
        this.dm.attach(this);
    }

    @Override
    public void update() {
        Log.v("Cycle Observer", "Notified");

        // get the current state of the Subject
        CloudModState state = dm.getState();

        if(state.getTableName().equals(CycleContract.CycleEntry.TABLE_NAME)){
            // the state change is related to this observer

            dbh= DbHelper.getInstance(dm.getContext());
            db = dbh.getWritableDatabase();

            UpAcc acc = DbQuery.getUpAcc(db);

            if(acc != null){
                Log.v("acc check", "not null");
                if(DbQuery.insertRedoLog(db, dbh, state.getTableName(), state.getId(), state.getOperation()) != -1)
                    Log.v("Cycle Observer", "Cycle record inserted into redo log");

                // for testing to simulate that the cloud is online
                acc.setSignedIn(1);

                if(acc.getSignedIn() == 1){
                    Log.v("Signed in?", "YES");

                    // determine which CRUD operation is necessary and execute the required update to the cloud
                    switch(state.getOperation()){
                        case TransactionLog.TL_UPDATE:
                            updateCycle();
                            break;
                        case TransactionLog.TL_INS:
                            insertCycle();
                            break;
                        case TransactionLog.TL_DEL:
                            deleteCycle();
                            break;
                    }
                }
            }
        }
    }

    private void updateCycle(){
        Log.v("Cloud update", "Updating Cycle");
        CloudInterface cloud= new CloudInterface(dm.getContext(),db,dbh);// new CloudInterface(context);
        //cloud.updateCycle();
    }

    private void insertCycle(){
        Log.v("Cloud update", "Inserting Cycle");
        CloudInterface c= new CloudInterface(dm.getContext(),db,dbh);// new CloudInterface(context);
        //c.insertCycle();
    }

    private void deleteCycle(){
        Log.v("Cloud update", "Deleting Cycle");
        CloudInterface cloud= new CloudInterface(dm.getContext(),db,dbh);//new CloudInterface(context);
        //cloud.deleteCycle();
    }
}
