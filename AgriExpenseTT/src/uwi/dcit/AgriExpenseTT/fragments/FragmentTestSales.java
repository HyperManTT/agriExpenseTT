package uwi.dcit.AgriExpenseTT.fragments;

import android.graphics.Color;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.InterfaceSysModuleTabElement;
import uwi.dcit.AgriExpenseTT.helpers.DbHelper;

/**
 * Created by jason on 04/12/2016.
 */

public class FragmentTestSales extends FragmentSysModuleT implements InterfaceSysModuleTabElement {

    @Override
    public int getTabColor() {
        return Color.CYAN;
    }

    @Override
    public String getTabName() {
        return "Sales";
    }

    @Override
    public boolean isExistInDb() {
        return false;
    }

    @Override
    public Fragment getEmptyFrag() {
        return null;
    }

    @Override
    public void setModuleRegisteredLocation(ArrayList<String> moduleLocationList) {

        moduleLocationList.add("home");

    }

    @Override
    public void initializeSysModule(DbHelper dbh) {


    }

}
