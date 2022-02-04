package com.android.nas.transfer;

import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.android.nas.util.State;
import com.android.nas.util.StateMachine;
import com.android.nas.network.NetWorkUtils;

public class TransferStateMachine extends StateMachine {
    private static String tag = "TransferStateMachine";
    private static Context mContext = null;
    public static final int MSG_CONNECTED = 1;
    public static final int MSG_RETRY = 2;
    public static final int MSG_ADDTASK = 3;
    public static final int MSG_DISCONNECT = 4;
    public static final int MSG_STOPTASK =5;
    public static final int MSG_STARTTASK = 6;
    public static final int MSG_STOPALL = 7;
    public static final int MSG_STARTALL = 8;
    public static final int MSG_CANCELTASK = 9;
    public static final int MSG_CANCELALL = 10;
    private State mInitializingState = new InitializingState();
    private State mConnectedState = new ConnectedState();
    private State mDisConnectedState = new DisConnectedState();
    private State mTranseferState = new TranseferState();
    protected TransferStateMachine(String name) {
        super(name);
        addState(mInitializingState);
        addState(mConnectedState);
        addState(mDisConnectedState);
        addState(mTranseferState);
        setInitialState(mInitializingState);
        start();
    }

    class InitializingState extends State{
        public void enter() {
            Log.d(tag, "entering InitializingState");
            if(!NetWorkUtils.isNetworkConnected(mContext)){
                Log.d(tag, "net is not avaliable");
                sendMessageDelayed(MSG_RETRY,3);
            }else{
                TranseferService.transferSocket = NetWorkUtils.connTransSocket(TranseferService.tag,TranseferService.serverHost,TranseferService.portArr);
                if(TranseferService.transferSocket != null){
                    sendMessage(MSG_CONNECTED);
                }else{
                    sendMessageDelayed(MSG_RETRY,1);
                }
            }
        }

        public boolean processMessage(Message msg){
            switch(msg.what){
                case MSG_CONNECTED:
                    transitionTo(mConnectedState);
                    break;
                case MSG_RETRY:
                    transitionTo(mInitializingState);
                    break;
                default:
                    Log.d(tag, "InitializingState: invalid operation");
                    return false;
            }
            return true;
        }

        public void exit() {
            Log.d(tag, "exit InitializingState");
        }
    }

    class ConnectedState extends State{
        public void enter() {
            Log.d(tag, "entering ConnectedState");
            if(!TranseferService.transferSocket.isConnected()){
                Log.d(tag, "ConnectedState: transferSocket isn‘t connected");
                transitionTo(mDisConnectedState);
            }
        }
        public boolean processMessage(Message msg){
            switch(msg.what){
                case MSG_DISCONNECT:
                    break;
                case MSG_ADDTASK:
                    transitionTo(mTranseferState);
                    break;
                default:
                    Log.d(tag, "InitializingState: invalid operation");
                    return false;
            }
            return true;
        }
        public void exit() {
            Log.d(tag, "exit ConnectedState");
        }
    }

    class DisConnectedState extends State{
        public void enter() {
            Log.d(tag, "entering DisConnectedState");
        }
    }

    class TranseferState extends State{
        public void enter() {
            Log.d(tag, "entering TranseferState");
        }

        @Override
        public boolean processMessage(Message msg) {
            switch(msg.what){
                case MSG_STOPTASK:
                    break;
                case MSG_STARTTASK:
                    break;
                case MSG_STOPALL:
                    break;
                case MSG_STARTALL:
                    break;
                case MSG_CANCELTASK:
                    break;
                case MSG_CANCELALL:
                    break;
                default:
                    Log.d(tag, "InitializingState: invalid operation");
                    return false;

            }
            return true;
        }
        public void exit() {
            Log.d(tag, "exit TranseferState");
        }
    }



    public static TransferStateMachine makeStateMachine(Context context){
        mContext = context;
        TransferStateMachine transferStateMachine = new TransferStateMachine("Transfer");
        return transferStateMachine;
    }
}
