package com.ayoprez.deilylang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.newMoment.NewMomentActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ayo on 04.03.16.
 */
public class NoMomentMainFragment extends Fragment {

    private static NoMomentMainFragment instance;

    @OnClick(R.id.b_main)
    void momentsButton(){
        startActivity(new Intent(getActivity(), NewMomentActivity.class));
        getActivity().finish();
    }

    public static Fragment getInstance(){
        if(instance == null){
            instance = new NoMomentMainFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
