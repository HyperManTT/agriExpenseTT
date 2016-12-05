package uwi.dcit.AgriExpenseTT.Report;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;

/**
 * Created by Randy Ram on 19/11/2016.
 */


/**
 * Base class that all reports inherit from.
 * Default values are provided for folderLocation and defaultName but these can be changed in the
 * child classes if necessary
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

    /**
     * Create the directory that will store all the reports. Directory name is based on the variable
     * folderLocation
     */
    public static void createReportDirectory(){
        File path = new File(Environment.getExternalStorageDirectory()+"/"+folderLocation);
        if (!path.exists())
            path.mkdirs();
    }

    /**
     * Create the directory that will store all the reports. Directory name is based on the parameter
     * @param folderName - Name of the directory to create and store reports
     */
    public static void createReportDirectory(String folderName){
        File path = new File(Environment.getExternalStorageDirectory()+"/"+folderName);
        if (!path.exists())
            path.mkdirs();
    }
}
