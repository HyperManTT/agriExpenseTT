package uwi.dcit.AgriExpenseTT.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uwi.dcit.AgriExpenseTT.TransactionLogOperations.CloudDemo;
import uwi.dcit.AgriExpenseTT.TransactionLogOperations.Item;
import uwi.dcit.AgriExpenseTT.TransactionLogOperations.ItemFactory;
import uwi.dcit.AgriExpenseTT.cloud.CloudInterface;
import uwi.dcit.AgriExpenseTT.models.TransactionLogContract;
import uwi.dcit.AgriExpenseTT.models.UpdateAccountContract;
import uwi.dcit.agriexpensesvr.translogApi.model.TransLog;
import uwi.dcit.agriexpensesvr.translogApi.model.TransLogCollection;
import uwi.dcit.agriexpensesvr.upAccApi.model.UpAcc;


public class TransactionLog {
	SQLiteDatabase db;
	DbHelper dbh;
	Context context;
	ItemFactory itemFactory;
	CloudDemo cloudDemo = new CloudDemo();

//	public static final String TL_BEGIN="begin";
//	public static final String TL_END="end";
	public static final String TL_INS="ins";
	public static final String TL_DEL="del";
	public static final String TL_UPDATE="updt";

	//a variation of a transaction log, this records the operation and the row that was affected
	//based on the operation associated different tables are associated
	
	public TransactionLog(DbHelper dbh,SQLiteDatabase db,Context context){
		this.dbh=dbh;
		this.db=db;
		this.context=context;
		this.itemFactory = new ItemFactory(dbh, db);

	}
	public boolean pullAllFromCloud(UpAcc cloudAcc){

		List<TransLog> translogList = new ArrayList<>();

		/* this block of code, pulls the transaction logs from the endpoint.  */
		/*
		TransLogCollection responseTranslog;
		TranslogApi.Builder builderTransLog = new TranslogApi.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		         null);         
		builderTransLog = CloudEndpointUtils.updateBuilder(builderTransLog);
        TranslogApi endpointTranslog = builderTransLog.build();
		try {
			responseTranslog = endpointTranslog.getAllTranslog(cloudAcc.getAcc()).execute();
		} catch (IOException e) {e.printStackTrace(); return false;}

		List<TransLog> translogList = responseTranslog.getItems();
		*/

		translogList = cloudDemo.getTranslogs();

		ContentValues cv;
		//dbh.dropTables(db);
		//dbh.createDB(db);

		/* for each item in transloglist, insert into the local database. */
		for(TransLog t:translogList){

			/* CRUD operations rarely change, the coupling is not an issue. */
			if(t.getOperation().equalsIgnoreCase(TransactionLog.TL_INS)){
				logInsertLocal(t, t.getAccount());
			}
			else if(t.getOperation().equalsIgnoreCase(TransactionLog.TL_UPDATE)){
				logUpdateLocal(t, t.getAccount());
			}
			else if(t.getOperation().equalsIgnoreCase(TransactionLog.TL_DEL)){
				logDeleteLocal(t, t.getAccount());
			}

			cv = new ContentValues();
			cv.put(TransactionLogContract.TransactionLogEntry._ID, t.getId());
			cv.put(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_TABLE, t.getTableKind());
			cv.put(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_ROWID, t.getRowId());
			cv.put(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_OPERATION, t.getOperation());
			cv.put(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_TRANSTIME, t.getTransTime());
			db.insert(TransactionLogContract.TransactionLogEntry.TABLE_NAME, null, cv);
			DbQuery.updateAccount(db, t.getTransTime());
		}

		/* update account information. */
		cv=new ContentValues();
		cv.put(UpdateAccountContract.UpdateAccountEntry.UPDATE_ACCOUNT_ACC, cloudAcc.getAcc());
		cv.put(UpdateAccountContract.UpdateAccountEntry.UPDATE_ACCOUNT_CLOUD_KEY, cloudAcc.getKeyrep());
		db.update(UpdateAccountContract.UpdateAccountEntry.TABLE_NAME, cv, UpdateAccountContract.UpdateAccountEntry._ID+"=1", null);

		
		return true;
		
	}
	
