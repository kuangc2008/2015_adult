package com.activity;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by kuangcheng on 15-8-6.
 */
public class DataStructureActivity extends BaseActivity{

    private ListView mListView = null;
    private ArrayAdapter<String> mAdapter= null;
    private String[] ss = new String[]{
            "Are binary trees equals",
            "Binary tree in order",

            "Binary tree by level",
            "Binary tree pre order",
            "Binary tree post order",
            "Is binary search tree",
            "Binary tree depth",
            "Lowest common ancestor",
            "Sorted array to BST",
            "AVL tree median",
            "Path calculator",
            "Path to every leaf",
            "Is tree balanced",
            "Generate list with nodes by level"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListView = new ListView(this);

        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ss);
        mListView.setAdapter(mAdapter);
        setContentView(mListView);
        mListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        break;
                    case 1:

                        break;

                    case 2:

                        break;
                }
            }
        });
    }


}
