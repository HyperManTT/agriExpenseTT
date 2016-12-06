package uwi.dcit.AgriExpenseTT.fragments.help;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import uwi.dcit.AgriExpenseTT.R;
import uwi.dcit.AgriExpenseTT.helpers.GAnalyticsHelper;

public class HelpManageResourceFragment extends HelpOption
{

	public ArrayList<String> getScreenTexts()
	{
		ArrayList<String> screenTexts = new ArrayList<String>();
		screenTexts.add("Managing Resources");
		screenTexts.add(getResources().getString(R.string.help_manage_resources));
		screenTexts.add(getResources().getString(R.string.help_manage_resources_2));
		screenTexts.add(getResources().getString(R.string.help_manage_resources_3));
		screenTexts.add(getResources().getString(R.string.help_manage_resources_4));
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
		images.add(R.drawable.help_manage_resources);
		images.add(R.drawable.help_use_resource_home);
		images.add(R.drawable.help_use_resource_item);
		images.add(R.drawable.help_use_resource_item);
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
		String screenViewText = "Help Manage Resources Fragment";
		return screenViewText;
	}

	public int getLayout()
	{
		int layout = R.layout.fragment_help_article_view_layout4;
		return layout;
	}
	
}
