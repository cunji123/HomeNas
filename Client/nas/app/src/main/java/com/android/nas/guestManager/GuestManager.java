package com.android.nas.guestManager;

import java.net.Socket;

public class GuestManager {
    protected static String tag = "GuestManagerService";
    public GuestManagerBinder mBinder = null;
    protected static Socket guestManagerSocket = null;
    protected static String serverHost = "jasbdter.com";
    protected static int portArr[] = {0,1,2,3,4,5,6,7,8,9};
    protected static GuestManagerStateMachine mGuestManagerStateMachine = null;
}
