package com.ayoprez.deilylang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends BaseAdapter {

	// Declare Variables
    private LayoutInflater inflater;
    private List<UserData> R_List = null;
    private Context ctx;
 
    public ReviewAdapter(Context context, ArrayList<UserData> reviewList) {
        this.R_List = reviewList;
        this.ctx = context;
        this.inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
   
    public class ViewHolder {
        TextView Time;
        TextView Language;
        TextView Level;
        TextView NewMoment;
    }
    
    @Override
    public int getCount() {
        return R_List.size();
    }
     
    @Override
    public long getItemId(int position) {
        return position;
    }
        
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
              
        if(view == null){
            holder = new ViewHolder();
                       
           	view = inflater.inflate(R.layout.review_item, parent, false);
           	holder.Time = (TextView) view.findViewById(R.id.tV_review_time);
            holder.Language = (TextView) view.findViewById(R.id.tV_review_language);
            holder.Level = (TextView) view.findViewById(R.id.tV_review_level);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        
       
        holder.Time.setText(GiveDotsToTime(R_List.get(position).getTime()));
       	holder.Language.setText(R_List.get(position).getLanguage());
       	holder.Level.setText(R_List.get(position).getLevel());
             
        return view;
    }
    
    private String GiveDotsToTime(String time){
    	return time.substring(0, 2) + ":" + time.substring(2,4);
    }

	@Override
	public Object getItem(int position) {
		return R_List.get(position);
	}     

}