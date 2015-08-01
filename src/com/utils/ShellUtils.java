package com.utils;

import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengkuang on 15/8/1.
 */
public class ShellUtils {
    public static final String GET_PROP_WIFI_PORT = "getprop service.adb.tcp.port";
    public static final String ls = "ls";

    public static String execSync(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();
            process.destroy();

            // Waits for the command to finish.
            process.waitFor();
            Log.i("kcc", "exec param:" + command + "  result:" + output.toString());
            return output.toString();
        } catch (Exception e) {
            Log.w("kcc", "kcc", e);
        }
        return null;
    }


    /**
     * 这个root是有问题的，我不管了  我装得时debug版，不需要root权限
     * @param command
     * @return
     */
    public static String execAyRootSync(String command) {
        List<String> commands = new ArrayList<String>();
        commands.add(command);
        return execAyRootSync(commands);
    }

    public static String execAyRootSync(List<String> commands) {
        try {
            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream out = new DataOutputStream(process.getOutputStream());
            for(String command : commands) {
                out.writeBytes(command + " \n");
            }
            out.close();

            // Waits for the command to finish.
            process.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();


            Log.i("kcc",  "  result:" + output.toString());
            return output.toString();
        } catch (Exception e) {
            Log.w("kcc", "kcc", e);
        }
        return null;
    }


    public static interface OnShellCallback {
        public void onGetLine(String line);
        public void onFinish();
    }
    public static Process execAsync(final String command, final OnShellCallback callback) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        AsyncTask task  = new AsyncTask<Process, String, Void>() {
            @Override
            protected Void doInBackground(Process... params) {
                try {
                    Process process = params[0];
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        callback.onGetLine(line);
                    }
                    reader.close();

                    // Waits for the command to finish.
                    process.waitFor();
                } catch (Exception e) {
                    Log.w("kcc", "kcc", e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if(callback != null) {
                    callback.onFinish();
                }

            }
        };

        AsyncTaskCompat.executeParallel(task, process);
        return process;
    }
}
