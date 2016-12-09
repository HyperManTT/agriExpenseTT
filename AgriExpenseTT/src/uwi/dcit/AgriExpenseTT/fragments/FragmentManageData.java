package uwi.dcit.AgriExpenseTT.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import uwi.dcit.AgriExpenseTT.InterfaceSlideInLocationT;
import uwi.dcit.AgriExpenseTT.Main;
import uwi.dcit.AgriExpenseTT.ManageData;
import uwi.dcit.AgriExpenseTT.R;

/**
 * Created by jason on 29/11/2016.
 */

public class FragmentManageData extends Fragment implements InterfaceSlideInLocationT {
    private Button add;
    private Button edit;
    private Button delete;
    private String userLocationRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getArguments().containsKey("userLocationRequest")){
//            this.userLocationRequest = getArguments().getString("userLocationRequest");
//        }
        if (savedInstanceState != null){
            if (savedInstanceState.containsKey("userLocationRequest")){
                this.userLocationRequest = savedInstanceState.getString("userLocationRequest");
                startSlideInLocation(this.userLocationRequest);
            }
        }



//        if (getArguments().containsKey("location")){
//            locationName = getArguments().getString("locationName");
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_manage_data_t,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        add = (Button) view.findViewById(R.id.btn_manageData_addt);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startSlideInLocation("add");
                ManageData manageData = (ManageData) getActivity();
                manageData.loadLocation(new FragmentAddData(),"add");

            }
        });
        edit = (Button) view.findViewById(R.id.btn_manageData_editt);
        edit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startSlideInLocation("edit");
            }
        });
        delete = (Button) view.findViewById(R.id.btn_manageData_deletet);
        delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startSlideInLocation("delete");

            }
        });

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
       if (userLocationRequest != null) outState.putString("userLocationRequest",userLocationRequest);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null){
            if (savedInstanceState.containsKey("userLocationRequest")){
                this.userLocationRequest = savedInstanceState.getString("userLocationRequest");
                startSlideInLocation(this.userLocationRequest);
            }
        }
    }

    @Override
    public void startSlideInLocation(String userLocationRequest) {
        this.userLocationRequest = userLocationRequest;

        ManageData manageData = (ManageData) getActivity();
        manageData.loadSysModules(userLocationRequest);

    }
}
