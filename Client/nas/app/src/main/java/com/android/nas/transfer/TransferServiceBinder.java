package com.android.nas.transfer;

import android.os.Binder;

public class TransferServiceBinder extends Binder {
    private TranseferService mTranseferService = null;
    public void TransferServiceBinder(TranseferService transeferService){ mTranseferService = transeferService; }
    public TranseferService getService() { return mTranseferService; }

    public void startTask(int fileNu){
        //启动一个Task
    }
}
