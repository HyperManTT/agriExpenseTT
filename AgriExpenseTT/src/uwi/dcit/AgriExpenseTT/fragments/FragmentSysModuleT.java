package uwi.dcit.AgriExpenseTT.fragments;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.helpers.DbHelper;

/**
 * Created by jason on 23/11/2016.
 */

public abstract class FragmentSysModuleT extends ListFragment implements Parcelable  {

    public abstract boolean isExistInDb();
    public abstract Fragment getEmptyFrag();
    public abstract void setModuleRegisteredLocation(ArrayList<String> moduleLocationList);
    public abstract void initializeSysModule(DbHelper dbh);


}
