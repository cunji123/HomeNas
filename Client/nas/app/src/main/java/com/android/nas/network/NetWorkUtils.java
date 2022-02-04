package com.android.nas.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.nas.guestManager.GuestManagerStateMachine;
import com.android.nas.util.LogUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class NetWorkUtils {
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = mConnectivityManager.getActiveNetwork();
        if(network == null){
            LogUtils.myLod(NetWorkUtils.class,"isNetworkConnected","network is null");
            return false;
        }
        NetworkCapabilities networkCapabilities = mConnectivityManager.getNetworkCapabilities(network);
        if(networkCapabilities == null){
            LogUtils.myLod(NetWorkUtils.class,"isNetworkConnected","networkCapabilities is null");
            return false;
        }
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);

    }
    public static native boolean nativePing();
    public static InetAddress getIpByName(String serverHost){
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getByName(serverHost);
            LogUtils.myLod(NetWorkUtils.class,"connTransSocket","host addr is: " + inetAddress);
        } catch (UnknownHostException e) {
            LogUtils.myLod(NetWorkUtils.class,"connTransSocket","Get Address failed: " + e.toString());
            return null;
        }
        return inetAddress;
    }
    public static Socket connTransSocket(String tag, String serverHost,int portArr[]){
        //String name=Thread.currentThread().getStackTrace()[3].getClassName();
        InetAddress inetAddress = getIpByName(serverHost);
        if(inetAddress == null){
            return null;
        }
        Socket socket = null;
        for(int i = 0;i<portArr.length;i++){
            for(int j = 0;j<3 ;j++){
                socket = new Socket();
                try {
                    socket.connect(new InetSocketAddress(inetAddress,portArr[i] + 50000),1500);
                    LogUtils.myLod(NetWorkUtils.class,"connTransSocket","Connect to: " + String.valueOf(portArr[i]));
                    return socket;
                } catch (IOException e) {
                    LogUtils.myLod(NetWorkUtils.class,"connTransSocket","Connect to port fail: " + e.toString());
                }
            }
        }

        return null;
    }
}