	/* this function inserts an entry into the local database for transaction log. */
	public int insertTransLog(String table,int rowId,String operation){
		ContentValues cv=new ContentValues();
		long unixTime = System.currentTimeMillis() / 1000L;
		cv.put(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_OPERATION, operation);
		cv.put(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_TABLE, table);
		cv.put(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_ROWID, rowId);
		cv.put(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_TRANSTIME, unixTime);
		db.insert(TransactionLogContract.TransactionLogEntry.TABLE_NAME, null, cv);
		int row=DbQuery.getLast(db, dbh, TransactionLogContract.TransactionLogEntry.TABLE_NAME);
		DbQuery.updateAccount(db, unixTime);
		return row;//returns the row number of the record just inserted
	}

	/* this function will update the cloud based on transactions after lastupdate, this function was already decoupled. */
	public void updateCloud(long lastUpdate){
		//get all the transactions that happened after the cloud's last update
		System.out.println("starting Qquery");
		String code="select * from "+ TransactionLogContract.TransactionLogEntry.TABLE_NAME+
				" where "+ TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_TRANSTIME+">="+lastUpdate+";";
		Cursor cursor=db.rawQuery(code, null);
		
		//will now attempt to recreate such operations on the cloud
		while(cursor.moveToNext()){
			System.out.println("records");
			String operation=cursor.getString(cursor.getColumnIndex(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_OPERATION));
			String table=cursor.getString(cursor.getColumnIndex(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_TABLE));
			int rowId=cursor.getInt(cursor.getColumnIndex(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_ROWID));
			//now that i have the table, row and operation i can insert a record into the redo log
			//by inserting it into the redo log I can be assured it will be inserted when possible
			DbQuery.insertRedoLog(db, dbh, table, rowId, operation);
		}
        cursor.close();
		//CloudInterface c=new CloudInterface(context, db, dbh);
		//c.flushToCloud();
	}

	/* this function uploads all entries in the local database for an account, as if the account is completely new. */
	public boolean createCloud(String namespace){

		/* this block of code, creates cloud entries for each item stored in the local database. */
		Map<String, Item> map  = itemFactory.getItemList();

		for(Item item : map.values()){
			item.createCloud(namespace);
		}

		/* this block of code uploads all local transaction log information to the cloud platform. */
		/*
		TranslogApi.Builder builderTranslog = new TranslogApi.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		         null);

        TranslogApi endpointTranslog = builderTranslog.build();

		String code = "select * from "+ TransactionLogContract.TransactionLogEntry.TABLE_NAME;
		CloudInterface cloudIF=new CloudInterface(context, db, dbh);
		Cursor cursor = db.rawQuery(code, null);
		while(cursor.moveToNext()){
			TransLog t=new TransLog();
			t.setId(cursor.getInt(cursor.getColumnIndex(TransactionLogContract.TransactionLogEntry._ID)));
			t.setAccount(namespace);
			t.setOperation(cursor.getString(cursor.getColumnIndex(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_OPERATION)));
			t.setTableKind(cursor.getString(cursor.getColumnIndex(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_TABLE)));
			t.setRowId(cursor.getInt(cursor.getColumnIndex(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_ROWID)));
			t.setTransTime(cursor.getLong(cursor.getColumnIndex(TransactionLogContract.TransactionLogEntry.TRANSACTION_LOG_TRANSTIME)));
			t.setKeyrep(DbQuery.getKey(db, dbh, t.getTableKind(), t.getRowId()));
			try {
				System.out.println(t.toPrettyString());
				t=endpointTranslog.insertTransLog(t).execute();
				cloudIF.updateUpAccC(t.getTransTime());
			} catch (IOException e) {e.printStackTrace();
				return false;}
		}
        cursor.close();
	*/
		/* update account on local database. */
		ContentValues cv=new ContentValues();
		cv.put(UpdateAccountContract.UpdateAccountEntry.UPDATE_ACCOUNT_SIGNEDIN, 1);
		db.update(UpdateAccountContract.UpdateAccountEntry.TABLE_NAME, cv, UpdateAccountContract.UpdateAccountEntry._ID+"=1", null);

		return true;
	}
	
