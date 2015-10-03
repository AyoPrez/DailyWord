package com.ayoprez.newMoment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayoprez.deilyquote.R;

import java.util.ArrayList;

/**
 * Created by AyoPrez on 22/08/15.
 */
public class LanguageDialogAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private ArrayList<String> languages;

    public LanguageDialogAdapter(Context context, ArrayList<String> languagesList){
        this.languages = languagesList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return languages.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView==null)
            view = inflater.inflate(R.layout.item_dialog_language, null);

        TextView text = (TextView) view.findViewById(R.id.tV_language_newMoment);
        ImageView flag = (ImageView) view.findViewById(R.id.iV_flag_newMoment);

        text.setText(languages.get(position));
        switch (position){
            case 0:
                flag.setImageResource(R.drawable.spanish_flag);
                break;
            case 1:
                flag.setImageResource(R.drawable.uk_flag);
                break;
            case 2:
                flag.setImageResource(R.drawable.german_flag);
                break;
            case 3:
                flag.setImageResource(R.drawable.italian_flag);
                break;
            case 4:
                flag.setImageResource(R.drawable.france_flag);
                break;
        }

        return view;
    }
}