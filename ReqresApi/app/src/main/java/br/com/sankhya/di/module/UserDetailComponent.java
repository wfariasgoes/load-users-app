package br.com.sankhya.di.module;

import br.com.sankhya.di.Activity;
import br.com.sankhya.di.app.AppComponent;
import br.com.sankhya.view.activity.UserDetailActivity;
import dagger.Component;
@Activity
@Component(dependencies = AppComponent.class, modules = UserDetailModule.class)
public interface UserDetailComponent {
    void inject(UserDetailActivity activity);
}
