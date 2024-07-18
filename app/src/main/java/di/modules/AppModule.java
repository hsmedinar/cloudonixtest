package di.modules;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    Application application;

    public AppModule(Application application){
        this.application=application;
    }


    @Provides
    public Application provideApplication(){
        return application;
    }


    @Provides
    public Context provideContextApplication(){
        return application.getApplicationContext();
    }

}
