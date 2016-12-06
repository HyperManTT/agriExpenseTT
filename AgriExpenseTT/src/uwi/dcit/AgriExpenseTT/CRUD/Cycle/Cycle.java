package uwi.dcit.AgriExpenseTT.CRUD.Cycle;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import uwi.dcit.AgriExpenseTT.CRUD.ObjectMapper;

/**
 * Created by shivr on 12/2/2016.
 * Edited by Kirk on 12/4/2016.
 */

public class Cycle extends ObjectMapper implements Parcelable {

    private String cropName;
    private int cropId;
    private String landType;
    private String harvestType;
    private Double landAmount;
    private Long date;
    private Double totalSpent;
    private Double harvestAmount;
    private Double costPer;
    private String cycleName;

    public Cycle(){
        super(-1);
    }

    public Cycle(String landType, String cropName, String harvestType, Double landAmount, Long date, String cycleName, int cropId) {
        super(-1);
        this.landType = landType;
        this.cropName = cropName;
        this.harvestType = harvestType;
        this.landAmount = landAmount;
        this.date = date;
        this.totalSpent = 0.0;
        this.harvestAmount = 0.0;
        this.costPer = 0.0;
        this.cycleName = cycleName;
        this.cropId = cropId;
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
        cv.put(CycleContract.CycleEntry.CROPCYCLE_RESOURCE, cropName);
        cv.put(CycleContract.CycleEntry.CROPCYCLE_NAME, cycleName);
        return cv;
    }

    public void setCursorValues(Cursor cycleCursor){
        this.id = cycleCursor.getInt(cycleCursor.getColumnIndex(CycleContract.CycleEntry._ID));
        this.cropId = cycleCursor.getInt(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_CROPID));
        this.landType = cycleCursor.getString(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_LAND_TYPE));
        this.landAmount = cycleCursor.getDouble(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_LAND_AMOUNT));
        this.date = cycleCursor.getLong(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_DATE));
        this.totalSpent = cycleCursor.getDouble(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_TOTALSPENT));
        this.harvestType = cycleCursor.getString(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_HARVEST_TYPE));
        this.harvestAmount = cycleCursor.getDouble(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_HARVEST_AMT));
        this.costPer = cycleCursor.getDouble(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_COSTPER));
        this.cropName = cycleCursor.getString(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_RESOURCE));
        this.cycleName = cycleCursor.getString(cycleCursor.getColumnIndex(CycleContract.CycleEntry.CROPCYCLE_NAME));
    }

    public boolean isValidObject() {
        return !(this.getCropId()==-1);
    }

    public Integer getCropId() {
        return id;
    }

    public void setCropId(Integer cropId) {
        this.id = cropId;
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

    public Double getLandAmount() {
        return landAmount;
    }

    public void setLandAmount(Double landAmount) {
        this.landAmount = landAmount;
    }

    public Long getTime() {
        return date;
    }

    public void setTime(Long date) {
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

    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
    }

    public String getCycleName(){
        return cycleName;
    }

    public Cycle(Parcel dest){
        id=dest.readInt();
        cropId=dest.readInt();
        landType=dest.readString();
        landAmount=dest.readDouble();
        date=dest.readLong();
        totalSpent=dest.readDouble();
        harvestAmount=dest.readDouble();
        harvestType=dest.readString();
        costPer=dest.readDouble();
        cycleName =dest.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeInt(cropId);
        dest.writeString(landType);
        dest.writeDouble(landAmount);
        dest.writeLong(date);
        dest.writeDouble(totalSpent);
        dest.writeDouble(harvestAmount);
        dest.writeString(harvestType);
        dest.writeDouble(costPer);
        dest.writeString(cycleName);
    }

    public static final Parcelable.Creator<Cycle> CREATOR = new Parcelable.Creator<Cycle>() {

        @Override
        public Cycle createFromParcel(Parcel source) {
            return new Cycle(source);
        }

        @Override
        public Cycle[] newArray(int size) {
            return new Cycle[size];
        }
    };
    @Override
    public int describeContents() {

        return 0;
    }
}