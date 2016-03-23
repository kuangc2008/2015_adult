package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by kuangcheng01 on 2016/3/16.
 */
public class BinaryApiTestActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new Thread(new Runnable() {
            @Override
            public void run() {
                CandyData data = getDataFromAndroidhttpServer("http://172.24.116.44:8008/?poiid=1012");
                String result = httpPost(data.url, data.data);
                Log.e("kcc", "result->" + result);
            }
        }).start();
    }


    private static class CandyData {
        String url;  //url
        byte[] data; //二进制数据
    }

    private static CandyData getDataFromAndroidhttpServer(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (conn.getResponseCode() == 200) {

                byte[] b = new byte[conn.getContentLength()];
                conn.getInputStream().read(b);

                String post_url = conn.getHeaderField("post_url");

                CandyData data = new CandyData();
                data.url = post_url;
                data.data = b;
                return data;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
//                byte[] b = new byte[500];
//                int len = connection.getInputStream().read(b);
//                return new String(b, 0, len, "utf-8");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
