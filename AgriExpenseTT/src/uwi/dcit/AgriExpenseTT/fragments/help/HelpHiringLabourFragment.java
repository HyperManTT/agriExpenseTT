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


public class HelpHiringLabourFragment extends HelpOption {

	public ArrayList<String> getScreenTexts()
	{
		ArrayList<String> screenTexts = new ArrayList<String>();
		screenTexts.add("Hire Labour");
		screenTexts.add(getResources().getString(R.string.help_hiring_labour_1));
		screenTexts.add(getResources().getString(R.string.help_hiring_labour_3));
		screenTexts.add(getResources().getString(R.string.help_hiring_labour_3));
		screenTexts.add(getResources().getString(R.string.help_hiring_labour_4));
		screenTexts.add(getResources().getString(R.string.help_hiring_labour_5));
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
		screenTextIds.add(R.id.article_text_5);
		return screenTextIds;
	}

	public ArrayList<Integer> getImages()
	{
		ArrayList<Integer> images = new ArrayList<Integer>();
		images.add(R.drawable.help_hire_labour);
		images.add(R.drawable.help_hire_labour_timeframe);
		images.add(R.drawable.help_hire_labour_cropcycle);
		images.add(R.drawable.help_hire_labour_time);
		images.add(R.drawable.help_hire_labour_enterdetails);
		return images;
	}

	public ArrayList<Integer> getImageIds()
	{
		ArrayList<Integer> imageIds = new ArrayList<Integer>();
		imageIds.add(R.id.article_image);
		imageIds.add(R.id.article_image_2);
		imageIds.add(R.id.article_image_3);
		imageIds.add(R.id.article_image_4);
		imageIds.add(R.id.article_image_5);
		return imageIds;
	}

	public String getScreenViewText()
	{
		String screenViewText = "Help Hiring Labour Fragment";
		return screenViewText;
	}

	public int getLayout()
	{
		int layout = R.layout.fragment_help_article_view_layout5;
		return layout;
	}
}
