package com.ayoprez.savedWords;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ayoprez.deilylang.R;

import java.util.List;

/**
 * Created by AyoPrez on 24/05/15.
 */
public class SavedWordsAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<SavedWords> dataFromListView = null;
    private Context ctx;

    public SavedWordsAdapter(Context context, List<SavedWords> getDataFromDatabaseToListView) {
        this.dataFromListView = getDataFromDatabaseToListView;
        this.ctx = context;
        this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class ViewHolder {
        TextView Word;
    }

    @Override
    public int getCount() {
        return dataFromListView.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;

        if(view == null){
            holder = new ViewHolder();

            view = inflater.inflate(R.layout.wordslist_item, parent, false);
            holder.Word = (TextView) view.findViewById(R.id.textView_savedWordsList);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.Word.setText(""+dataFromListView.get(position).getWord());

        return view;
    }

    @Override
    public Object getItem(int position) {
        return dataFromListView.get(position);
    }
}