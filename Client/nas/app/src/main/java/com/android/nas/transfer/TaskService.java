package com.android.nas.transfer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TaskService extends Service {
    public void onCreate(){
        //Socket连接
    }
    public IBinder onBind(Intent intent) {
        return null;
    }
}
