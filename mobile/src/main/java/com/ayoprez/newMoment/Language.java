package com.ayoprez.newMoment;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ayoprez.deilylang.DetectDeviceLanguage;
import com.ayoprez.deilylang.R;

import java.util.ArrayList;

public class Language {

	private Dialog Dialog;
	private LanguageDialogAdapter Adapter;
	private ListView Language_List;
	
	
	public Language(final Context context){

		this.Dialog = new Dialog(context);
		this.Dialog.setContentView(R.layout.dialog_language);
		this.Dialog.setTitle(R.string.button_language);
		
		this.Language_List = (ListView)Dialog.findViewById(R.id.LV_Language);

		this.Adapter = new LanguageDialogAdapter(context, getFilledDialogWithLanguages(context));
		this.Language_List.setAdapter(Adapter);
		
		this.Language_List.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				((NewMomentActivity)context).Language_Text(getFilledDialogWithLanguages(context).get(position));
				Dialog.dismiss();
			}
		});
		
		Dialog.show();
	}

	//Right now the app only support spanish and english
	private ArrayList<String> getFilledDialogWithLanguages(Context context){
		final ArrayList<String> Languages = new ArrayList<>();

		if(DetectDeviceLanguage.getISO3Language().equals("spa")){
			Languages.add(context.getString(R.string.English));
		}else{
			Languages.add(context.getString(R.string.Spanish));
		}
		return Languages;
	}
}