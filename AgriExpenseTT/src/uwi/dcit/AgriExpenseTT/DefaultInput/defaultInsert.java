package uwi.dcit.AgriExpenseTT.DefaultInput;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Kirk on 05/12/2016.
 */

public class defaultInsert {
    private SQLiteDatabase db;
    private static defaultInsert instance = null;

    private defaultInsert(SQLiteDatabase db1){
        db= db1;
    }

    public static void insert(SQLiteDatabase db){
        if(instance == null) {
            instance = new defaultInsert(db);
            defaultSoilAdds.insert(db);
            defaultCrops.insert(db);
            defaultFertilizers.insert(db);
            defaultChemicals.insert(db);
            defaultCounties.insert(db);
            defaultCountries.insert(db);

        }
    }
}
