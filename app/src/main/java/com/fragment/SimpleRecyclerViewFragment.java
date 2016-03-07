package com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adult_zeren.R;

/**
 * Created by chengkuang on 15/7/12.
 */
public class SimpleRecyclerViewFragment extends Fragment{


    private static final int DATASET_COUNT = 60;

    protected String[] mDataset;
    protected RecyclerView mRecyclerView = null;
    protected RecyclerView.LayoutManager mLayoutManager = null;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }
    protected LayoutManagerType mCurrentLayoutManager = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataSet();
    }

    private void initDataSet() {
        mDataset = new String[DATASET_COUNT];
        for(int i = 0; i < DATASET_COUNT; i++) {
            mDataset[i] = "this is element #" + i;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.simple_recycle_view, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity());


        return rootView;
    }
}
