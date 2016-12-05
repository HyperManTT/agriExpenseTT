package uwi.dcit.AgriExpenseTT;

import android.app.Activity;

import uwi.dcit.AgriExpenseTT.Report.ExcelReport;

/**
 * Created by Randy Ram on 19/11/2016.
 */


/**
 * Factory class used to create all types of reports
 */
public class ReportFactory {

    Activity activity;
    public ReportFactory(Activity act){
        this.activity = act;
    }

    /**
     * Create a specific type of report based on the reportType parameter
     * @param reportType - The type of report that is to be created.
     */
    public void createReport(String reportType){
        if (reportType.equalsIgnoreCase("EXCEL")){
            ExcelReport report = new ExcelReport(activity);
            report.createReport();  //Invoke the specific function to create an Excel report
        }
    }
}
