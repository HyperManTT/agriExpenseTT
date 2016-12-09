package uwi.dcit.AgriExpenseTT;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.fragments.FragmentChoosePurchase;
import uwi.dcit.AgriExpenseTT.fragments.FragmentManageData;
import uwi.dcit.AgriExpenseTT.fragments.FragmentSysModuleMgr;
import uwi.dcit.AgriExpenseTT.fragments.FragmentSysModule;
import uwi.dcit.AgriExpenseTT.fragments.FragmentTestSales;
import uwi.dcit.AgriExpenseTT.fragments.FragmentViewCycles;
import uwi.dcit.AgriExpenseTT.fragments.FragmentViewResources;
import uwi.dcit.AgriExpenseTT.helpers.GAnalyticsHelper;

public class ManageData extends BaseActivity {

	public ArrayList<FragmentSysModule> fragmentSysModuleTArrayList;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_data_t);

		loadManageDataFragment();



		GAnalyticsHelper.getInstance(this.getApplicationContext()).sendScreenView("Manage Data");
	}

	private void loadManageDataFragment(){

		this.loadLocation(new FragmentManageData(),"manage data");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_data, menu);
		return true;
	}

	public void loadSysModules(String userLocationRequest ){

		//Add modules to  be used by system
		fragmentSysModuleTArrayList = new ArrayList<FragmentSysModule>();

		fragmentSysModuleTArrayList.add(new FragmentViewCycles());
        fragmentSysModuleTArrayList.add(new FragmentViewResources());
        fragmentSysModuleTArrayList.add(new FragmentChoosePurchase());
        fragmentSysModuleTArrayList.add(new FragmentTestSales());

		FragmentSysModuleMgr fragmentSysModuleMgr = new FragmentSysModuleMgr();
		fragmentSysModuleMgr.initializer(fragmentSysModuleTArrayList);

		Bundle args = new Bundle();
		args.putString("userLocationRequest",userLocationRequest);
		fragmentSysModuleMgr.setArguments(args);

		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.manage_data_container, fragmentSysModuleMgr,userLocationRequest);
		transaction.addToBackStack(null);
		transaction.commit();

	}

	/**
	 * Go to diffrent manage data location to be manged
	 * @param frag
	 * @param fragTag
	 */
	public void loadLocation(Fragment frag, String fragTag){

		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		Bundle args = new Bundle();
		args.putString("locationName",fragTag);
		frag.setArguments(args);
		transaction.replace(R.id.manage_data_container,frag,fragTag);
		transaction.commit();

//        this.mTitle = fragTag.substring(0, 1).toUpperCase() + fragTag.substring(1);
//        getSupportActionBar().setTitle(fragTag.substring(0, 1).toUpperCase() + fragTag.substring(1));
		// change icon to arrow drawable
//        getSupportActionBar().setHomeAsUpIndicator(android.);

	}
}