	/* this function starts a new asyntask to run database operations. */
	public void logsUpdateLocal(String namespace,long lastLocalUpdate){
		new UpdateLocal(namespace,lastLocalUpdate).execute();
	}

	/* this async task performs database operations. */
	public class UpdateLocal extends AsyncTask<Void,Void,Void>{

		String namespace;
		long lastLocalUpdate;
		CloudDemo cloudDemo;

		public UpdateLocal(String namespace,long lastLocalUpdate){
			this.namespace=namespace;
			this.lastLocalUpdate=lastLocalUpdate;
			this.cloudDemo = new CloudDemo();
		}
		@Override
		protected Void doInBackground(Void... params) {

			List<TransLog> transList = null;
			TransLogCollection tlist = new TransLogCollection();

			/* this block of code connects to the transaction log endpoint, and fetches the translogs for a particular user.  */

			/*
           TranslogApi.Builder builder = new TranslogApi.Builder(
			         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
			         null);         
			builder = CloudEndpointUtils.updateBuilder(builder);
            TranslogApi endpoint = builder.build();
			tlist = null;

			try {tlist = endpoint.logs(lastLocalUpdate, namespace).execute();}
			catch (Exception e) {e.printStackTrace();}
			*/

			if (tlist != null) {

				/* this line of code fetchs the list of translog objects returned from the endpoint. */
               /*List<TransLog> transList = tlist.getItems(); */

				/* use the translogs from cloud demo for demonstration purposes. */
				transList = cloudDemo.getTranslogs();

				/* for each translog object, insert the data into the device database. */
                for (TransLog tLog : transList) {
                    if (tLog.getOperation().equals(TransactionLog.TL_INS))
                        logInsertLocal(tLog, namespace);
                    else if (tLog.getOperation().equals(TransactionLog.TL_UPDATE))
                        logUpdateLocal(tLog, namespace);
                    else if (tLog.getOperation().equals(TransactionLog.TL_DEL)) {
                        logDeleteLocal(tLog, namespace);
                    }
                }
            }
			return null;
		}
	}

	/* this function wiil insert into the local database for a particular item, data is retrieved from the cloud. */
	public void logInsertLocal (TransLog t,String namespace){
		Item item = itemFactory.GetItem(t.getTableKind());
		item.insert(t, namespace);
	}

	/* this function will update the local database for a particular item, data is retrieved from cloud. */
	public void logUpdateLocal(TransLog t,String namespace){
		Item item = itemFactory.GetItem(t.getTableKind());
		item.update(t, namespace);
	}


	/* this function deletes a local entry given a table and row id. */
	private void logDeleteLocal(TransLog tLog, String namespace2) {
        try {
            DbQuery.deleteRecord(db, dbh, tLog.getTableKind(), tLog.getRowId());
        }catch (Exception e){e.printStackTrace();}
	}

	/* this function removes all the information from the cloud related to a particular account. */
	public void removeNamespace(String namespace){

		/* this block of code, removes all entries for each type of item stored in cloud related to a particular account. */

		Map<String, Item> map  = itemFactory.getItemList();

		for(Item item : map.values()){
			item.removeNamespace(namespace);
		}

		/* this block of code, connects to the transaction log endpoint, and deletes all entries related to a particular account. */

		/*
		TranslogApi.Builder builderTransLog = new TranslogApi.Builder(
		         AndroidHttp.newCompatibleTransport(), new JacksonFactory(),
		         null);

		builderTransLog = CloudEndpointUtils.updateBuilder(builderTransLog);
        TranslogApi endpointTranslog = builderTransLog.build();
		try {
			endpointTranslog.deleteAll(namespace).execute();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		*/

		Log.i("DESIGN UPDATE 1", "REMOVING ENTRIES IN CLOUD FOR TRANSACTION LOG.");
	}
}
