package uwi.dcit.AgriExpenseTT.CRUD.ResourcePurchase;

import android.content.Context;

import java.util.List;

import uwi.dcit.AgriExpenseTT.CRUD.ObjectTypeMapper;

/**
 * Created by shivr on 12/4/2016.
 */

public class ResourcePurchaseCRUD extends ObjectTypeMapper{

    public ResourcePurchaseCRUD(Context context){
        super(context, ResourcePurchaseContract.ResourcePurchaseEntry.TABLE_NAME);
    }

    @Override
    public void getObjectFromDB(int id) {
        
    }

    @Override
    public List getAllObjectsFromDB() {
        return null;
    }
}
