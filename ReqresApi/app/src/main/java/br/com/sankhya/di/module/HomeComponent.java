package br.com.sankhya.di.module;

import br.com.sankhya.di.Fragment;
import br.com.sankhya.di.app.AppComponent;
import br.com.sankhya.view.fragment.HomeFragment;
import dagger.Component;

@Fragment
@Component(dependencies = AppComponent.class, modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeFragment fragment);
}
