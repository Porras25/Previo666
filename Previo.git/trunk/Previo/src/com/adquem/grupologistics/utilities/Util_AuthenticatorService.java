package com.adquem.grupologistics.utilities;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;



public class Util_AuthenticatorService extends Service {
    @Override
    public IBinder onBind(Intent intent) {

        Util_Authenticator authenticator = new Util_Authenticator(this);
        return authenticator.getIBinder();
    }
}
