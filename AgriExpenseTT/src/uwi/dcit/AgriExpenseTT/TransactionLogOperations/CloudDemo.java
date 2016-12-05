package uwi.dcit.AgriExpenseTT.TransactionLogOperations;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import uwi.dcit.agriexpensesvr.cycleUseApi.model.CycleUse;
import uwi.dcit.agriexpensesvr.rPurchaseApi.model.RPurchase;
import uwi.dcit.agriexpensesvr.translogApi.model.Key;
import uwi.dcit.agriexpensesvr.translogApi.model.TransLog;
import uwi.dcit.agriexpensesvr.cycleApi.model.Cycle;

/**
 * Created by ander on 12/3/2016.
 */
public class CloudDemo {

    private List<TransLog> translogs;
    private List<Cycle> cycles;
    private List<RPurchase> rPurchases;
    private List<CycleUse> cycleUses;

    public CloudDemo(){

        translogs = new ArrayList<TransLog>();
        translogs.add(createTransLog("anderson", 100, "personal", "ins", 100, "cropCycle", 1480810277435l));
        translogs.add(createTransLog("anderson", 101, "personal", "ins", 101, "resPurchases", 1480810277435l));
        translogs.add(createTransLog("anderson", 102, "personal", "ins", 102, "cycleResources", 1480810200000l));

        cycles = new ArrayList<Cycle>();
        cycles.add(createCycle("anderson", "personal", 1.0, "NA", 1, "fIG", 1.0, "NA", 1, 2.0, "NA", 100l, 100.0));

        rPurchases = new ArrayList<RPurchase>();
        rPurchases.add(createRPurchase("anderson", 100.00, "ElementName", "personal", 2, 10.0, 10.0, "ML", 6, "Fert"));

        cycleUses = new ArrayList<CycleUse>();
        cycleUses.add(createCycleUse("anderson", 1.0, 10.0, 1, 3, "personal", 2, "ML"));
    }

    public List<TransLog> getTranslogs(){
        return translogs;
    }

    public TransLog createTransLog(String account, Integer id, String keyrep, String operation, Integer rowId, String tableKind, Long transTime){
        TransLog t = new TransLog();
        t.setAccount(account);
        t.setId(id);
        t.setKeyrep(keyrep);
        t.setOperation(operation);
        t.setRowId(rowId);
        t.setTableKind(tableKind);
        t.setTransTime(transTime);
        t.setKey(new Key());

        return t;
    }

    public CycleUse createCycleUse(String account, Double amount, Double cost, Integer cycleId, Integer id, String keyrep, Integer purchaseId, String resource){

        CycleUse use = new CycleUse();
        use.setAccount(account);
        use.setAmount(amount);
        use.setCost(cost);
        use.setCycleid(cycleId);
        use.setId(id);
        use.setKeyrep(keyrep);
        use.setPurchaseId(purchaseId);
        use.setResource(resource);
        use.setKey(new uwi.dcit.agriexpensesvr.cycleUseApi.model.Key());

        return use;
    }

    public Cycle createCycle(String account, String keyrep, Double costPer, String county, Integer cropId, String cropName, Double harvestAmt, String harvestType, Integer id, Double landQty, String landType, Long startDate, Double totalSpent){

        Cycle cycle = new Cycle();
        cycle.setAccount(account);
        cycle.setKeyrep(keyrep);
        cycle.setCostPer(costPer);
        cycle.setCounty(county);
        cycle.setCropId(cropId);
        cycle.setCropName(cropName);
        cycle.setHarvestAmt(harvestAmt);
        cycle.setHarvestType(harvestType);
        cycle.setId(id);
        cycle.setLandQty(landQty);
        cycle.setLandType(landType);
        cycle.setStartDate(startDate);
        cycle.setTotalSpent(totalSpent);
        cycle.setKey(new uwi.dcit.agriexpensesvr.cycleApi.model.Key());

        return cycle;
    }

    public RPurchase createRPurchase(String account, Double cost, String elementName, String keyrep, Integer pId, Double qty, Double qtyRemaining, String quantifier, Integer resourceId, String type){

        RPurchase purchase = new RPurchase();
        purchase.setAccount(account);
        purchase.setCost(cost);
        purchase.setElementName(elementName);
        purchase.setKeyrep(keyrep);
        purchase.setPId(pId);
        purchase.setQty(qty);
        purchase.setQtyRemaining(qtyRemaining);
        purchase.setQuantifier(quantifier);
        purchase.setResourceId(resourceId);
        purchase.setType(type);
        purchase.setKey(new uwi.dcit.agriexpensesvr.rPurchaseApi.model.Key());


        return purchase;
    }

    /* this method simulates getting a particular cycle from the cloud platform. */
    public Cycle getCycle(String namespace, String keyrep){
        for(Cycle c : cycles){
            if(c.getAccount().equalsIgnoreCase(namespace) && c.getKeyrep().equalsIgnoreCase(keyrep)){
                return c;
            }
        }

        return null;
    }

    public RPurchase getPurchase(String namespace, String keyrep){
        for(RPurchase p : rPurchases){
            if(p.getAccount().equalsIgnoreCase(namespace) && p.getKeyrep().equalsIgnoreCase(keyrep)){
                return p;
            }
        }

        return null;
    }

    public CycleUse getCycleUse(String namespace, String keyrep){
        for(CycleUse u : cycleUses){
            if(u.getAccount().equalsIgnoreCase(namespace) && u.getKeyrep().equalsIgnoreCase(keyrep)){
                return u;
            }
        }

        return null;
    }

    public List<TransLog> fetch_translogs(){
        Log.i("DESIGN UPDATE 1", "FETCHED TRANSLOGS FROM CLOUD.");
        return translogs;
    }
}
