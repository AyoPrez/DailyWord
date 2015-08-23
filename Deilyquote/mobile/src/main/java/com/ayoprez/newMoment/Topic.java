package com.ayoprez.newMoment;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ayoprez.deilyquote.R;

import java.util.ArrayList;

public class Topic {

	private Context ctx;
	private Dialog Dialog;
	private TopicDialogAdapter Adapter;
	private ListView listViewTopics;

	public Topic(Context context){
		this.ctx = context;
		
		this.Dialog = new Dialog(ctx);
		this.Dialog.setContentView(R.layout.dialog_topic);
		this.Dialog.setTitle(R.string.button_topic);

		final ArrayList<String> Topics = new ArrayList<>();
		Topics.add(ctx.getString(R.string.topic1));
		Topics.add(ctx.getString(R.string.topic2));
		Topics.add(ctx.getString(R.string.topic3));
		Topics.add(ctx.getString(R.string.topic4));
		Topics.add(ctx.getString(R.string.topic5));
		Topics.add(ctx.getString(R.string.topic6));
		Topics.add(ctx.getString(R.string.topic7));
		Topics.add(ctx.getString(R.string.topic8));
//		Topics.add(ctx.getString(R.string.topic9));
		Topics.add(ctx.getString(R.string.topic10));

		listViewTopics = (ListView) Dialog.findViewById(R.id.listView_topic);
//		this.Adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_list_item_1, Topics);
		this.Adapter = new TopicDialogAdapter(ctx, Topics);
		this.listViewTopics.setAdapter(Adapter);

		listViewTopics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				((NewMomentActivity)ctx).Topic_Text(Topics.get(position));
				Dialog.dismiss();
			}
		});

		Dialog.show();
	}
		
}