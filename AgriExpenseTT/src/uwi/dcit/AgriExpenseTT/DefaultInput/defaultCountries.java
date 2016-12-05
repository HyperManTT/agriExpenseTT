

package uwi.dcit.AgriExpenseTT.DefaultInput;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;
import uwi.dcit.AgriExpenseTT.models.CountryContract;

/**
 * Created by Kirk on 05/12/2016.
 */

public class defaultCountries{
    private static String [][] data = CountryContract.countries;
    private static defaultCountries instance = null;
    private SQLiteDatabase db;
    private boolean inserted = false;
    private static String type = DHelper.cat_chemical;


    private defaultCountries(SQLiteDatabase db1){
        db= db1;
    }

    private void insertDefault(){
        if (!inserted) {
            for (String [] country : data){
                ContentValues cv = new ContentValues();
                cv.put(CountryContract.CountryEntry.COLUMN_NAME_COUNTRY, country[0]);
                cv.put(CountryContract.CountryEntry.COLUMN_NAME_TYPE, country[1]);
                db.insert(CountryContract.CountryEntry.TABLE_NAME, null, cv);
            }
            inserted = true;
        }
    }

    public static void insert(SQLiteDatabase db){
        if(instance == null) {
            instance = new defaultCountries(db);
            instance.insertDefault();
        }
    }
}