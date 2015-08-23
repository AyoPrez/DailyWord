package com.ayoprez.newMoment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ayoprez.deilyquote.MainActivity;
import com.ayoprez.deilyquote.R;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewMomentActivity extends AppCompatActivity{

    private Toolbar toolbar;
	private Button B_Language, B_Topic, B_Time, B_Accept;
	private Context ctx = this;
	private String AppLanguage = Locale.getDefault().getDisplayLanguage().toString();

    private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_newmoment);

        this.context = this;

        initToolbar();

		B_Language = (Button) findViewById(R.id.b_language);
		B_Topic = (Button) findViewById(R.id.b_topic);
		B_Time = (Button) findViewById(R.id.b_time);
		B_Accept = (Button) findViewById(R.id.b_accept);
		
		B_Language.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				new Language(ctx);
			}		
		});
		
		B_Topic.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
                new Topic(context);
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
						B_Topic.getText().toString(), B_Time.getText().toString());
			}	
		});

	}

	private void initToolbar(){
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				backToProfileIntent();
			}
		});
	}

	private void backToProfileIntent(){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}


	public void Language_Text(String Text){
		B_Language.setText(Text);
	}
	
	public void Topic_Text(String Text){
		B_Topic.setText(Text);
	}
		
	public void Time_Text(String Text){
		B_Time.setText(Text);
	}

	@Override
	protected void attachBaseContext(Context newBase) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
	}
}