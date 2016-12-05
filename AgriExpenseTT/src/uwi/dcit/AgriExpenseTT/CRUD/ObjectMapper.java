package uwi.dcit.AgriExpenseTT.CRUD;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * This interface must be implemented by all objects that interact with the database
 * such as Purchases and Cycles.
 *
 * Created by shivr on 12/4/2016.
 */

public abstract class ObjectMapper {

    protected Integer id;

    public abstract ContentValues getContentValues();

    public abstract void setCursorValues(Cursor cursor);

    public abstract boolean isValidObject();

    public int getId(){
        return this.id;
    }

    public ObjectMapper(int id){
        this.id=id;
    }

}
