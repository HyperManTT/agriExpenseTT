package uwi.dcit.AgriExpenseTT.CRUD.ResourcePurchase;

import android.content.ContentValues;
import android.database.Cursor;

import uwi.dcit.AgriExpenseTT.CRUD.ObjectMapper;

/**
 * Created by shivr on 12/4/2016.
 */

public class ResourcePurchase extends ObjectMapper{


    private String type;
    private String quantifier;
    private Double quantity;
    private Double quantityRemaining;
    private Double cost;
    private Long date;
    private String resourceName;

    public ResourcePurchase(Double quantity, String quantifier, String type, Double cost, Long date, String resourceName) {
        super(-1);
        this.quantity = quantity;
        this.quantifier = quantifier;
        this.type = type;
        this.cost = cost;
        this.date = date;
        this.resourceName = resourceName;
        this.quantityRemaining = quantity;
    }

    public ResourcePurchase(){
        super(-1);
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues cv= new ContentValues();
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_RESID, id);
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_TYPE, type);
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QUANTIFIER, quantifier);
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QTY, quantity);
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_COST, cost);
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_REMAINING, quantityRemaining);
        cv.put(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_RESOURCE, resourceName);
        return cv;
    }

    @Override
    public void setCursorValues(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry._ID)));
        this.setType(cursor.getString(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_TYPE)));
        this.setQuantifier(cursor.getString(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QUANTIFIER)));
        this.setQuantity(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_QTY)));
        this.setCost(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_COST)));
        this.setQuantityRemaining(cursor.getDouble(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_REMAINING)));
        this.setDate(cursor.getLong(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_DATE)));
        this.setResourceName(cursor.getString(cursor.getColumnIndex(ResourcePurchaseContract.ResourcePurchaseEntry.RESOURCE_PURCHASE_RESOURCE)));
    }

    @Override
    public boolean isValidObject() {
        return false;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantifier() {
        return quantifier;
    }

    public void setQuantifier(String quantifier) {
        this.quantifier = quantifier;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getQuantityRemaining() {
        return quantityRemaining;
    }

    public void setQuantityRemaining(Double quantityRemaining) {
        this.quantityRemaining = quantityRemaining;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
