package com.ayoprez.savedQuotes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayoprez.deilyquote.QuoteScreen;
import com.ayoprez.deilyquote.R;

import java.util.ArrayList;

/**
 * Created by AyoPrez on 17/08/15.
 */
public class SavedQuotesRecyclerViewAdapter extends RecyclerView.Adapter<SavedQuotesRecyclerViewAdapter.ViewHolder> {

    private ArrayList<SavedQuotes> savedQuotesList = new ArrayList<>();
    private Context context;

    public SavedQuotesRecyclerViewAdapter(Context context, ArrayList<SavedQuotes> savedQuotes) {
        this.context = context;
        savedQuotesList = savedQuotes;
    }

    @Override
    public SavedQuotesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_quotes_recyclerview_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.Quote.setText(savedQuotesList.get(position).getQuote());
        holder.Author.setText(savedQuotesList.get(position).getAuthor());
        holder.Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuoteInScreen(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return savedQuotesList.size();
    }

    public void showQuoteInScreen(int position){
        Intent intent = new Intent(context, QuoteScreen.class);
        intent.putExtra("quote", savedQuotesList.get(position).getQuote());
        intent.putExtra("author", savedQuotesList.get(position).getAuthor());
        intent.putExtra("language", savedQuotesList.get(position).getLanguage());
        intent.putExtra("saved", "saved");
        context.startActivity(intent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Quote;
        public TextView Author;
        public LinearLayout Layout;

        public ViewHolder(View v) {
            super(v);
            Layout = (LinearLayout) v.findViewById(R.id.item_layout);
            Quote = (TextView) v.findViewById(R.id.textView_savedQuote);
            Author = (TextView) v.findViewById(R.id.textView_savedQuote_author);
        }
    }

}