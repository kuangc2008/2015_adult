package com.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.Format;
import java.text.Normalizer;
import java.util.Formatter;

/**
 * Created by chengkuang on 15/7/31.
 */
public class WIfiUtils {


    public static String getWifiAddr(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wm.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
//        return android.text.format.Formatter.formatIpAddress(ip);

        final ByteBuffer byteBuffer = ByteBuffer.allocate(4);

        Log.i("kcc", "ip int is->" + Integer.toHexString(ip));
        //6500a8c0

        //前面在高，就是高字节；  前面在低，就是低字节
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putInt(ip);
        try {
            return InetAddress.getByAddress(byteBuffer.array()).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
