package uwi.dcit.AgriExpenseTT.CRUD;

import java.util.List;

/**
 * Created by shivr on 12/3/2016.
 */

public interface ObjectCRUD {
    public int insertObject();

    public void deleteObject();

    public void updateObject();

    public void getObjectFromDB();

    public List getAllObjects();
}
