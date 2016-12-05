package uwi.dcit.AgriExpenseTT.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import uwi.dcit.AgriExpenseTT.InterfaceSysModuleTabElement;
import uwi.dcit.AgriExpenseTT.R;
import uwi.dcit.AgriExpenseTT.helpers.DataManager;
import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.AgriExpenseTT.helpers.DbQuery;
import uwi.dcit.AgriExpenseTT.helpers.GAnalyticsHelper;

/**
 * Created by jason on 23/11/2016.
 */

public class FragmentViewResourcesT extends FragmentSysModuleT implements InterfaceSysModuleTabElement {
    SQLiteDatabase db;
    DbHelper dbh;
    ArrayList<String> rList;
    DataManager dm;

    @Override
    public boolean isExistInDb() {
        return true;
    }

    @Override
    public Fragment getEmptyFrag() {
        return null;
    }

    @Override
    public String getTabName() {
        return "Resources";
    }

    @Override
    public void setModuleRegisteredLocation(ArrayList<String> moduleLocationList) {
        moduleLocationList.add("edit");
        moduleLocationList.add("delete");
//        moduleLocationList.add("add");
//        moduleLocationList.add("home");

    }

    @Override
    public void initializeSysModule(DbHelper dbh) {
        this.dbh = dbh;
        this.db=dbh.getWritableDatabase();
        this.dm = new DataManager(getActivity(), this.db, this.dbh);
    }

    @Override
    public int getTabColor() {
        return Color.RED;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        populateList();
        Collections.sort(rList);
        ArrayAdapter<String> listAdapt=new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1, rList);
        setListAdapter(listAdapt);
        GAnalyticsHelper.getInstance(this.getActivity()).sendScreenView("View Resources Fragment");
    }

    private void populateList() {
        rList=new ArrayList<String>();
        DbQuery.getResources(db, dbh, null, rList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //returns the inflated layout which contains the listview
        return inflater.inflate(R.layout.fragment_choose_purchase, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String type=getArguments().getString("userLocationRequest");
        if(type.equals("delete")){
            @SuppressWarnings("unchecked")
            FragmentViewResourcesT.Confirm c = new FragmentViewResourcesT.Confirm(position,(ArrayAdapter<String>) l.getAdapter());

            AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
            builder1.setMessage("Are you sure you want to delete")
                    .setCancelable(true)
                    .setPositiveButton("Delete",c)
                    .setNegativeButton("Cancel",c);

            AlertDialog alert1 = builder1.create();
            alert1.show();
        }
    }


    private class Confirm implements DialogInterface.OnClickListener{
        int position;
        int id;
        ArrayAdapter<String> adpt;
        public Confirm(int position,ArrayAdapter<String> adpt){
            this.id=DbQuery.getNameResourceId(db, dbh, rList.get(position));
            this.adpt=adpt;
            this.position=position;
        }
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(which==DialogInterface.BUTTON_POSITIVE){
                dm.deleteResource(id);
                rList.remove(position);
                adpt.notifyDataSetChanged();
                Toast.makeText(getActivity(),"Resource deleted", Toast.LENGTH_SHORT).show();
                dialog.cancel();
                //DeleteExpenseList.this.finish();
            }else if(which==DialogInterface.BUTTON_NEGATIVE){
                dialog.cancel();
            }
        }
    }
}
