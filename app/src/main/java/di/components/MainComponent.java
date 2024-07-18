package di.components;


import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
import di.modules.AppModule;
import di.modules.MainModule;
import di.modules.NetworkModule;
import presentation.views.MainActivity;

@Singleton
@Component(modules = {AppModule.class, MainModule.class, NetworkModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
