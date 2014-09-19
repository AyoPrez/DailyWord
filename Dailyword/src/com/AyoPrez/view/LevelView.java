package com.AyoPrez.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.RadioGroup;

import com.AyoPrez.dailyword.R;

public class LevelView {

	private Context ctx;
	private Dialog Dialog;
	private String optionChoosed;

	public String getOptionChoosed() {
		return optionChoosed;
	}

	public void setOptionChoosed(String optionChoosed) {
		this.optionChoosed = optionChoosed;
	}

	public LevelView(Context context){
		this.ctx = context;
		
		this.Dialog = new Dialog(ctx);
		this.Dialog.setContentView(R.layout.level_dialog);
		this.Dialog.setTitle(R.id.b_level);

		final RadioGroup Levels = (RadioGroup) Dialog.findViewById(R.id.radioGroup_levels);
		Levels.clearCheck();		

		Levels.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {	
			public void onCheckedChanged(RadioGroup group, int checkedId) {				
				View radioButton = Levels.findViewById(checkedId);  
				int idx = Levels.indexOfChild(radioButton); 

				switch(idx){
				case 0:
					setOptionChoosed(ctx.getString(R.string.basic));
					break;
				case 1:
					setOptionChoosed(ctx.getString(R.string.easy));
					break;
				case 2:
					setOptionChoosed(ctx.getString(R.string.normal));
					break;
				case 3:
					setOptionChoosed(ctx.getString(R.string.hard));
					break;
				}

				Dialog.dismiss();
			}
		});

		Dialog.show();
	}
}