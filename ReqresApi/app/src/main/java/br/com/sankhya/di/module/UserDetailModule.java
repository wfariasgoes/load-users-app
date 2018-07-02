package br.com.sankhya.di.module;

import br.com.sankhya.di.Activity;
import br.com.sankhya.viewmodel.UserDatailViewmodel;
import dagger.Module;
import dagger.Provides;

@Module
public class UserDetailModule {

    UserDatailViewmodel.UserDetailListener listener;

    public UserDetailModule(UserDatailViewmodel.UserDetailListener listener) {
        this.listener = listener;
    }

    @Provides
    @Activity
    UserDatailViewmodel providerUserDatailViewmodel(){
        return new UserDatailViewmodel(listener);
    }
}
