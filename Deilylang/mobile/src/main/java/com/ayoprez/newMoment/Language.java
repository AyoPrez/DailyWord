package com.ayoprez.newMoment;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ayoprez.deilylang.R;

public class Language {

	private Dialog Dialog;
	private Context ctx;
	private ArrayAdapter<String> Adapter;
	private ListView Language_List;
	
	
	public Language(Context context){
		this.ctx = context;
		
		this.Dialog = new Dialog(ctx);
		this.Dialog.setContentView(R.layout.language_dialog);
		this.Dialog.setTitle(R.string.button_language);
		
		this.Language_List = (ListView)Dialog.findViewById(R.id.LV_Language);
		
		final String[] Languages = {ctx.getString(R.string.English), ctx.getString(R.string.Finish), 
				ctx.getString(R.string.Spanish), ctx.getString(R.string.German)};
					
		this.Adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_list_item_1, Languages);
		this.Language_List.setAdapter(Adapter);
		
		this.Language_List.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				((NewMomentActivity)ctx).Language_Text(Languages[position]);
				Dialog.dismiss();
			}
		});
		
		Dialog.show();
	}
	
}