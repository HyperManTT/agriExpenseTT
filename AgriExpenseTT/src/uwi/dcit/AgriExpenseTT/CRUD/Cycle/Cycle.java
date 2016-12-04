package uwi.dcit.AgriExpenseTT.CRUD.Cycle;

/**
 * Created by shivr on 12/2/2016.
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
    private String county;

    public Cycle(Integer cropId, String landType, String cropName, String harvestType, Integer landAmount, Integer date, Double totalSpent, Double harvestAmount, String county) {
        this.cropId = cropId;
        this.landType = landType;
        this.cropName = cropName;
        this.harvestType = harvestType;
        this.landAmount = landAmount;
        this.date = date;
        this.totalSpent = totalSpent;
        this.harvestAmount = harvestAmount;
        this.county = county;
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public boolean isValidObject(){
        if(this.cropId == -1){
            return false;
        }
        return true;
    }
}
