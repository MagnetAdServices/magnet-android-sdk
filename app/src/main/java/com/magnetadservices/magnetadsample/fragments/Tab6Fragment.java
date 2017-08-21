package com.magnetadservices.magnetadsample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magnetadservices.magnetadsample.R;
import com.magnetadservices.magnetadsample.Utils;
import com.magnetadservices.magnetadsample.adapters.RecyclerViewAdapter;

import java.util.List;

/**
 * Created by Esmaeili on 2017-08-19.
 */

public class Tab6Fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab6_fragment, container, false);

        List<Object> data = Utils.injectAdObjects(getContext(), Utils.getListOfCountries(getContext()));

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.native_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), data);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
