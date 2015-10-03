package com.ayoprez.newMoment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ayoprez.deilyquote.R;

import java.util.ArrayList;

/**
 * Created by AyoPrez on 22/08/15.
 */
public class TopicDialogAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private ArrayList<String> topics;

    public TopicDialogAdapter(Context context, ArrayList<String> topicsList){
        this.topics = topicsList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return topics.size();
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
//        ImageView images = (ImageView) view.findViewById(R.id.iV_flag_newMoment);

        text.setText(topics.get(position));

        return view;
    }
}