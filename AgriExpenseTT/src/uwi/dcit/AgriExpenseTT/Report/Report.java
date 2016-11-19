package uwi.dcit.AgriExpenseTT.Report;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;

/**
 * Created by Randy Ram on 19/11/2016.
 */

public class Report {

    SQLiteDatabase db;
    DbHelper dbh;
    Activity activity;

    public static  String folderLocation = "AgriExpense";
    public static  String defaultName 	="AgriExpenseReport";

    public Report(Activity act){
        dbh = new DbHelper(act.getBaseContext());
        db = dbh.getWritableDatabase();
        activity = act;
    }

    public static void createReportDirectory(){
        File path = new File(Environment.getExternalStorageDirectory()+"/"+folderLocation);
        if (!path.exists())
            path.mkdirs();
    }

    public static void createReportDirectory(String folderName){
        File path = new File(Environment.getExternalStorageDirectory()+"/"+folderName);
        if (!path.exists())
            path.mkdirs();
    }
}
