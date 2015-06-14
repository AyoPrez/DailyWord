package com.ayoprez.savedWords;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ayoprez.deilylang.R;
import com.ayoprez.restfulservice.GetSavedWords;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by AyoPrez on 24/05/15.
 */
public class TabBasic extends Fragment{

    private List<SavedWords> wordsList;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
//        wordsList = new UserWordsRepository().getAllWordsByLevel(this.getActivity(), "Basic");
        wordsList = new ArrayList<>();
        new GetSavedWords(getActivity()).sendWordRequest(2, "basic");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout, container, false);

        listView = (ListView) view.findViewById(R.id.listView_savedWords);
        listView.setAdapter(new SavedWordsAdapter(this.getActivity(), wordsList));

        return view;
    }

    public void onEvent(SavedWords words){
        wordsList.add(words);
    }
}