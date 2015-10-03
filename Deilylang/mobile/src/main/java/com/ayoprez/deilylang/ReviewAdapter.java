package com.ayoprez.deilylang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import deilyword.UserMoments;

public class ReviewAdapter extends BaseAdapter {

	// Declare Variables
    private LayoutInflater inflater;
    private List<UserMoments> dataFromListView = null;
    private Context ctx;
 
    public ReviewAdapter(Context context, List<UserMoments> getDataFromDatabaseToListView) {
        this.dataFromListView = getDataFromDatabaseToListView;
        this.ctx = context;
        this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
   
    public class ViewHolder {
        TextView Time;
        TextView Language;
        TextView Level;
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
                       
           	view = inflater.inflate(R.layout.item_review, parent, false);
           	holder.Time = (TextView) view.findViewById(R.id.tV_review_time);
            holder.Language = (TextView) view.findViewById(R.id.tV_review_language);
            holder.Level = (TextView) view.findViewById(R.id.tV_review_level);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.Time.setText(GiveDotsToTime(dataFromListView.get(position).getTime()));
       	holder.Language.setText(dataFromListView.get(position).getLanguage());
       	holder.Level.setText(dataFromListView.get(position).getLevel());

        return view;
    }
    
    private String GiveDotsToTime(String time){
    	return time.substring(0, 2) + ":" + time.substring(2,4);
    }

	@Override
	public Object getItem(int position) {
		return dataFromListView.get(position);
	}

}