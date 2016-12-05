package uwi.dcit.AgriExpenseTT.fragments.help;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import uwi.dcit.AgriExpenseTT.R;
import uwi.dcit.AgriExpenseTT.helpers.GAnalyticsHelper;
import java.util.*;

//public class HelpManageDataFragment extends Fragment {
public class HelpManageDataFragment extends HelpOption {

	public ArrayList<String> getScreenTexts()
	{
		ArrayList<String> screenTexts = new ArrayList<String>();
		screenTexts.add("Manage Data");
		screenTexts.add(getResources().getString(R.string.help_manage_data_1));
		screenTexts.add(getResources().getString(R.string.help_manage_data_2));
		screenTexts.add(getResources().getString(R.string.help_manage_data_3));
		screenTexts.add(getResources().getString(R.string.help_manage_data_4));
		return screenTexts;
	}

	public ArrayList<Integer> getScreenTextIds()
	{
		ArrayList<Integer> screenTextIds = new ArrayList<Integer>();
		screenTextIds.add(R.id.article_heading);
		screenTextIds.add(R.id.article_text);
		screenTextIds.add(R.id.article_text_2);
		screenTextIds.add(R.id.article_text_3);
		screenTextIds.add(R.id.article_text_4);
		return screenTextIds;
	}

	public ArrayList<Integer> getImages()
	{
		ArrayList<Integer> images = new ArrayList<Integer>();
		images.add(R.drawable.help_manage_data);
		images.add(R.drawable.help_manage_data_edit_cycle);
		images.add(R.drawable.help_manage_data_editpurchases_details);
		images.add(R.drawable.help_manage_data_delete_record);
		return images;
	}

	public ArrayList<Integer> getImageIds()
	{
		ArrayList<Integer> imageIds = new ArrayList<Integer>();
		imageIds.add(R.id.article_image);
		imageIds.add(R.id.article_image_2);
		imageIds.add(R.id.article_image_3);
		imageIds.add(R.id.article_image_4);
		return imageIds;
	}

	public String getScreenViewText()
	{
		String screenViewText = "Help Manage Data Fragment";
		return screenViewText;
	}

	public int getLayout()
	{
		int layout = R.layout.fragment_help_article_view_layout4;
		return layout;
	}

	
}
