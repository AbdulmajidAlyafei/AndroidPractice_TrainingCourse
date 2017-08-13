package com.alyafei.myapplication.Practice;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alyafei.myapplication.Main6Activity_Spinner;
import com.alyafei.myapplication.R;

/**
 * Created by Eng. Abdulmajid Alyafey on 8/13/2017.
 */

public class FirstFragmentNavigationBar extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView= inflater.inflate(R.layout.first_layout,container,false);
        return myView;
    }
}
