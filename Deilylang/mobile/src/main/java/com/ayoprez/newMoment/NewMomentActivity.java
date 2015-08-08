package com.ayoprez.newMoment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ayoprez.deilylang.MainActivity;
import com.ayoprez.deilylang.R;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewMomentActivity extends AppCompatActivity{

	private Button B_Language, B_Level, B_Time, B_Accept;
	private Context ctx = this;
	private String AppLanguage = Locale.getDefault().getISO3Language();
    private Toolbar toolbar;
    private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.newmoment_activity);

        this.context = this;

        initToolbar();

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

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
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