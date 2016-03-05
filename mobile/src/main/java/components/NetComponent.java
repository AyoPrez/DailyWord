package components;

import com.ayoprez.deilylang.MainActivity;
import com.ayoprez.modules.AppModule;
import com.ayoprez.modules.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ayo on 20.02.16.
 */
@Singleton
@Component(modules={AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
    // void inject(MyFragment fragment);
    // void inject(MyService service);
}