package uwi.dcit.AgriExpenseTT.fragments.help;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import uwi.dcit.AgriExpenseTT.R;
import uwi.dcit.AgriExpenseTT.helpers.GAnalyticsHelper;
import uwi.dcit.AgriExpenseTT.helpers.HelpTopics;

public class HelpListFragment extends ListFragment{
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // We need to use a different list item layout for devices older than Honeycomb
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
        // Create an array adapter for the list view, using the Topics array
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, HelpTopics.Topics)); //TODO Convert this to a String Array XML rather than a programatic constant
        GAnalyticsHelper.getInstance(this.getActivity()).sendScreenView("Help List Fragment");
    }
	
	@Override
    public void onStart() {
        super.onStart();
        this.setupListeners();
	}
	
	public void setupListeners(){
        this.getListView().setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

				HelpClient helpClient = new HelpClient(new HelpFactory());
				Fragment fragment = helpClient.getProduct(position);

				if (fragment != null)
					getFragmentManager()
						.beginTransaction()
						.replace(R.id.help_lists, fragment)
						.addToBackStack("Help List")
						.commit();
				else
					Toast.makeText(getActivity(), "Help Topic not found", Toast.LENGTH_SHORT).show();

				
			}
        });
	}
}
