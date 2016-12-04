package uwi.dcit.AgriExpenseTT;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import uwi.dcit.AgriExpenseTT.cloud.SignInManager;
import uwi.dcit.AgriExpenseTT.fragments.FragmentHomeT;
import uwi.dcit.AgriExpenseTT.fragments.FragmentManageDataT;
import uwi.dcit.AgriExpenseTT.fragments.NavigationDrawerFragment;
import uwi.dcit.AgriExpenseTT.helpers.NavigationControl;


public abstract class BaseActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks , NavigationControl {

    protected SignInManager signInManager;
    protected Fragment leftFrag,rightFrag;
    protected NavigationDrawerFragment mNavigationDrawerFragment;
    protected boolean isTablet = false;
//    protected String mTitle;

    protected final int RequestCode_backup =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_ACTION_BAR); // Request Feature must be called before adding content
        super.onCreate(savedInstanceState);
        signInManager = new SignInManager(BaseActivity.this,BaseActivity.this);
        isTablet = this.getResources().getBoolean(R.bool.isTablet);
    }

    public void setupNavDrawer(){
        mNavigationDrawerFragment = (NavigationDrawerFragment)getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        if (mNavigationDrawerFragment != null)
            mNavigationDrawerFragment.setUp( R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {
        switch (position){
            case 0:
                // Home
//                startActivity(new Intent(this, Main.class));
                goToLocation(new FragmentHomeT(),"home");

                break;
            case 1:
                //new cycle
                startActivity(new Intent(this, NewCycle.class));
                break;
            case 2:
                //new purchase
                startActivity(new Intent(this, NewPurchase.class));
                break;
            case 3:
                //hire labour
                startActivity(new Intent(this, HireLabour.class));
                break;
            case 4:
                //report manager
                startActivity(new Intent(this,ManageReport.class));
                break;
            case 5:
                // manage data
                goToLocation(new FragmentManageDataT(),"manage data");
                break;
            case 6:
                backUpData();
                break;

        }
    }

    /**
     * Go to Diffrent Locations with the application
     * @param frag
     * @param fragTag
     */
    public void goToLocation(Fragment frag, String fragTag){

       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle args = new Bundle();
        args.putString("locationName",fragTag);
        frag.setArguments(args);
        transaction.replace(R.id.navContentLeft,frag,fragTag);
        transaction.commit();

//        this.mTitle = fragTag.substring(0, 1).toUpperCase() + fragTag.substring(1);
//        getSupportActionBar().setTitle(fragTag.substring(0, 1).toUpperCase() + fragTag.substring(1));
        // change icon to arrow drawable
//        getSupportActionBar().setHomeAsUpIndicator(android.);

    }

    public void backUpData(){
        Toast.makeText(getApplicationContext(), "Backing up data not available at this time. Please check back later", Toast.LENGTH_LONG).show();

//        Intent i = new Intent(getApplicationContext(), Backup.class);
//        if (this.signInManager.isExisting() == null){ 			// User does not exist => check Internet and then create user
//            if (!NetworkHelper.isNetworkAvailable(this)){ 		// No network available so display appropriate message
//                Toast.makeText(getApplicationContext(), "No internet connection, Unable to sign-in at the moment.", Toast.LENGTH_LONG).show();
//                return;
//            }
//            startActivityForResult(i,RequestCode_backup);// Launch the Backup activity with the sign-up action passed
//        }else if (!this.signInManager.isSignedIn()){ 			// If not signed attempt to login with existing account
//            signInManager.signIn();
//        }
    }

    @Override
    public void navigate(Fragment oldFrag,Fragment newFrag) {

    }

    @Override
    public Fragment getLeftFrag() {
        return leftFrag;
    }
    @Override
    public Fragment getRightFrag() {
        return rightFrag;
    }

    @Override
    public String[] getMenuOptions() {
        return new String[]{
                getString(R.string.menu_item_home),
                getString(R.string.menu_item_newCycle),
                getString(R.string.menu_item_newPurchase),
                getString(R.string.menu_item_hireLabour),
                getString(R.string.menu_item_genFile),
                getString(R.string.menu_item_manageData),
                getString(R.string.menu_item_signIn)
        };
    }

    @Override
    public int[] getMenuImages() {
        return new int[]{
                R.drawable.ic_home_black_36dp,
                R.drawable.mainmenu_cycle_triangle,
                R.drawable.mainmenu_shopping_cart,
                R.drawable.mainmenu_shovel_single,
                R.drawable.mainmenu_reports,
                R.drawable.mainmenu_data_settings,
                R.drawable.mainmenu_signin
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
//            startActivity(new Intent(getApplicationContext(),ManageData.class));
            this.goToLocation(new FragmentManageDataT(),"manage data");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void hideSoftKeyboard() {
        if(getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


}
