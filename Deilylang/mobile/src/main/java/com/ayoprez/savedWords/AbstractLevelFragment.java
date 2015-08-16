package com.ayoprez.savedWords;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.ayoprez.deilylang.R;

/**
 * Created by AyoPrez on 10/08/15.
 */
public class AbstractLevelFragment extends Fragment {

    protected SavedWordsRecyclerViewAdapter adapter;
    protected RecyclerView recyclerView;
    protected View mainView;
    protected static String WORDS = "words";
    protected static String TYPES = "types";
    protected static String LANGUAGE = "language";

    public void changeLayout(int id){
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mainView = inflater.inflate(id, null);
        ViewGroup rootView = (ViewGroup) getView();
        rootView.removeAllViews();
        rootView.addView(mainView);
    }

    public Spinner getSpinner(){
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner_nav);
        return spinner;
    }

}
