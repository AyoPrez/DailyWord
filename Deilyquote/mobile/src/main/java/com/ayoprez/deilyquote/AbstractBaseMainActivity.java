package com.ayoprez.deilyquote;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.ayoprez.login.SessionManager;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by AyoPrez on 23/08/15.
 */
public abstract class AbstractBaseMainActivity extends AppCompatActivity{

    protected SessionManager sessionManager = new SessionManager(this);

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
