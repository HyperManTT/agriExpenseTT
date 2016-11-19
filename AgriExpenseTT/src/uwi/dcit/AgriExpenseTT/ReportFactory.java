package uwi.dcit.AgriExpenseTT;

import android.app.Activity;

import uwi.dcit.AgriExpenseTT.Report.ExcelReport;

/**
 * Created by Randy Ram on 19/11/2016.
 */

public class ReportFactory {

    Activity activity;
    public ReportFactory(Activity act){
        this.activity = act;
    }
    public void createReport(String reportType){
        if (reportType.equalsIgnoreCase("EXCEL")){
            ExcelReport report = new ExcelReport(activity);
            report.createReport();
        }
    }
}
