package com.ayoprez.newMoment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ayoprez.deilylang.AbstractBaseActivity;
import com.ayoprez.deilylang.DetectDeviceLanguage;
import com.ayoprez.deilylang.MainActivity;
import com.ayoprez.deilylang.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewMomentActivity extends AbstractBaseActivity{

    private Context context;

	@Bind(R.id.b_language)
	protected Button B_Language;
	@Bind(R.id.b_level)
	protected Button B_Level;
	@Bind(R.id.b_time)
	protected Button B_Time;

	@OnClick(R.id.b_language)
	void OnLanguageClick(){
		new Language(context);
	}

	@OnClick(R.id.b_level)
	void OnLevelClick(){
		new Level(context);
	}

	@OnClick(R.id.b_time)
	void OnMomentClick(){
		new Moment(context);
	}

	@OnClick(R.id.b_accept)
	void OnAcceptClick(){
		new Accept(context).Accept_Dialog(DetectDeviceLanguage.getISO3Language(), B_Language.getText().toString(),
				B_Level.getText().toString(), B_Time.getText().toString());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_newmoment);
		ButterKnife.bind(this);

        this.context = this;

        initToolbar();
	}

	@Override
    protected void initToolbar(){
		super.initToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				goToNewScreen(MainActivity.class);
			}
		});
    }

	public void Language_Text(String Text){
		B_Language.setText(Text);
	}
	
	public void Level_Text(String Text){
		B_Level.setText(Text);
	}
		
	public void Time_Text(String Text){
		B_Time.setText(Text);
	}

}