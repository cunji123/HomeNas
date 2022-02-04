package com.android.nas.guestManager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


import com.android.nas.util.LogUtils;

import java.net.Socket;


public class GuestManagerService extends Service {
    protected static String tag = "GuestManagerService";
    public GuestManagerBinder mBinder = null;
    protected static Socket guestManagerSocket = null;
    protected static String serverHost = "jasbdter.com";
    protected static int portArr[] = {0,1,2,3,4,5,6,7,8,9};
    protected static GuestManagerStateMachine mGuestManagerStateMachine = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mBinder = new GuestManagerBinder(GuestManagerService.this);
        mGuestManagerStateMachine = GuestManagerStateMachine.makeStateMachine(getApplicationContext());
    }

    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
