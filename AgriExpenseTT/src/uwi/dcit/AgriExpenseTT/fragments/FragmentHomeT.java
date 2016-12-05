package uwi.dcit.AgriExpenseTT.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import uwi.dcit.AgriExpenseTT.InterfaceSlideInLocationT;
import uwi.dcit.AgriExpenseTT.Main;

/**
 * Created by jason on 01/12/2016.
 */


public class FragmentHomeT extends Fragment implements InterfaceSlideInLocationT {

    String locationName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         locationName = getArguments().getString("locationName");
        this.startSlideInLocation(this.locationName);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        this.startSlideInLocation(this.locationName);
    }

    @Override
    public void startSlideInLocation(String userLocationRequest) {

        Main main = (Main) getActivity();
        main.loadSysModules(userLocationRequest);

    }
}
