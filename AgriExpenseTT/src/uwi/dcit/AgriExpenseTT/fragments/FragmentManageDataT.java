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
import uwi.dcit.AgriExpenseTT.R;

/**
 * Created by jason on 29/11/2016.
 */

public class FragmentManageDataT extends Fragment implements InterfaceSlideInLocationT {
    private Button add;
    private Button edit;
    private Button delete;
    private String locationName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationName = getArguments().getString("locationName");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_manage_datat,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        add = (Button) view.findViewById(R.id.btn_manageData_addt);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startSlideInLocation("add");
                Main main = (Main) getActivity();
                main.goToLocation(new FragmentAddDataT(),"add");

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
    public void startSlideInLocation(String userLocationRequest) {

        Main main = (Main) getActivity();
        main.loadSysModules(userLocationRequest);

    }
}
