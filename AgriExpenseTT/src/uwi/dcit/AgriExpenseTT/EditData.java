package uwi.dcit.AgriExpenseTT;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import uwi.dcit.AgriExpenseTT.fragments.FragmentSlidingTabsEdit;
import uwi.dcit.AgriExpenseTT.helpers.GAnalyticsHelper;

public class EditData extends BaseActivity {


    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
    }

    @Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_navigation);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.navContentLeft, new FragmentSlidingTabsEdit())
                .commit();

        // Google Analytics
        GAnalyticsHelper.getInstance(this.getApplicationContext()).sendScreenView("Edit Data Screen");
	}
}
