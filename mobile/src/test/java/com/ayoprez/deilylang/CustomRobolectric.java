package com.ayoprez.deilylang;

import android.app.Activity;

import org.junit.runners.model.InitializationError;
import org.robolectric.AndroidManifest;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.SdkConfig;
import org.robolectric.SdkEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.res.Fs;
import org.robolectric.shadows.ShadowMenuInflater;

import java.util.Properties;

public class CustomRobolectric extends RobolectricTestRunner {
    public CustomRobolectric(Class<?> testClass)
            throws InitializationError {
        super(testClass);
    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        String manifestProperty = "./mobile/src/main/AndroidManifest.xml";
        String resProperty = "./mobile/src/main/res";
        return new AndroidManifest(Fs.fileFromPath(manifestProperty), Fs.fileFromPath(resProperty)) {
            @Override
            public int getTargetSdkVersion() {
                return 18;
            }

            @Override
            public String getThemeRef(Class<? extends Activity> activityClass) {
                return "@style/RoboAppTheme";
            }
        };
//
//        // android studio has a different execution root for tests than pure gradle
//        // so we avoid here manual effort to get them running inside android studio
//        if (!new File(manifestProperty).exists()) {
//            manifestProperty = manifestProperty;
//        }
//
//        config = overwriteConfig(config, "manifest", manifestProperty);
//        return super.getAppManifest(config);
    }

    protected Config.Implementation overwriteConfig(
            Config config, String key, String value) {
        Properties properties = new Properties();
        properties.setProperty(key, value);
        return new Config.Implementation(config,
                Config.Implementation.fromProperties(properties));
    }

    @Override
    protected SdkConfig pickSdkVersion(AndroidManifest appManifest, Config config) {
        // current Robolectric supports not the latest android SDK version
        // so we must downgrade to simulate the latest supported version.
        config = overwriteConfig(config, "emulateSdk", "18");
        return super.pickSdkVersion(appManifest, config);
    }

    @Override
    protected void configureShadows(SdkEnvironment sdkEnvironment, Config config) {
        Properties properties = new Properties();
        // to add more shadows use white space separation + " " +
        properties.setProperty("shadows", ShadowMenuInflater.class.getName());
        super.configureShadows(sdkEnvironment, new Config.Implementation(config, Config.Implementation.fromProperties(properties)));
    }
}
