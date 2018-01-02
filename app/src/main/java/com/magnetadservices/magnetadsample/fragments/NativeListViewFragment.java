package com.magnetadservices.magnetadsample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.magnetadservices.magnetadsample.R;
import com.magnetadservices.magnetadsample.Utils;
import com.magnetadservices.magnetadsample.adapters.ListViewAdapter;

import java.util.List;

/**
 * Created by Esmaeili on 2017-08-19.
 */

public class NativeListViewFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab5_fragment, container, false);

        List<Object> data = Utils.injectAdObjects(getContext(), Utils.getListOfCountries(getContext()));

        ListView listView = view.findViewById(R.id.native_list);
        ListViewAdapter viewAdapter = new ListViewAdapter(getContext(), data);
        listView.setAdapter(viewAdapter);
        listView.setDivider(null);

        return view;
    }
}
