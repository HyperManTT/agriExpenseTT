package uwi.dcit.AgriExpenseTT.CRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;

/**
 * Created by shivr on 12/1/2016.
 */

public class DBCRUD {
    SQLiteDatabase db;
    DbHelper dbh;
    Context context;

    public DBCRUD(Context context){
        dbh= new DbHelper(context);
        db = dbh.getWritableDatabase();
        this.context = context;
    }

    public int insertObject(ContentValues cv, String tableName){
        Long rowId = db.insert(tableName, null, cv);
        db.close();
        return rowId.intValue();
    }

    public void updateOnject(){

    }

    public void deleteObject(){

    }

    public void getObject(){

    }

    public void getAllObjects(){

    }
}
