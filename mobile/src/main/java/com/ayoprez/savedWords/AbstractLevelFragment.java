package com.ayoprez.savedWords;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Spinner;

import com.ayoprez.deilylang.R;

/**
 * Created by AyoPrez on 10/08/15.
 */
public abstract class AbstractLevelFragment extends Fragment {

    protected SavedWordsRecyclerViewAdapter adapter;
    protected RecyclerView recyclerView;
    protected View mainView;
    protected static String WORDS = "words";
    protected static String TYPES = "types";
    protected static String LANGUAGE = "language";

    public void changeLayout(int layout, LayoutInflater inflater, ViewGroup rootView){
        mainView = inflater.inflate(layout, null);
        rootView.removeAllViews();
        rootView.addView(mainView);
    }

    public Spinner getSpinner(){
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner_nav);
        return spinner;
    }

    public void showEmptyList(View mainView, SavedWordsRecyclerViewAdapter adapter){
        ViewStub viewStub = (ViewStub) mainView.findViewById(R.id.empty_view);
        if(adapter.getItemCount() == 0){
            viewStub.inflate();
            viewStub.setVisibility(View.VISIBLE);
        }else{
            viewStub.setVisibility(View.GONE);
        }
    }
}