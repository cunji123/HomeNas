package com.android.nas.guestManager;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.android.nas.transfer.TransferStateMachine;
import com.android.nas.util.LogUtils;
import com.android.nas.util.State;
import com.android.nas.util.StateMachine;
import com.android.nas.network.NetWorkUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class GuestManagerStateMachine extends StateMachine {
    private static String tag = "GuestManagerStateMachine";
    private static Context mContext = null;
    public static final int MSG_CONNECTED = 1;
    public static final int MSG_RETRY = 2;
    public static final int MSG_ADDTASK = 3;
    public static final int MSG_DISCONNECT = 4;
    private DisConnectedState mDisConnectedState = new DisConnectedState();
    private ConnectedState mConnectedState = new ConnectedState();
    private LoginState mLoginState = new LoginState();
    private UnLoginState mUnLoginState = new UnLoginState();
    public GuestManagerStateMachine(String name) {
        super(name);
        addState(mDisConnectedState);
        addState(mConnectedState);
            addState(mLoginState,mConnectedState);
            addState(mUnLoginState,mConnectedState);

        setInitialState(mDisConnectedState);
        start();
    }

    class DisConnectedState extends State{
        public void enter() {
            LogUtils.myLod(GuestManagerStateMachine.class,"enter", "entering DisConnectedState");
            if(!NetWorkUtils.isNetworkConnected(mContext) && NetWorkUtils.getIpByName(GuestManagerService.serverHost) == null){
                LogUtils.myLod(GuestManagerStateMachine.class,"DisConnectedState","net is not avaliable");
                GuestManagerStateMachine.this.sendMessageDelayed(MSG_RETRY,3000);
                return;
            }

            GuestManagerService.guestManagerSocket = NetWorkUtils.connTransSocket(GuestManagerService.tag,GuestManagerService.serverHost,GuestManagerService.portArr);
            if(GuestManagerService.guestManagerSocket != null){
                sendMessage(MSG_CONNECTED);
            }else{
                LogUtils.myLod(GuestManagerStateMachine.class,"DisConnectedState","connect not success");
                GuestManagerStateMachine.this.sendMessageDelayed(MSG_RETRY,1000);
            }

        }

        public boolean processMessage(Message msg){
            switch(msg.what){
                case MSG_CONNECTED:
                    transitionTo(mConnectedState);
                    break;
                case MSG_RETRY:
                    transitionTo(mDisConnectedState);
                    break;
                default:
                    LogUtils.myLod(GuestManagerStateMachine.class,"processMessage","DisConnectedState: invalid operation");
                    return false;
            }
            return true;
        }

        public void exit() {
            LogUtils.myLod(GuestManagerStateMachine.class,"ConnectedState","exit DisConnectedState");
        }
    }
    class ConnectedState extends State{
        public void enter() {
            LogUtils.myLod(GuestManagerStateMachine.class,"ConnectedState","entering ConnectedState");
            if(!GuestManagerService.guestManagerSocket.isConnected()){
                LogUtils.myLod(GuestManagerStateMachine.class,"ConnectedState","ConnectedState: transferSocket isn‘t connected");
                transitionTo(mDisConnectedState);
            }

        }
        public boolean processMessage(Message msg){
            switch(msg.what){
                case MSG_DISCONNECT:
                    break;
                case MSG_ADDTASK:
                    break;
                default:
                    Log.d(tag, "ConnectedState: invalid operation");
                    return false;
            }
            return true;
        }
        public void exit() {
            Log.d(tag, "exit ConnectedState");
        }
    }

    class LoginState extends State{
        public void enter() {
            Log.d(tag, "entering LoginState");
        }
        public boolean processMessage(Message msg){
            switch(msg.what){
                case MSG_DISCONNECT:
                    break;
                case MSG_ADDTASK:
                    break;
                default:
                    Log.d(tag, "ConnectedState: invalid operation");
                    return false;
            }
            return true;
        }
        public void exit() {
            Log.d(tag, "exit LoginState");
        }
    }
    class UnLoginState extends State{
        public void enter() {
            Log.d(tag, "entering UnLoginState");
        }
        public boolean processMessage(Message msg){
            switch(msg.what){
                case MSG_DISCONNECT:
                    break;
                case MSG_ADDTASK:
                    break;
                default:
                    Log.d(tag, "ConnectedState: invalid operation");
                    return false;
            }
            return true;
        }
        public void exit() {
            Log.d(tag, "exit UnLoginState");
        }
    }
    
    public static GuestManagerStateMachine makeStateMachine(Context context){
        mContext = context;
        GuestManagerStateMachine guestManagerStateMachine = new GuestManagerStateMachine("GuestManager");
        return guestManagerStateMachine;
    }

}
