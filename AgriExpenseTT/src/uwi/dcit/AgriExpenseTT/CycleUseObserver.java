package uwi.dcit.AgriExpenseTT;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import uwi.dcit.AgriExpenseTT.cloud.CloudInterface;
import uwi.dcit.AgriExpenseTT.helpers.DataManager;
import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.AgriExpenseTT.helpers.DbQuery;
import uwi.dcit.AgriExpenseTT.helpers.TransactionLog;
import uwi.dcit.AgriExpenseTT.models.CycleContract;
import uwi.dcit.AgriExpenseTT.models.CycleResourceContract;
import uwi.dcit.AgriExpenseTT.models.ResourceContract;
import uwi.dcit.agriexpensesvr.upAccApi.model.UpAcc;

/**
 * Created by mdls8 on 12/2/2016.
 */

public class CycleUseObserver extends TransLogObserver {

    private DbHelper dbh;
    private SQLiteDatabase db;

    public CycleUseObserver(DataManager dm){
        // when instance of this is created, it is immediately attached as an observer to the subject (DataManager)
        this.dm = dm;
        this.dm.attach(this);
    }

    @Override
    public void update() {
        Log.v("CycleUse Observer", "Notified");

        // get the current state of the Subject
        CloudModState state = dm.getState();

        if(state.getTableName().equals(CycleResourceContract.CycleResourceEntry.TABLE_NAME)) {
            // the state change is related to this observer

            dbh= DbHelper.getInstance(dm.getContext());
            db = dbh.getWritableDatabase();

            UpAcc acc = DbQuery.getUpAcc(db);

            if (acc != null) {
                Log.v("acc check", "not null");
                if (DbQuery.insertRedoLog(db, dbh, state.getTableName(), state.getId(), state.getOperation()) != -1)
                    Log.v("Cycle-use Observer", "Cycle-use record inserted into redo log");

                // for testing to simulate that the cloud is online
                acc.setSignedIn(1);

                if (acc.getSignedIn() == 1) {
                    Log.v("Signed in?", "YES");

                    // determine which CRUD operation is necessary and execute the required update to the cloud
                    switch (state.getOperation()) {
                        case TransactionLog.TL_UPDATE:
                            updateCycleUse();
                            break;
                        case TransactionLog.TL_INS:
                            insertCycleUse();
                            break;
                        case TransactionLog.TL_DEL:
                            deleteCycleUse();
                            break;
                    }
                }
            }
        }
    }

    private void insertCycleUse(){
        Log.v("Cloud update", "Inserting Cycle-Use");
        CloudInterface c= new CloudInterface(dm.getContext(),db,dbh);//new CloudInterface(context);
        //c.insertCycleUseC();
    }

    private void deleteCycleUse(){
        Log.v("Cloud update", "Deleting Cycle-Use");
        CloudInterface c= new CloudInterface(dm.getContext(),db,dbh);//new CloudInterface(context);
        //c.deleteCycleUse();
    }

    private void updateCycleUse(){
        Log.v("Cloud update", "Updating Cycle-Use");
    }
}
