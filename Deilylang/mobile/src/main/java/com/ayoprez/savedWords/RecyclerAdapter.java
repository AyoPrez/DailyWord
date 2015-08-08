package com.ayoprez.savedWords;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.ayoprez.deilylang.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by AyoPrez on 1/08/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private ArrayList<SavedWords> savedWordsList;
    private TextToSpeech textToSpeech;
    private Context context;

    public RecyclerAdapter(Context context, ArrayList<SavedWords> savedWords){
        this.savedWordsList = savedWords;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.saved_words_recyclerview_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.savedWord.setText(savedWordsList.get(i).getWord());
        viewHolder.savedType.setText(savedWordsList.get(i).getType());
        viewHolder.savedSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        talkSpeech(status, defineLocales(savedWordsList.get(i).getLanguage()), savedWordsList.get(i).getWord());
                    }
                });
            }
        });
    }

    private void talkSpeech(int status, Locale language, String speech){
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.setLanguage(language);
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private Locale defineLocales(String language){

        Locale languageWords = null;

        if(language.toLowerCase().equals("english")){
            languageWords = Locale.ENGLISH;
        }else{
            if(language.toLowerCase().equals("spanish")){
                languageWords = new Locale("es", "ES");
            }else{
                if(language.toLowerCase().equals("german")){
                    languageWords = Locale.GERMAN;
                }
            }
        }

        return languageWords;
    }

    @Override
    public int getItemCount() {
        if(savedWordsList != null) {
            return savedWordsList.size();
        }else{
            return 1;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageButton savedSpeaker;
        TextView savedWord;
        TextView savedType;

        public ViewHolder(View itemView) {
            super(itemView);
            savedWord = (TextView) itemView.findViewById(R.id.textView_savedWord);
            savedType = (TextView) itemView.findViewById(R.id.textView_savedWordsList);
            savedSpeaker = (ImageButton) itemView.findViewById(R.id.imageButton_savedWord_speaker);
        }
    }
}