package uwi.dcit.AgriExpenseTT.DefaultInput;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import uwi.dcit.AgriExpenseTT.helpers.DHelper;
import uwi.dcit.AgriExpenseTT.models.ResourceContract;

/**
 * Created by Kirk on 04/12/2016.
 */

public class defaultCrops{
    private static String [] data = new String[]{
            "SOYABEAN",
            "COCOA",
            //fruits
            "CITRUS",
            "ORANGES",
            "LIME",
            "LEMON",
            "GRAPEFRUIT",
            "TANGERINE",
            "PORTUGAL",
            "COCONUT",
            "GOLDEN APPLE",
            "MANGO",
            "WATERMELON",
            "PAW PAW",
            "PINEAPPLE",
            "SUGARCANE",
            "SORREL",
            "RICE",
            "MAIZE (CORN)",
            "BREADFRUIT",
            "BANANA",
            "BREADNUT (CHATAIGNE)",
            "CHERRY",
            "CARAMBOLA",
            "PEANUTS",
            "NUTMEG",
            //herbs
            "ANISE SEED",
            "BASIL",
            "BAY LEAF",
            "CELERY",
            "CHIVE",
            "CURRY LEAF",
            "DILL",
            "FENNEL",
            "MARJORAM",
            "MINT",
            "OREGANO",
            "PARSLEY",
            "ROSEMARY",
            "CULANTRO (SHADON BENI / BANDANIA)",
            "TARRAGON",
            "THYME - FRENCH",
            "THYME - SPANISH",
            "THYME - FINE",
            //root crops
            "BEET",
            "CASSAVA",
            "CUSH CUSH",
            "DASHEEN",
            "EDDOES",
            "GINGER",
            "HORSERADISH",
            "ONIONS",
            "SWEET POTATO",
            "TANNIA",
            "LEREN (TOPI TAMBU)",
            "TUMERIC (SAFFRON)",
            "YAM",
            //vegetables
            "BHAGI",
            "BORA (BODI) BEAN",
            "BROCCOLI",
            "CARROTS",
            "CABBAGE",
            "CARAILLI",
            "CAULIFLOWER",
            "CHOI SUM (CHINESE CABBAGE)", //Brassica rapa cv. chinensis
            "CHRISTOPHENE",
            "CORN",
            "CUCUMBER",
            "DASHEEN BUSH",
            "GREEN FIG",
            "COWPEA (GUB GUB)",
            "HOT PEPPER",
            "JACK BEAN",
            "JHINGI",
            "LAUKI",
            "LETTUCE",
            "EGGPLANT",
            "RADISH (MOORAI)",
            "OCHRO",
            "PAKCHOY", //Brassica rapa
            "PIGEON PEAS",
            "PIMENTO PEPPER",
            "PLANTAIN",
            "VINE SPINACH (POI BHAGI)",
            "PUMPKIN",
            "SAIJAN",
            "SATPUTIYA (LOOFAH)",
            "SEIM",
            "STRING BEAN",
            "SQUASH",
            "SWEET PEPPER",
            "TOMATO",
            "WATERCRESS",
            "WING BEAN",
            "ESCALLION",
    };
    private static defaultCrops instance = null;
    private SQLiteDatabase db;
    private boolean inserted = false;
    private static String type = DHelper.cat_plantingMaterial;


    private defaultCrops(SQLiteDatabase db1){
        db= db1;
    }

    protected void insertDefault(){
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
            instance = new defaultCrops(db);
            instance.insertDefault();
        }
    }
}
