package com.ayoprez.savedWords;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ayoprez.deilylang.DetectDeviceLanguage;
import com.ayoprez.deilylang.R;
import com.ayoprez.utils.SpeakerHelper;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.CustomEvent;
import de.greenrobot.event.EventBus;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by AyoPrez on 1/08/15.
 */
public class SavedWordsRecyclerViewAdapter extends RecyclerView.Adapter<SavedWordsRecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> savedWordsList = new ArrayList<>();
    private ArrayList<String> savedTypesList = new ArrayList<>();
    private String language;
    private TextToSpeech textToSpeech;
    private int speechStatus;

    public SavedWordsRecyclerViewAdapter(Context context, ArrayList<String> savedWords, ArrayList<String> savedTypes, String language){
        this.savedWordsList = savedWords;
        this.savedTypesList = savedTypes;
        this.language = language;
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                speechStatus = status;
            }
        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_saved_words_recyclerview, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if(savedWordsList.size() > 0 && savedWordsList != null && savedTypesList != null) {
            viewHolder.savedWord.setText(savedWordsList.get(i));
            viewHolder.savedType.setText(savedTypesList.get(i));
            viewHolder.savedSpeaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Crashlytics.getInstance().answers.logCustom(new CustomEvent("SavedWordsSpeaker"));
                    talkSpeech(speechStatus, DetectDeviceLanguage.getLocale(language), savedWordsList.get(i));
                }
            });
        }
    }

    private void talkSpeech(int status, Locale language, String speech){
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech.setLanguage(language);
            textToSpeech.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        }
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

        @Bind(R.id.imageButton_savedWord_speaker)
        ImageButton savedSpeaker;
        @Bind(R.id.textView_savedWord)
        TextView savedWord;
        @Bind(R.id.textView_savedWord_type)
        TextView savedType;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}