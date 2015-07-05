package com.ayoprez.newMoment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ayoprez.deilylang.R;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewMomentActivity extends Activity{

	private Button B_Language, B_Level, B_Time, B_Accept;
	private Context ctx = this;
	private String AppLanguage = Locale.getDefault().getDisplayLanguage().toString();

    private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.newmoment_activity);

        this.context = this;

		B_Language = (Button) findViewById(R.id.b_language);
		B_Level = (Button) findViewById(R.id.b_level);
		B_Time = (Button) findViewById(R.id.b_time);
		B_Accept = (Button) findViewById(R.id.b_accept);
		
		B_Language.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				new Language(ctx);
			}		
		});
		
		B_Level.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
                new Level(context);
			}
		});
	
		B_Time.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				new Moment(context);
			}			
		});

		B_Accept.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				new Accept(ctx).Accept_Dialog(AppLanguage, B_Language.getText().toString(), 
						B_Level.getText().toString(), B_Time.getText().toString());
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

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}

}