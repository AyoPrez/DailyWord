package com.ayoprez.deilylang;

import android.support.multidex.MultiDexApplication;

import com.ayoprez.modules.AppModule;
import com.ayoprez.modules.NetModule;

import components.DaggerNetComponent;
import components.NetComponent;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by AyoPrez on 14/06/15.
 */
public class MainApplication extends MultiDexApplication {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

//        mNetComponent = DaggerNetComponent.builder()
//                // list of modules that are part of this component need to be created here too
//                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
//                .netModule(new NetModule("https://api.github.com"))
//                .build();



        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/RockoFLF.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
//        LeakCanary.install(this);
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }

}
