package uwi.dcit.AgriExpenseTT.CRUD.Cycle;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by shivr on 12/2/2016.
 * Edited by Kirk on 12/4/2016.
 */

public class Cycle {
    private Integer cropId;
    private String cropName;
    private String landType;
    private String harvestType;
    private Integer landAmount;
    private Integer date;
    private Double totalSpent;
    private Double harvestAmount;
    private Double costPer;
    private String county;
    private String cycleName;

    public Cycle(Integer cropId, String landType, String cropName, String harvestType, Integer landAmount, Integer date, Double totalSpent, Double harvestAmount, Double costPer, String county, String cycleName) {
        this.cropId = cropId;
        this.landType = landType;
        this.cropName = cropName;
        this.harvestType = harvestType;
        this.landAmount = landAmount;
        this.date = date;
        this.totalSpent = totalSpent;
        this.harvestAmount = harvestAmount;
        this.costPer = costPer;
        this.county = county;
        this.cycleName = cycleName;


    }
    public ContentValues getContentValues(){
        ContentValues cv = new ContentValues();
        cv.put(CycleContract.CycleEntry.CROPCYCLE_CROPID, cropId);
        cv.put(CycleContract.CycleEntry.CROPCYCLE_LAND_TYPE, landType);
        cv.put(CycleContract.CycleEntry.CROPCYCLE_LAND_AMOUNT, landAmount);
        cv.put(CycleContract.CycleEntry.CROPCYCLE_DATE, date);
        cv.put(CycleContract.CycleEntry.CROPCYCLE_TOTALSPENT, totalSpent);
        cv.put(CycleContract.CycleEntry.CROPCYCLE_HARVEST_TYPE, harvestType);
        cv.put(CycleContract.CycleEntry.CROPCYCLE_HARVEST_AMT, harvestAmount);
        cv.put(CycleContract.CycleEntry.CROPCYCLE_COSTPER, costPer);
        cv.put(CycleContract.CycleEntry.CROPCYCLE_COUNTY, county);
        cv.put(CycleContract.CycleEntry.CROPCYCLE_RESOURCE, cropName);
        cv.put(CycleContract.CycleEntry.CROPCYCLE_NAME, cycleName);
        return cv;
    }

    public void setCursorVaues(Cursor cycleCursor){
        this.cropId = cycleCursor.getInt(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_CROPID));
        this.landType = cycleCursor.getString(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_LAND_TYPE));
        this.landAmount = cycleCursor.getInt(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_LAND_AMOUNT));
        this.date = cycleCursor.getInt(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_DATE));
        this.totalSpent = cycleCursor.getDouble(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_TOTALSPENT));
        this.harvestType = cycleCursor.getString(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_HARVEST_TYPE));
        this.harvestAmount = cycleCursor.getDouble(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_HARVEST_AMT));
        this.costPer = cycleCursor.getDouble(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_COSTPER));
        this.county = cycleCursor.getString(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_COUNTY));
        this.cropName = cycleCursor.getString(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_RESOURCE));
        this.cycleName = cycleCursor.getString(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_NAME));
        cycleCursor.close();

    }
    public Integer getCropId() {
        return cropId;
    }

    public void setCropId(Integer cropId) {
        this.cropId = cropId;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public String getLandType() {
        return landType;
    }

    public void setLandType(String landType) {
        this.landType = landType;
    }

    public String getHarvestType() {
        return harvestType;
    }

    public void setHarvestType(String harvestType) {
        this.harvestType = harvestType;
    }

    public Integer getLandAmount() {
        return landAmount;
    }

    public void setLandAmount(Integer landAmount) {
        this.landAmount = landAmount;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(Double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public Double getHarvestAmount() {
        return harvestAmount;
    }

    public void setHarvestAmount(Double harvestAmount) {
        this.harvestAmount = harvestAmount;
    }

    public Double getCostPer() {
        return costPer;
    }

    public void setCostPer(Double costPer) {
        this.costPer = costPer;
    }

    public String getCounty() {
        return county;
    }

    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
    }

    public String getCycleName(){
        return cycleName;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}