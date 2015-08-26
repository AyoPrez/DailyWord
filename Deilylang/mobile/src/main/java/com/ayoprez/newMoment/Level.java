package com.ayoprez.newMoment;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.RadioGroup;

import com.ayoprez.deilylang.R;

public class Level {

	private Context ctx;
	private Dialog Dialog;
	private NewMomentActivity MA;
	
	
	public Level(Context context){
		this.ctx = context;
		this.MA = ((NewMomentActivity)ctx);
		
		this.Dialog = new Dialog(ctx);
		this.Dialog.setContentView(R.layout.dialog_level);
		this.Dialog.setTitle(R.string.button_level);
		
		final RadioGroup Levels = (RadioGroup) Dialog.findViewById(R.id.radioGroup_levels);
		Levels.clearCheck();		
		
		Levels.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {	
			public void onCheckedChanged(RadioGroup group, int checkedId) {				
				View radioButton = Levels.findViewById(checkedId);  
				int idx = Levels.indexOfChild(radioButton); 
								
				switch(idx){
					case 0:
						MA.Level_Text(ctx.getString(R.string.basic));
						break;
					case 1:
						MA.Level_Text(ctx.getString(R.string.easy));
						break;
					case 2:
						MA.Level_Text(ctx.getString(R.string.normal));
						break;
					case 3:
						MA.Level_Text(ctx.getString(R.string.hard));
						break;
				}
								
				Dialog.dismiss();
			}
		});
		
		Dialog.show();
	}
		
}