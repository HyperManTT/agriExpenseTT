package uwi.dcit.AgriExpenseTT.fragments.help;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import uwi.dcit.AgriExpenseTT.R;
import uwi.dcit.AgriExpenseTT.helpers.GAnalyticsHelper;


public class HelpNewCropCycleFragment extends HelpOption{

	public ArrayList<String> getScreenTexts()
	{
		ArrayList<String> screenTexts = new ArrayList<String>();
		screenTexts.add("New Crop Cycle");
		screenTexts.add(getResources().getString(R.string.help_cropcycle_new_1));
		screenTexts.add(getResources().getString(R.string.help_cropcycle_new_2));
		screenTexts.add(getResources().getString(R.string.help_cropcycle_new_3));
		screenTexts.add(getResources().getString(R.string.help_cropcycle_new_4));
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
		images.add(R.drawable.help_new_cropcycle_crop);
		images.add(R.drawable.help_new_cropcycle_land);
		images.add(R.drawable.help_new_cropcycle_date);
		images.add(R.drawable.help_new_cropcycle_details);
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
		String screenViewText = "Help New Crop Cycle Fragment";
		return screenViewText;
	}

	public int getLayout()
	{
		int layout = R.layout.fragment_help_article_view_layout4;
		return layout;
	}
}
