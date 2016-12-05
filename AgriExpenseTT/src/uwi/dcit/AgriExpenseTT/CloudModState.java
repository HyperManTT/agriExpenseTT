package uwi.dcit.AgriExpenseTT;

import uwi.dcit.agriexpensesvr.upAccApi.model.UpAcc;

/**
 * Created by mdls8 on 12/2/2016.
 */

public class CloudModState {
    private String tableName;
    private String operation;
    private int id;

    public CloudModState(String tableName, String operation, int id){
        this.tableName = tableName;
        this.operation = operation;
        this.id = id;
    }

    public String getTableName(){
        return tableName;
    }

    public String getOperation(){
        return operation;
    }

    public int getId(){
        return id;
    }
}
