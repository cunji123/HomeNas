package com.android.nas.transfer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.net.Socket;

public class TranseferService extends Service {
    protected static final String tag = "TransferServices";
    private static TransferServiceBinder mBinder = null;
    protected static Socket transferSocket = null;
    protected static String serverHost = "jasbdter.com";
    protected static int portArr[] = {10,11,12,13,14,15,16,17,18,19};
    private static TransferStateMachine mTransferStateMachine = null;
    public void onCreate(){
        Log.d(tag,"onCreate");
        mBinder = new TransferServiceBinder();
        //mTransferStateMachine = TransferStateMachine.makeStateMachine(getApplicationContext());
    }

    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
