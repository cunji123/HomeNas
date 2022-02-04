package com.android.nas.guestManager;

import android.os.Binder;

public class GuestManagerBinder extends Binder {
    private GuestManagerService mGuestManagerService;
    GuestManagerBinder(GuestManagerService guestManagerService){
        mGuestManagerService = guestManagerService;
    }
    public GuestManagerService getService(){
        return mGuestManagerService;
    }
    public boolean isLoginState(){
        return mGuestManagerService.mGuestManagerStateMachine.getCurrentState().getName().equals("LoginState");
    }
}
