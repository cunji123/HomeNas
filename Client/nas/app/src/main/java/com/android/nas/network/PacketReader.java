package com.android.nas.network;

import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import android.text.PrecomputedText;

import com.android.nas.util.LogUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

import javax.xml.transform.Result;

public class PacketReader extends AsyncTask<Void, Void, Void> {
    private InputStream mInputStream = null;
    PacketReader(Socket socket){
        ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.fromSocket(socket);
        mInputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
        LogUtils.myLod(PacketReader.class,"PacketReader","");
    }

    @Override
    protected Void doInBackground(Void... voids) {
        LogUtils.myLod(PacketReader.class,"doInBackground","");
        while(true){

            return null;
        }

    }
    @Override
    protected void onCancelled() {
        LogUtils.myLod(PacketReader.class,"onCancelled","cancel packet reader");
        return;
    }

}
