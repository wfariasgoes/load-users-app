package br.com.sankhya.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import javax.inject.Inject;

import br.com.sankhya.R;
import br.com.sankhya.ReqResApplication;
import br.com.sankhya.databinding.ActivityHomeBinding;
import br.com.sankhya.di.module.DaggerMainComponent;
import br.com.sankhya.di.module.MainModule;
import br.com.sankhya.view.BasicActivity;
import br.com.sankhya.view.adapter.MyFragmentAdapter;
import br.com.sankhya.view.common.Common;
import br.com.sankhya.view.fragment.HomeFragment;
import br.com.sankhya.view.fragment.RecenteFragment;
import br.com.sankhya.viewmodel.MainViewModel;

public class HomeActivity extends BasicActivity implements MainViewModel.MainListener{

    ActivityHomeBinding binding;

    @Inject
    MainViewModel viewModel;

    @Inject
    MainViewModel.Handler handler;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case Common.PERMISSION_REQUEST_CODE:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "Permissão concedida", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, "Você precisa aceitar a permissão para baixar a imagem", Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.toolbar.setTitle("Teste Sankhya");
        setSupportActionBar(binding.toolbar);
        initInjectors();
        binding.setMain(viewModel);
        binding.setHandler(handler);
        binding.toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));

        //Request runtime permission
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE ) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Common.PERMISSION_REQUEST_CODE);
        }
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment, HomeFragment.getInstance())
                    .commit();
        }
    }

    private void initInjectors() {
        DaggerMainComponent.builder()
                .appComponent(((ReqResApplication) getApplication()).getAppComponent())
                .mainModule(new MainModule(this))
                .build().inject(this);
    }



    @Override
    public void onClickUser() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, HomeFragment.getInstance())
                .commit();
    }

    @Override
    public void onClickRecents() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, RecenteFragment.getInstance())
                .commit();
    }

    @Override
    public void onClickPreferences() {

    }
}
