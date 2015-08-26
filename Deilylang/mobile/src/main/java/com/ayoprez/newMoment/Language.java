package com.ayoprez.newMoment;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ayoprez.deilylang.R;

import java.util.ArrayList;
import java.util.Locale;

public class Language {

	private Dialog Dialog;
	private Context ctx;
	private LanguageDialogAdapter Adapter;
	private ListView Language_List;
	
	
	public Language(Context context){
		this.ctx = context;
		
		this.Dialog = new Dialog(ctx);
		this.Dialog.setContentView(R.layout.dialog_language);
		this.Dialog.setTitle(R.string.button_language);
		
		this.Language_List = (ListView)Dialog.findViewById(R.id.LV_Language);


        //TODO improve this
        String localLanguage = Locale.getDefault().getISO3Language();

        final ArrayList<String> Languages = new ArrayList<>();

		switch (localLanguage){
            case "spa":
                Languages.add(ctx.getString(R.string.English));
                break;
            case "eng":
                Languages.add(ctx.getString(R.string.Spanish));
                break;
        }

		this.Adapter = new LanguageDialogAdapter(ctx, Languages);
//		this.Adapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_list_item_1, Languages);
		this.Language_List.setAdapter(Adapter);
		
		this.Language_List.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				((NewMomentActivity)ctx).Language_Text(Languages.get(position));
				Dialog.dismiss();
			}
		});
		
		Dialog.show();
	}
	
}