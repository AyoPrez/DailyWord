package com.ayoprez.savedWords;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.ayoprez.deilylang.R;
import com.ayoprez.restfulservice.GetSavedWords;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;

/**
 * Created by AyoPrez on 8/08/15.
 */
public class LevelEasyFragment extends Fragment {

    private RecyclerAdapter adapter;
    private ArrayList<SavedWords> wordsList;
    private RecyclerView recyclerView;
    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.language_fragment, container, false);

        getSavedWords(1);

        adapter = new RecyclerAdapter(getActivity(), wordsList);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_savedWords);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(getActivity());
    }

    public Spinner getSpinner(){
        Spinner spinner = (Spinner) getActivity().findViewById(R.id.spinner_nav);
        Log.e("DeilyLang", "Spinner: " + spinner.getSelectedItem());
        return spinner;
    }

    private void getSavedWords(int userId){
        if(getSpinner().getSelectedItem().equals(getString(R.string.English))){
            new GetSavedWords(getActivity()).sendEnglishUserWordsRequest(userId, "spanish");
        }else{
            if (getSpinner().getSelectedItem().equals(getString(R.string.Spanish))){
                new GetSavedWords(getActivity()).sendSpanishUserWordsRequest(userId, "english");
            }
        }
    }

    private ArrayList<SavedWords> getEasyWords(ArrayList<SavedWords> wordsList){
        ArrayList<SavedWords> easyList = new ArrayList<>();
        for(int i = 0; i < wordsList.size(); i++){
            if(wordsList.get(i).getLevel().toLowerCase().equals("easy")){
                easyList.add(wordsList.get(i));
            }
        }
        return easyList;
    }

    public void onEvent(ArrayList<SavedWords> savedWords){
        wordsList = getEasyWords(savedWords);
    }
}
