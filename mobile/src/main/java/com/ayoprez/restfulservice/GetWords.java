package com.ayoprez.restfulservice;

import android.content.Context;
import android.util.Log;

import com.ayoprez.deilylang.ErrorHandler;
import com.ayoprez.deilylang.R;
import com.ayoprez.deilylang.WordFromDatabase;
import com.ayoprez.notification.LaunchNotification;
import com.crashlytics.android.Crashlytics;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by AyoPrez on 12/04/15.
 */
public class GetWords {
    public static final String LOG_TAG = GetWords.class.getSimpleName();

    public static final String ENDPOINT = "http://deilylang.com/api/index.php/";

    private WordsAPI wordsAPI;
    private Context context;

    public GetWords(Context context){
        this.context = context;

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ENDPOINT)
                .build();

        wordsAPI = restAdapter.create(WordsAPI.class);
    }

    public void sendEnglishWordRequest(final String level, final int id_u, final String languageMobile){
        new LaunchNotification(context);

        wordsAPI.getNewEnglishWord(level, id_u, languageMobile, new Callback<WordFromDatabase>() {

            @Override
            public void success(WordFromDatabase word, Response response) {
                if(word != null && word.getWord()[0] != null && word.getId() != 0) {
                    EventBus.getDefault().post(word);
                }else{
                    ErrorHandler.getInstance().Error(LOG_TAG, "Unsuccess");
                    ErrorHandler.getInstance().informUser(context, context.getString(R.string.errorDefault));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                ErrorHandler.getInstance().Error(LOG_TAG, error.toString());
                ErrorHandler.getInstance().informUser(context, context.getString(R.string.errorDefault));
                EventBus.getDefault().post(error);
            }
        });
    }

    public void sendSpanishWordRequest(final String level, final int id_u, final String languageMobile){
        new LaunchNotification(context);

        wordsAPI.getNewSpanishWord(level, id_u, languageMobile, new Callback<WordFromDatabase>() {

            @Override
            public void success(WordFromDatabase word, Response response) {
                if(word != null && word.getWord()[0] != null && word.getId() != 0) {
                    EventBus.getDefault().post(word);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                ErrorHandler.getInstance().Error(LOG_TAG, error.toString());
                ErrorHandler.getInstance().informUser(context, context.getString(R.string.errorDefault));
                EventBus.getDefault().post(error);
            }
        });
    }

}