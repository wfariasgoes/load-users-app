package br.com.sankhya.di.module;

import br.com.sankhya.di.Fragment;
import br.com.sankhya.viewmodel.HomeViewmodel;
import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    private HomeViewmodel.HomeListener listener;

    public HomeModule(HomeViewmodel.HomeListener listener) {
        this.listener = listener;
    }

    @Provides
    @Fragment
    HomeViewmodel providerHomeViewmodel(){
        return  new HomeViewmodel(listener);
    }
}
