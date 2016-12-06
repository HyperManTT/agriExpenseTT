package uwi.dcit.AgriExpenseTT.fragments.help;

import uwi.dcit.AgriExpenseTT.fragments.help.HelpOption;
import uwi.dcit.AgriExpenseTT.fragments.help.HelpGenerateReportFragment;
import uwi.dcit.AgriExpenseTT.fragments.help.HelpHiringLabourFragment;
import uwi.dcit.AgriExpenseTT.fragments.help.HelpIntroFragment;
import uwi.dcit.AgriExpenseTT.fragments.help.HelpManageDataFragment;
import uwi.dcit.AgriExpenseTT.fragments.help.HelpManageResourceFragment;
import uwi.dcit.AgriExpenseTT.fragments.help.HelpNewCropCycleFragment;
import uwi.dcit.AgriExpenseTT.fragments.help.HelpNewPurchaseFragment;

/**
 * Created by user on 11/27/2016.
 */

public class HelpFactory
{

    public HelpOption createOption (Integer option)
    {

        HelpOption helpOption;
        helpOption = null;


        if (option == 0)
            helpOption = new HelpIntroFragment();

        else if (option == 1)
           helpOption = new HelpNewPurchaseFragment();

        else if (option == 2)
           helpOption = new HelpNewCropCycleFragment();

        else if (option == 3)
           helpOption = new HelpManageResourceFragment();

        else if (option == 4)
           helpOption = new HelpHiringLabourFragment();

        else if (option == 5)
           helpOption = new HelpManageDataFragment();

        else if (option == 6)
           helpOption = new HelpCalculateSalesFragment();

        else if (option == 7)
           helpOption = new HelpGenerateReportFragment();

        return helpOption;
    }
}
