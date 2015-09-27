package com.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.os.AsyncTaskCompat;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.constant.GLobal;
import com.example.adult_zeren.R;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chengkuang on 15/8/1.
 */
public class DiskUsageActivity extends BaseActivity {
    private ListView mListView = null;
    private List<MyFile> mData = new ArrayList<MyFile>();
    private String mCurrentPath;
    private DiskUsageAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListView = new ListView(this);
        mListView.setCacheColorHint(0);
        mListView.setDivider(null);
        mAdapter = new DiskUsageAdapter();
        mListView.setAdapter(mAdapter);
        setContentView(mListView);

        mCurrentPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        updateDatas();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyFile clickedFile = mAdapter.getItem(position);
                if(clickedFile.isDirectory()) {
                    mCurrentPath = clickedFile.mFilePath;
                    updateDatas();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(mCurrentPath.equals(Environment.getExternalStorageDirectory().getAbsolutePath())) {
            super.onBackPressed();
        } else {
            File currFile = new File(mCurrentPath);
            mCurrentPath = currFile.getParent();
            updateDatas();
        }
    }

    public void updateDatas() {
        mData.clear();;
        File[] currentPathChids = new File(mCurrentPath).listFiles();
        for(File currentPathChild : currentPathChids) {
            MyFile myFile = new MyFile(currentPathChild.getAbsolutePath(), -1);
            mData.add(myFile);
        }
        mAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyFileManager.getInstance().destory();
    }

    private class DiskUsageAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mData == null ? 0 : mData.size();
        }

        @Override
        public MyFile getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mHolder = null;
            if(convertView == null) {
                convertView = DiskUsageActivity.this.getLayoutInflater().inflate(R.layout.disk_usage_layout, null, false);
                mHolder = new ViewHolder();
                mHolder.mSizeTV = (TextView) convertView.findViewById(R.id.path_size);
                mHolder.mPathTV = (TextView) convertView.findViewById(R.id.path_name);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) convertView.getTag();
            }
            MyFile myFile = getItem(position);
            mHolder.mPathTV.setText(myFile.getName());
            mHolder.mSizeTV.setText(myFile.mFileSize);
            setSize(mHolder.mSizeTV, myFile);
            return convertView;
        }


        private class ViewHolder{
            TextView mPathTV;
            TextView mSizeTV;
        }
    }


    private void setSize(final TextView tv, final MyFile myFile) {
        MyFile oldMyFile = (MyFile) tv.getTag();
        if (oldMyFile != null && !oldMyFile.mFilePath.equals(myFile.mFilePath)) {
            MyFileManager.getInstance().cancleTask(oldMyFile.mFilePath);
        }
        tv.setTag(myFile);

        if(myFile.size < 0) {
            MyFileManager.getInstance().getFoldSizeAsync(myFile.mFilePath, new MyFileManager.FileSizeGetListener() {
                @Override
                public void onFileSizeGet(MyFile file) {
                    myFile.size = file.size;
                    myFile.mFileSize = file.mFileSize;
                    tv.setText(file.mFileSize);
                }
            });
        }
    }


    public static class MyFile {
        public long size;
        public String mFileSize;
        public String mFilePath;

        public MyFile(String filePath, long size) {
            this.mFilePath = filePath;
            this.size = size;
            if(size == -1) {
                this.mFileSize = "正在计算";
            } else {
                this.mFileSize = Formatter.formatFileSize(GLobal.mContext, size);
            }
        }

        public boolean isDirectory() {
            return new File(mFilePath).isDirectory();
        }


        public String getName() {
            return new File(mFilePath).getName();
        }
    }

    public static class MyFileManager {
        public static interface FileSizeGetListener {
            public void onFileSizeGet(MyFile file);
        }

        private static MyFileManager mInstance = null;
        private HashMap<String, MyFile> files = new HashMap<String, MyFile>();
        private HashMap<String, AsyncTask>  tasks = new HashMap<String, AsyncTask>();
        private Context mContext = null;
        private MyFileManager( Context context) {
            mContext = context;
        }

        public static MyFileManager getInstance() {
            if(mInstance == null) {
                mInstance = new MyFileManager(GLobal.mContext);
            }
            return mInstance;
        }

        public void destory() {
            files.clear();
            tasks.clear();
            mInstance = null;
        }

        public MyFile getFile(String filePath) throws InterruptedException {
            return getFile(filePath, filePath);
        }

        public MyFile getFile(String rootPath, String filePath) throws InterruptedException {
            if(files.containsKey(filePath)) {
                return files.get(filePath);
            }

      //      Log.i("kcc", "rootPath->" + rootPath + "  filePaht->" + filePath + "  cancle->;" + isCancled(rootPath));
            if(isCancled(rootPath)) {
                throw new InterruptedException();
            }

            File file = new File(filePath);
            if(file.isFile()) {
                MyFile myFile = new MyFile(filePath, file.length());
                files.put(filePath, myFile);
                return myFile;
            } else {
                File[] subFiles = file.listFiles();
                long size = 0;

                if(subFiles != null) {
                    for (File subFileName : subFiles) {
                        MyFile myFile = getFile(rootPath, subFileName.getAbsolutePath());
                        size += myFile.size;
                    }
                }
                MyFile folderFile = new MyFile(filePath,  size);
                files.put(filePath, folderFile);
                return folderFile;
            }
        }

        public void getFoldSizeAsync(final String filePath, final FileSizeGetListener listener) {
            if(files.containsKey(filePath)) {
                listener.onFileSizeGet(files.get(filePath));
                return;
            }

            if(tasks.get(filePath) != null) {
                return;
            }

            AsyncTask<Void, Void, MyFile> task = new AsyncTask<Void, Void, MyFile>() {
                @Override
                protected MyFile doInBackground(Void... params) {
                    if(isCancelled()) {
                        return null;
                    }
                    try {
                        return getFile(filePath);
                    } catch (InterruptedException e) {
                        Log.w("kcc", filePath + this, e);
                        return null;
                    }
                }

                @Override
                protected void onPostExecute(MyFile file) {
                    tasks.remove(filePath);
                    if(isCancelled()) {
                        return;
                    }
                    if(listener != null) {
                        if(file == null) {
                            Log.i("kcc", "hehe + this" + this);
                        }
                        listener.onFileSizeGet(file);
                    }
                }

                @Override
                protected void onCancelled() {
                    super.onCancelled();
                    tasks.remove(filePath);
                }
            };
            AsyncTaskCompat.executeParallel(task);
            tasks.put(filePath, task);
        }

        public void cancleTask(String filePaht) {
            AsyncTask task = tasks.get(filePaht);
            Log.i("kcc2", "cancleTask->" + filePaht + "  taks is null?" + (task == null));
            if(task != null) {
                task.cancel(true);
            }
        }

        public boolean isCancled(String filePaht) {
            AsyncTask task = tasks.get(filePaht);
            if(task != null) {
                return task.isCancelled();
            }
            return false;
        }
    }
}
