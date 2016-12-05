package uwi.dcit.AgriExpenseTT.CRUD;

import java.util.List;

/**
 * Created by shivr on 12/3/2016.
 */

// TODO: 12/5/2016 WOULD HAVE TO DELETE THIS CLASS IF OTHER DESIGN IS WORKING.

public interface ObjectTypeCRUD <E>{
    public int insertObject( E object);

    public void deleteObject(int id);

    public void updateObject(E object);

    //The following two methods are not bound to the object itself in the respective classes.

    public void getObjectFromDB(int id);

    public List getAllObjects();
}
