package br.com.sankhya.di.module;

import br.com.sankhya.di.Activity;
import br.com.sankhya.viewmodel.MainViewModel;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private MainViewModel.MainListener listener;

    public MainModule(MainViewModel.MainListener listener) {
        this.listener = listener;
    }

    @Activity
    @Provides
    MainViewModel providerMainViewModel(){
        return new MainViewModel(listener);
    }

    @Provides
    @Activity
    MainViewModel.Handler providerMainViewModelHandler(MainViewModel viewModel){
        return viewModel.handler;
    }
}
