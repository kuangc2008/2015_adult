package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by kuangcheng01 on 2016/3/16.
 */
public class BinaryApiTestActivity extends Activity {

    /**
     * The digits for every supported radix.
     */
    private static final char[] DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    };

    private static final char[] UPPER_CASE_DIGITS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://api.meituan.com/maitonapi/MaitonorderApiService/v0?__vhost=api.maiton.meituan.com&utm_source=undefined&utm_medium=android&utm_term=351&version_name=6.5.1&utm_content=862163020369974&utm_campaign=AgroupBgroupC0E7422062790620600576_a4947284_c1_e916900898408260537_npoi20033%3A20062Ghomepage_category1_1__a1__gfood__hpoilist__i1&ci=1&uuid=AB6AF80399EDB227C2BF7CD654B1DB1A451B5F93DB0845EF2DA868B978A86E92&msid=8621630203699741457313130545&__skua=a21c6bc7967e8c0ac63aa2e468cd67f4&__skck=6a375bce8c66a0dc293860dfa83833ef&__skts=1458118273050&__skno=0ab4dca7-61f4-44c4-b34e-306e59dcd04f&__skcy=dEGrQ%2BjbhablDRB3Xi1eGVLKYvQ%3D";
                byte[] bytes = Base64.decode("yMABAAAAABAAAAA9CWNsaWVudEtleQVncm91cIABAAEAAAAKbWFpdG9uSW5mbwAAAAEMAAEKAAIAAAAAAAAACgsAAQAAAAACAAQBAgADAQIABQECAAYBAAA=", Base64.DEFAULT);

                for(int i=0; i<bytes.length; i++) {

                    //16进制
//                    Log.e("kcc", byteToHexString(bytes[i], false));


                    //二进制


                }



                byte b1 = (byte) 129;
                String s1 = String.format("%8s", Integer.toBinaryString(b1 & 0xFF)).replace(' ', '0');
                System.out.println(s1); // 10000001
                Log.e("kcc", s1);

                byte b2 = (byte) 2;
                String s2 = String.format("%8s", Integer.toBinaryString(b2 & 0xFF)).replace(' ', '0');
                System.out.println(s2); // 00000010
                Log.e("kcc", s2);

                byte b3 = (byte) -1;
                String s3 = String.format("%8s", Integer.toBinaryString(b3 & 0xFF)).replace(' ', '0');
                System.out.println(s3); // 00000010
                Log.e("kcc", s3);

                String ss = byteArryToBinaryString(bytes);
                Log.e("kcc", ss + "  len->" + ss.length());

                String ss2 = byteArryToHexString(bytes);
                Log.e("kcc", ss2 + "  len->" + ss2.length());


//                String result = httpPost(url, bytes);
//                Log.e("kcc", "result->" + result);
            }
        }).start();
    }


    public static String byteArryToBinaryString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for(byte number : bytes) {
            String numStr = String.format("%8s", Integer.toBinaryString(number & 0xFF)).replace(' ', '0');
            sb.append(numStr);
        }

        return sb.toString();
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String byteArryToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }




    public static String byteToHexString(byte b, boolean upperCase) {
        char[] digits = upperCase ? UPPER_CASE_DIGITS : DIGITS;
        char[] buf = new char[2]; // We always want two digits.
        buf[0] = digits[(b >> 4) & 0xf];
        buf[1] = digits[b & 0xf];
        return new String(buf);
    }

    public static String httpPost(String strURL, byte[] sData) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();

            // //设置连接属性
            connection.setDoOutput(true);// 使用 URL 连接进行输出
            connection.setDoInput(true);// 使用 URL 连接进行输入
            connection.setUseCaches(false);// 忽略缓存
            connection.setRequestMethod("POST");// 设置URL请求方法
            connection.setReadTimeout(2000);
            connection.setConnectTimeout(2000);

            byte[] data = sData;

            // 设置请求属性
            // 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
            connection.setRequestProperty("Accept", "application/x-thrift");
            connection.setRequestProperty("Content-Type",
                    "application/x-thrift");


            // 建立输出流，并写入数据
            OutputStream outputStream;

            outputStream = connection.getOutputStream();

            outputStream.write(data);
            outputStream.close();

            // 获得响应状态
            int responseCode = connection.getResponseCode();

            if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
                // 当正确响应时处理数据
                StringBuffer sb = new StringBuffer();
                String readLine;
                BufferedReader responseReader;
                // 处理响应流，必须与服务器响应流输出的编码一致
                responseReader = new BufferedReader(new InputStreamReader(
                        connection.getInputStream(), "utf-8"));

                while ((readLine = responseReader.readLine()) != null) {
                    sb.append(readLine).append("\n");
                }

                return sb.toString();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
