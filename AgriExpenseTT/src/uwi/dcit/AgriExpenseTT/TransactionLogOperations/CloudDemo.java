package uwi.dcit.AgriExpenseTT.TransactionLogOperations;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import uwi.dcit.agriexpensesvr.cycleUseApi.model.CycleUse;
import uwi.dcit.agriexpensesvr.rPurchaseApi.model.RPurchase;
import uwi.dcit.agriexpensesvr.translogApi.model.Key;
import uwi.dcit.agriexpensesvr.translogApi.model.TransLog;
import uwi.dcit.agriexpensesvr.cycleApi.model.Cycle;
import uwi.dcit.agriexpensesvr.upAccApi.model.UpAcc;

/**
 * Created by ander on 12/3/2016.
 */
public class CloudDemo {

    private List<TransLog> translogs;
    private List<Cycle> cycles;
    private List<RPurchase> rPurchases;
    private List<CycleUse> cycleUses;

    public CloudDemo(){

        /* this is just to demo that the new design works, create translogs to act as though its new data from the cloud. */

        /* for each new translog, create the corresponding item (cycle, purchase, cycleuse) with the correct namespace and keyrep */

        translogs = new ArrayList<TransLog>();
        translogs.add(createTransLog("anderson", 100, "mycycle1", "ins", 100, "cropCycle", 1480810277435l));
        translogs.add(createTransLog("anderson", 101, "mycycle2", "ins", 101, "cropCycle", 1480810277435l));
        translogs.add(createTransLog("anderson", 102, "mypurchase1", "ins", 102, "resPurchases", 1480810277435l));
        translogs.add(createTransLog("anderson", 103, "mypurchase1", "ins", 103, "resPurchases", 1480810277435l));
        //translogs.add(createTransLog("anderson", 101, "personal", "ins", 101, "resPurchases", 1480810277435l));
        //translogs.add(createTransLog("anderson", 102, "personal", "ins", 102, "cycleResources", 1480810200000l));

        cycles = new ArrayList<Cycle>();
        cycles.add(createCycle("anderson", "mycycle1", 20.00, "St Patrick", 70, "BROCCOLI", 100.00, "Lb", 1, 20.0, "Acre", Long.getLong("1480910400000"), 0.00));
        cycles.add(createCycle("anderson", "mycycle2", 20.00, "St Patrick", 40, "CELERY", 100.00, "Lb",  2, 20.0, "Acre", Long.getLong("1480910400000"), 0.00));


        rPurchases = new ArrayList<RPurchase>();
        rPurchases.add(createRPurchase("anderson", 100.00, "Soil amendment", "mypurchase1", 2, 10.0, 10.0, "Bag", 2, "Compost"));
        rPurchases.add(createRPurchase("anderson", 100.00, "Soil amendment", "mypurchase2", 3, 100.0, 10.0, "Bag", 3, "Gypsum"));

        cycleUses = new ArrayList<CycleUse>();
        //cycleUses.add(createCycleUse("anderson", 1.0, 10.0, 1, 3, "personal", 2, "ML"));
    }

    /* inorder to test the different functionality of the transaction log, change the last updated times of the local and cloud account. */

    /*
        Instructions for testing.
        1. How to test that local updates using data from the cloud.
        Answer: Ensure that the last updated time for the cloud account is greater than the local account time.

        2. How to test that the cloud can be updated using data from the local device. (this is handled by the cloud interface.)
        Answer: Ensure that the last updated time for the local account is greater than the cloud account time.

        3. How to test that the cloud data can be overridden, and local data written to the cloud.
        Answer: Ensure that the last updated time for the local account is greater than the cloud account time,
                and in uncomment the localAcc.setAcc(null) from the getLocalAccount function.
                Finally, select the override cloud option in the pop up.

        4. How to test that the local data can be overridden, and the cloud data written to the local device.
                Answer: Ensure that the last updated time for the local account is greater than the cloud account time,
                and in uncomment the localAcc.setAcc(null) from the getLocalAccount function.
                Finally, select the override local option in the pop up.

                (THIS FUNCTIONALITY DOES NOT WORK IN THE ORIGINAL APP)
                The pullallfromcloud method drops all tables, but the oncreate fails to recreate the db. this section of code is commented off,
                to show that the design works. The local data will not be cleared.

        5. How to test creating a new cloud.
            Answer: Ensure that the cloud account returned by getCloudAccount function is null

          ALL DATA IS LOGGED USING LOG.I FUNCTION, WHERE THE TAG IS "DESIGN UPDATE 1"

          ON WINDOWS OS USE COMMAND : adb logcat | find "DESIGN UPDATE 1"

    */
    public UpAcc getLocalAccount(){
        UpAcc localAcc = new UpAcc();

        localAcc.setLastUpdated(1480991154348l);
        localAcc.setSignedIn(1);
        localAcc.setAddress("No Address");
        localAcc.setCountry("Trinidad and Tobago");
        localAcc.setKeyrep("myaccount");
        localAcc.setAcc("anderson");

        //localAcc.setAcc(null);

        return localAcc;
    }

    public UpAcc getCloudAccout(){
        UpAcc acc = new UpAcc();


        acc.setLastUpdated(1480805211187l);
        acc.setSignedIn(1);
        acc.setAddress("No Address");
        acc.setCountry("Trinidad and Tobago");
        acc.setKeyrep("myaccount");
        acc.setAcc("anderson");

        return null;
        //return acc;
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
