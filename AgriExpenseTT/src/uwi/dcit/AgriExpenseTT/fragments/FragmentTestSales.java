package uwi.dcit.AgriExpenseTT.fragments;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.InterfaceSysModuleTabElement;
import uwi.dcit.AgriExpenseTT.helpers.DbHelper;

/**
 * Created by jason on 04/12/2016.
 */

public class FragmentTestSales extends FragmentSysModule implements InterfaceSysModuleTabElement {

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

        moduleLocationList.add("edit");

    }

    @Override
    public void initializeSysModule(DbHelper dbh) {


    }

    public FragmentTestSales(){}
    public FragmentTestSales(Parcel in){


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        @Override
        public FragmentTestSales createFromParcel(Parcel source) {
            return new FragmentTestSales(source);
        }

        @Override
        public FragmentTestSales[] newArray(int size) {
            return new FragmentTestSales[size];
        }
    };
}
