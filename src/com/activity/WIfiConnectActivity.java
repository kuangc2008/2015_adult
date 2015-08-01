package com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.adult_zeren.R;
import com.utils.ShellUtils;
import com.utils.WIfiUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by chengkuang on 15/7/31.
 */
public class WIfiConnectActivity extends Activity {
    private TextView mTcpPort = null;
    private Button mSetTcpPort = null;
    private Process mLogcatProcess = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi_main);
        initViews();
        getTcpPort();
    }


    private void initViews() {
        TextView wifiTV = (TextView) findViewById(R.id.wifi_ip);
        wifiTV.setText(WIfiUtils.getWifiAddr(this));

        mTcpPort = (TextView) findViewById(R.id.tcp_port);

        mSetTcpPort = (Button) findViewById(R.id.set_tcp_port);
        mSetTcpPort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTcpPort();
            }
        });
    }

    private void setTcpPort() {
        ShellUtils.execSync("setprop service.adb.tcp.port 5555");
   //     ShellUtils.execSync("stop adbd");
   //     ShellUtils.execSync("start adbd");
//        getTcpPort();

    }

    @Override
    protected void onStop() {
        super.onStop();
//        mLogcatProcess.destroy();
    }

    private void getTcpPort() {
//        mLogcatProcess = ShellUtils.execAsync("top", new ShellUtils.OnShellCallback() {
//            @Override
//            public void onGetLine(String line) {
//                Log.i("kcc", line);
//            }
//
//            @Override
//            public void onFinish() {
//                Log.i("kcc", "done");
//            }
//        });
        String wifiPort = ShellUtils.execSync(ShellUtils.GET_PROP_WIFI_PORT);
        int returnCharacter = wifiPort.lastIndexOf("\n");
        if(returnCharacter > 0) {
            wifiPort = wifiPort.substring(0, returnCharacter);
        }
        int port = 0;
        try {
            port = Integer.parseInt(wifiPort);
        } catch (Exception e) {
        }
        mTcpPort.setText(port == 0 ? "请设置" : wifiPort);



    }
}
