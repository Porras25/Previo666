package com.adquem.grupologistics.utilities;

import com.adquem.grupologistics.adapters.Adp_ThreadedSync_Sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Utl_SyncService extends Service {
	private static Adp_ThreadedSync_Sync sSyncAdapter;
	private static final Object sSyncAdapterLock = new Object();

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		synchronized (sSyncAdapterLock) {
			if (sSyncAdapter == null) {
				sSyncAdapter = new Adp_ThreadedSync_Sync(getApplicationContext(), true);
			}
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return sSyncAdapter.getSyncAdapterBinder();
	}

}
