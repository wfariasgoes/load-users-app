package br.com.sankhya.viewmodel;

import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import javax.inject.Inject;

import br.com.sankhya.R;


public class MainViewModel {
    private MainListener listener;
    public Handler handler;

    public MainViewModel(MainListener listener) {
        this.listener = listener;
        this.handler = new Handler();
    }

    @BindingMethods({
            @BindingMethod(
                    type = BottomNavigationView.class,
                    attribute = "onNavigationItemSelected",
                    method = "setOnNavigationItemSelectedListener"
            ),
    })

    public class Handler {
        public boolean onNavigationClick(@NonNull MenuItem item) {


            if (item.getItemId() == R.id.action_perfil) {
                listener.onClickUser();
            } else if (item.getItemId() == R.id.action_countries) {
                listener.onClickRecents();
            } else if (item.getItemId() == R.id.action_visits) {
                listener.onClickPreferences();
            }
            return true;
        }
    }

    public interface MainListener{
        void onClickUser();
        void onClickRecents();
        void onClickPreferences();
    }
}
