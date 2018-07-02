package br.com.sankhya.di.module;

import br.com.sankhya.di.Activity;
import br.com.sankhya.di.app.AppComponent;
import br.com.sankhya.view.activity.HomeActivity;
import dagger.Component;

@Activity
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(HomeActivity activity);
}
