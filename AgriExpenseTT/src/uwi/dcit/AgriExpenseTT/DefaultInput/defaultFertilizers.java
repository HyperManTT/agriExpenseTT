package uwi.dcit.AgriExpenseTT.DefaultInput;

        import android.content.ContentValues;
        import android.database.sqlite.SQLiteDatabase;

        import uwi.dcit.AgriExpenseTT.helpers.DHelper;
        import uwi.dcit.AgriExpenseTT.models.ResourceContract;

/**
 * Created by Kirk on 05/12/2016.
 */

public class defaultFertilizers{
    private static String [] data = new String[]{
            "Fersan (7.12.40 + 1TEM)",
            "Magic Grow (7.12.40 + TE HYDROPHONIC)",
            "Hydro YARA Liva (15.0.15)",
            "Techni - Grow (7.12.27 + TE)",
            "Ferqidd (10.13.32 + TE)",
            "Plant Prod (7.12.27 + TE)",
            "Flower Plus (9.18.36 + TE)",
            "Iron Chelate Powder (FE - EDTA)",
            "Magnesium Sulphate (Mg SO4)",
            "12-24-12 FERTILIZER",
            "HARVEST MORE 10-55-10",
            "HARVEST MORE 13-0-44",
            "HARVEST MORE 5-5-45",
            "NPK 12-12-17",
            "UREA 46-0-0",
            "PLANT BOOSTER",
            "MIRACLE GRO ALL PROPOSE PLANT FOOD",
            "SCOTTS FLOWER AND VEGETABLE PLANT FOOD"
    };
    private static defaultFertilizers instance = null;
    private SQLiteDatabase db;
    private boolean inserted = false;
    private static String type = DHelper.cat_fertilizer;


    private defaultFertilizers(SQLiteDatabase db1){
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
            instance = new defaultFertilizers(db);
            instance.insertDefault();
        }
    }
}