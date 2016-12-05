

package uwi.dcit.AgriExpenseTT.DefaultInput;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;
import uwi.dcit.AgriExpenseTT.models.CountyContract;

/**
 * Created by Kirk on 05/12/2016.
 */

public class defaultCounties{
    private static String [][] data = CountyContract.counties;
    private static defaultCounties instance = null;
    private SQLiteDatabase db;
    private boolean inserted = false;
    private static String type = DHelper.cat_chemical;


    private defaultCounties(SQLiteDatabase db1){
        db= db1;
    }

    private void insertDefault(){
        if (!inserted) {
            for (String [] county : data){
                ContentValues cv = new ContentValues();
                cv.put(CountyContract.CountyEntry.COLUMN_NAME_COUNTRY, county[0]);
                cv.put(CountyContract.CountyEntry.COLUMN_NAME_COUNTY , county[1]);
                db.insert(CountyContract.CountyEntry.TABLE_NAME, null, cv);
            }
            inserted = true;
        }
    }

    public static void insert(SQLiteDatabase db){
        if(instance == null) {
            instance = new defaultCounties(db);
            instance.insertDefault();
        }
    }
}