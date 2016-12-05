package uwi.dcit.AgriExpenseTT;

import android.database.sqlite.SQLiteDatabase;

import uwi.dcit.AgriExpenseTT.helpers.DataManager;
import uwi.dcit.AgriExpenseTT.helpers.DbHelper;

/**
 * Created by mdls8 on 12/2/2016.
 */

public abstract class TransLogObserver {
    protected DataManager dm;

    public abstract void update();
}
