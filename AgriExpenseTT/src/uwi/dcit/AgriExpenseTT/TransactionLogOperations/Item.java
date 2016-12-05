package uwi.dcit.AgriExpenseTT.TransactionLogOperations;

import android.database.sqlite.SQLiteDatabase;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.agriexpensesvr.translogApi.model.TransLog;

/**
 * Created by ander on 12/2/2016.
 */
public interface Item {

    public void update(TransLog log, String namespace);
    public void insert(TransLog log, String namespace);
    public void delete(TransLog log, String namespace);

    public boolean createCloud(String namespace);
    public void removeNamespace(String namespace);
}
