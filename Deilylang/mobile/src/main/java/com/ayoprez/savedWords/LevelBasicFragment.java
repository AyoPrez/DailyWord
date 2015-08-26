package com.ayoprez.savedWords;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.deilylang.R;

import java.util.ArrayList;

/**
 * Created by AyoPrez on 8/08/15.
 */

public class LevelBasicFragment extends AbstractLevelFragment {

    protected ArrayList<String> wordsList = new ArrayList<>();
    protected ArrayList<String> typesList = new ArrayList<>();
    protected String mainLanguage;

    public static Fragment newInstance(ArrayList<String> listWords, ArrayList<String> listTypes, String language) {
        LevelBasicFragment myFragment = new LevelBasicFragment();

        Bundle args = new Bundle();
        args.putStringArrayList(WORDS, listWords);
        args.putStringArrayList(TYPES, listTypes);
        args.putString(LANGUAGE, language);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments().getStringArrayList(WORDS).size() > 0) {
            wordsList = getArguments().getStringArrayList(WORDS);
            typesList = getArguments().getStringArrayList(TYPES);
            mainLanguage = getArguments().getString(LANGUAGE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.language_fragment, container, false);

        recyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView_savedWords);
        adapter = new SavedWordsRecyclerViewAdapter(getActivity(), wordsList, typesList, mainLanguage);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        showEmptyList(mainView, adapter);

        return mainView;
    }
}