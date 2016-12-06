package uwi.dcit.AgriExpenseTT.fragments.help;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import uwi.dcit.AgriExpenseTT.helpers.GAnalyticsHelper;


/**
 * Created by Carlon on 3/12/2016.
 */

public abstract class HelpOption extends Fragment
{
    private List<String> screenTexts = null;
    private List<Integer> screenTextIds = null;
    private List<Integer> images = null;
    private List<Integer> imageIds = null;
    private String screenViewText = null;
    int layout;
    String TAG= "HelpOption";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        screenTexts = getScreenTexts();
        screenTextIds = getScreenTextIds();
        screenViewText = getScreenViewText();
        images = getImages();
        imageIds = getImageIds();
        layout = getLayout();

        Iterator<String> textIterator = screenTexts.iterator();
        Iterator<Integer> textIdIterator = screenTextIds.iterator();
        Iterator<Integer> imagesIterator = images.iterator();
        Iterator<Integer> imageIdsIterator = imageIds.iterator();

        try
        {
            View view = inflater.inflate(layout, container, false);
            while(textIterator.hasNext() && textIdIterator.hasNext())
            {
                TextView text = (TextView)view.findViewById(textIdIterator.next());
                text.setText(textIterator.next());
            }

            while(imageIdsIterator.hasNext() && imagesIterator.hasNext())
            {
                ImageView image = (ImageView)view.findViewById(imageIdsIterator.next());
                image.setImageDrawable(getResources().getDrawable(imagesIterator.next()));
            }

            GAnalyticsHelper.getInstance(this.getActivity()).sendScreenView(screenViewText);
            return view;
        }

        catch(Exception rnfe)
        {
            Log.d(rnfe.getMessage(),TAG);
            return super.onCreateView(inflater,container,savedInstanceState);
        }


    }

    public abstract ArrayList<String> getScreenTexts();
    public abstract ArrayList<Integer> getScreenTextIds();
    public abstract String getScreenViewText();
    public abstract int getLayout();
    public abstract ArrayList<Integer> getImages();
    public abstract ArrayList<Integer> getImageIds();
}
