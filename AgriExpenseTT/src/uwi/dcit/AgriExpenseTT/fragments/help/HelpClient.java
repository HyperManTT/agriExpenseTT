package uwi.dcit.AgriExpenseTT.fragments.help;
//import uwi.dcit.AgriExpenseTT.fragments.help.HelpOption;
//import uwi.dcit.AgriExpenseTT.fragments.help.HelpFactory;
//import HelpFactory
/**
 * Created by user on 12/3/2016.
 */

public class HelpClient
{

    HelpFactory factory;

    public HelpClient(HelpFactory factory)
    {
        this.factory = factory;
    }

    public HelpOption getProduct(Integer productType)
    {
        HelpOption helpOption =  factory.createOption(productType);
        return helpOption;
    }

}
