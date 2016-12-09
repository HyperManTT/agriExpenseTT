package uwi.dcit.AgriExpenseTT.fragments;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
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

public class FragmentViewResources extends FragmentSysModule implements InterfaceSysModuleTabElement{
	SQLiteDatabase db;
	DbHelper dbh;
	ArrayList<String> rList;
	DataManager dm;
	String userLocationRequest;

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putStringArrayList("rList",rList);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//dbh=new DbHelper(this.getActivity().getBaseContext());
		dbh= DbHelper.getInstance(this.getActivity().getApplicationContext());
		db=dbh.getWritableDatabase();
		dm = new DataManager(getActivity(), db, dbh);
		populateList();
		Collections.sort(rList);
		ArrayAdapter<String> listAdapt=new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1, rList);
		setListAdapter(listAdapt);
        GAnalyticsHelper.getInstance(this.getActivity()).sendScreenView("View Resources Fragment");
		if (getArguments().containsKey("userLocationRequest")){
			this.userLocationRequest = getArguments().getString("userLocationRequest");
		}
		if (savedInstanceState != null){
			if (getArguments().containsKey("rlist")){
				this.rList = getArguments().getStringArrayList("rlist");
			}
		}
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
//		String type = getArguments().getString("type");
//		if(type.equals("delete")){
		if (this.userLocationRequest != null) {
			if (userLocationRequest.equals("delete")) {
				@SuppressWarnings("unchecked")
				Confirm c = new Confirm(position, (ArrayAdapter<String>) l.getAdapter());

				AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
				builder1.setMessage("Are you sure you want to delete")
						.setCancelable(true)
						.setPositiveButton("Delete", c)
						.setNegativeButton("Cancel", c);

				AlertDialog alert1 = builder1.create();
				alert1.show();
			}
		}
	}

	@Override
	public int getTabColor() {
		return Color.GRAY;
	}

	@Override
	public String getTabName() {
		return "Resources";
	}

	@Override
	public boolean isExistInDb() {
		return DbQuery.resourceExist(this.db);
	}

	@Override
	public Fragment getEmptyFrag() {
		return null;
	}

	@Override
	public void setModuleRegisteredLocation(ArrayList<String> moduleLocationList) {
		moduleLocationList.add("edit");
		moduleLocationList.add("delete");
	}

	@Override
	public void initializeSysModule(DbHelper dbh) {
		this.dbh = dbh;
		this.db = dbh.getWritableDatabase();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(new String[] {this.userLocationRequest});
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public FragmentViewResources(Parcel in){

		String [] data = new String[1];

		in.readStringArray(data);
		this.userLocationRequest = data[0];

	}
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

		@Override
		public FragmentViewResources createFromParcel(Parcel source) {
			return new FragmentViewResources(source);
		}

		@Override
		public FragmentViewResources[] newArray(int size) {
			return new FragmentViewResources[size];
		}
	};

	public FragmentViewResources (){}
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
