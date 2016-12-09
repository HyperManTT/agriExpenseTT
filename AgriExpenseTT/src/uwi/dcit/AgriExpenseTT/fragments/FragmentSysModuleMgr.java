package uwi.dcit.AgriExpenseTT.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.InterfaceSysModuleTabElement;
import uwi.dcit.AgriExpenseTT.R;
import uwi.dcit.AgriExpenseTT.helpers.DbHelper;
import uwi.dcit.AgriExpenseTT.widgets.SlidingTabLayout;

/**
 * Created by jason on 23/11/2016.
 */

public class FragmentSysModuleMgr extends Fragment {

    private ArrayList<FragmentSysModule> fragmentSysModuleList;
    private ArrayList<String> moduleLocationList;
    private ArrayList<FragmentSysModuleMgr.FragItem> fragments;
    private FragmentSysModuleMgr.ResourcePageAdapter adapter;
    private boolean configured;
    SQLiteDatabase db;
    DbHelper dbh;
    private String userLocationRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments=new ArrayList<>();
        configured = true;
        //dbh = new DbHelper(getActivity().getBaseContext());
        dbh= DbHelper.getInstance(getActivity().getApplicationContext());
        db = dbh.getWritableDatabase();
        userLocationRequest = getArguments().getString("userLocationRequest");

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("fragmentSysModuleList")) {
                this.fragmentSysModuleList = (ArrayList<FragmentSysModule>) savedInstanceState.getSerializable("fragmentSysModuleList");
            }
            savedInstanceState.remove("fragmentSysModuleList");
        }

            populateList();

    }


    public void initializer (ArrayList<FragmentSysModule> fragmentSysModuleList){
        this.fragmentSysModuleList = fragmentSysModuleList;

    }

    @Override
    public void onResume(){
        super.onResume();
        if (!configured){
            populateList();
            adapter.notifyDataSetChanged();
            configured = true;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

//        outState.putSerializable("fragmentSysModuleList",fragmentSysModuleList);
//        outState.putParcelable("emptyFrag",);
        outState.putParcelableArrayList("fragmentSysModuleList",fragmentSysModuleList);
        super.onSaveInstanceState(outState);
    }

    public void populateList(){
        Bundle arguments = new Bundle();
        for (FragmentSysModule fragmentSysModule: fragmentSysModuleList) {

            fragmentSysModule.initializeSysModule(dbh);
            moduleLocationList = new ArrayList<String>();
            fragmentSysModule.setModuleRegisteredLocation(moduleLocationList);
            for (String moduleLocation: moduleLocationList) {
                if (userLocationRequest.equalsIgnoreCase(moduleLocation)){

                    InterfaceSysModuleTabElement interfaceSysModuleTabElement = (InterfaceSysModuleTabElement) fragmentSysModule;
//                    Fragment fragSysModule= (Fragment) fragmentSysModule;
                    Fragment emptyFragment = fragmentSysModule.getEmptyFrag();
                    if (emptyFragment == null){
                        emptyFragment = new FragmentEmptyDefault("System module: Nothing to show");
                    }
                    arguments.putString("userLocationRequest",userLocationRequest);
                    fragmentSysModule.setArguments(arguments);

                    if (fragmentSysModule.isExistInDb()) {

                        fragments.add(new FragItem(fragmentSysModule,
                                interfaceSysModuleTabElement.getTabColor(), interfaceSysModuleTabElement.getTabName()));
                    }
                    else{

                        fragments.add(new FragItem(emptyFragment
                                , interfaceSysModuleTabElement.getTabColor(), interfaceSysModuleTabElement.getTabName()));
                    }

                }
            }

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manage_resources, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){

        ViewPager mViewPager = (ViewPager) view.findViewById(R.id.viewpager_manage_resources);
        adapter = new FragmentSysModuleMgr.ResourcePageAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);

        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs_manage_resources);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) { return fragments.get(position).getColour(); }
            @Override
            public int getDividerColor(int position) { return fragments.get(position).getColour(); }
        });
    }


    protected class ResourcePageAdapter extends FragmentPagerAdapter {
        public ResourcePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position).getFrag();
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {return fragments.get(position).getTitle(); }
    }

    protected class FragItem{
        private Fragment frag;
        private int colour;
        private String title;
        public FragItem(Fragment frag,int colour,String title){
            this.frag=frag;
            this.colour=colour;
            this.title=title;
        }
        public Fragment getFrag() {
            return frag;
        }
        public int getColour() {
            return colour;
        }
        public String getTitle(){
            return title;
        }
    }


}

 class FragmentEmptyDefault extends Fragment implements Parcelable{
    TextView tv;
    String textTodisplay;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_empty_default,container,false);
        tv = (TextView) v.findViewById(R.id.tv_empty_desc_default);
        tv.setText(textTodisplay);

        return v;
    }

    public FragmentEmptyDefault(String textToDisplay){
        this.textTodisplay = textToDisplay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public FragmentEmptyDefault(Parcel in){
        String [] data = new String [1];
        in.readStringArray(data);
        this.textTodisplay = data[0];
    }

    public FragmentEmptyDefault(){

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{this.textTodisplay});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){


        @Override
        public FragmentEmptyDefault createFromParcel(Parcel source) {
            return new FragmentEmptyDefault(source);
        }

        @Override
        public FragmentEmptyDefault[] newArray(int size) {
            return new FragmentEmptyDefault[size];
        }
    };

}