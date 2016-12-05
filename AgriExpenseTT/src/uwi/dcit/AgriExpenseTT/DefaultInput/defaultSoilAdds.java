

package uwi.dcit.AgriExpenseTT.DefaultInput;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;
import uwi.dcit.AgriExpenseTT.models.ResourceContract;

/**
 * Created by Kirk on 05/12/2016.
 */

public class defaultSoilAdds{
    private static String [] data = new String[]{
            "Cow manure",
            "Compost",
            "Gypsum",
            "Limestone",
            "Sulphur",
            "Molasses",
            "Chicken manure",
            "Horse manure",
            "Calphos",
            "Sharp sand"
    };
    private static defaultSoilAdds instance = null;
    private SQLiteDatabase db;
    private boolean inserted = false;
    private static String type = DHelper.cat_soilAmendment;


    private defaultSoilAdds(SQLiteDatabase db1){
        db= db1;
    }

    private void insertDefault(){
        if (!inserted) {
            for (String s : data) {
                ContentValues cv = new ContentValues();
                cv.put(ResourceContract.ResourceEntry.RESOURCES_NAME, s);
                cv.put(ResourceContract.ResourceEntry.RESOURCES_TYPE, type);
                db.insert(ResourceContract.ResourceEntry.TABLE_NAME, null, cv);
            }
            inserted = true;
        }
    }

    public static void insert(SQLiteDatabase db){
        if(instance == null) {
            instance = new defaultSoilAdds(db);
            instance.insertDefault();
        }
    }
}