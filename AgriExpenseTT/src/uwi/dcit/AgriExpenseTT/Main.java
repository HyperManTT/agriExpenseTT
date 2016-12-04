package uwi.dcit.AgriExpenseTT;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.fragments.FragmentChoosePurchaseT;
import uwi.dcit.AgriExpenseTT.fragments.FragmentEmpty;
import uwi.dcit.AgriExpenseTT.fragments.FragmentHomeT;
import uwi.dcit.AgriExpenseTT.fragments.FragmentSlideInLocationT;
import uwi.dcit.AgriExpenseTT.fragments.FragmentSlidingMain;
import uwi.dcit.AgriExpenseTT.fragments.FragmentSysModuleT;
import uwi.dcit.AgriExpenseTT.fragments.FragmentViewCyclesT;
import uwi.dcit.AgriExpenseTT.fragments.FragmentViewResourcesT;
import uwi.dcit.AgriExpenseTT.helpers.GAnalyticsHelper;


public class Main extends BaseActivity {


    private CharSequence mTitle;
    public final static String APP_NAME = "AgriExpenseTT";
    public final static String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_navigation);
        // Needed after setContentView to refer to the appropriate XML View
        setupNavDrawer();

        if(this.isTablet && this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setupLandT();
        }else {
            setupPortT();
        }

//        Fragment currentFrag = getSupportFragmentManager().findFragmentById(R.id.container);
//        mTitle = currentFrag.getTag();
//        mTitle = getTitle();

        // Added Google Analytics
        GAnalyticsHelper.getInstance(this.getApplicationContext()).sendScreenView("Main Screen");
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        String fragTag = fragment.getTag();
        if (fragTag != null) {
            mTitle = fragTag.substring(0, 1).toUpperCase() + fragTag.substring(1);
            restoreActionBar();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        // Check for orientation to determine which interface to load => if portrait will use leftfrag
//        if(this.isTablet && this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            setupLandT();
//        }else {
//            setupPortT();
//        }
    }

    private void setupPort() {
        Fragment frag = new FragmentSlidingMain();

        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.navContentLeft,frag)
            .commit();
    }

    private void setupLand() {
        leftFrag = new FragmentSlidingMain();
        rightFrag = new FragmentEmpty();

        Bundle arguments=new Bundle();
        arguments.putString("type","select");
        rightFrag.setArguments(arguments);

        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.navContentLeft, leftFrag)
            .replace(R.id.navContentRight, rightFrag)
            .commit();
    }

    private void setupPortT(){
        this.loadHomeFragment();
    }
    private void setupLandT(){
        this.loadHomeFragment();
    }
    private void loadHomeFragment(){

        super.goToLocation(new FragmentHomeT(),"home");
    }

    public void restoreActionBar() {
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void navigate(Fragment oldFrag,Fragment newFrag) {
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        if(this.isTablet && this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE){

            Class reflectClass = oldFrag.getClass();
            Bundle arguments=oldFrag.getArguments();
            try {
                oldFrag = (Fragment)reflectClass.newInstance();
            } catch (Exception e){e.printStackTrace();}
            oldFrag.setArguments(arguments);
            ft.replace(R.id.navContentLeft, oldFrag);
            leftFrag=oldFrag;
            leftFrag.setRetainInstance(true);
            ft.replace(R.id.navContentRight,newFrag).addToBackStack("left");
            rightFrag=newFrag;
            rightFrag.setRetainInstance(false);
        }else{
            ft.replace(R.id.navContentLeft,newFrag).addToBackStack("right");
        }
        ft.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(findViewById(R.id.navContentRight)!=null){
            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.remove(rightFrag).commit();
            //have to put in someting here to purge transaction to ensure its still not running
            getSupportFragmentManager().executePendingTransactions();
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == RequestCode_backup) {
            // Make sure the request was successful
            if (resultCode == 1) {
                String country=data.getStringExtra("country");
                String county=data.getStringExtra("county");
                Log.d("Main Activity","returned with "+country+" "+county);
                signInManager.signIn();
            }
        }
    }

    public void openNewCycle(View view){
        startActivity(new Intent(getApplicationContext(), NewCycle.class));
    }

    public void openNewPurchase(View view){
        startActivity(new Intent(getApplicationContext(), NewPurchase.class));
    }

    /**
     * Loads all the appropriate system modules depending a given user location request
     * @param userLocationRequest
     */
    public void loadSysModules(String userLocationRequest ){

        //Add modules to  be used by system
        ArrayList<FragmentSysModuleT> fragmentSysModuleTArrayList = new ArrayList<FragmentSysModuleT>();

        fragmentSysModuleTArrayList.add(new FragmentViewCyclesT());
        fragmentSysModuleTArrayList.add(new FragmentViewResourcesT());
        fragmentSysModuleTArrayList.add(new FragmentChoosePurchaseT());

        FragmentSlideInLocationT fragmentSlideInLocationT = new FragmentSlideInLocationT();
        fragmentSlideInLocationT.initializer(fragmentSysModuleTArrayList);

        Bundle args = new Bundle();
        args.putString("userLocationRequest",userLocationRequest);
        fragmentSlideInLocationT.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.navContentLeft, fragmentSlideInLocationT,userLocationRequest);
        transaction.addToBackStack(null);
        transaction.commit();


    }
}
