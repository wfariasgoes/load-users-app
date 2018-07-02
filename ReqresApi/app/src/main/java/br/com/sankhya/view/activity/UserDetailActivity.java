package br.com.sankhya.view.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.com.sankhya.R;
import br.com.sankhya.ReqResApplication;
import br.com.sankhya.database.UserRecents;
import br.com.sankhya.database.datasource.RecentRepository;
import br.com.sankhya.database.localdatabase.LocalDatabase;
import br.com.sankhya.database.localdatabase.RecentsDataSource;
import br.com.sankhya.databinding.ActivityUserDetailBinding;
import br.com.sankhya.di.module.DaggerHomeComponent;
import br.com.sankhya.di.module.DaggerUserDetailComponent;
import br.com.sankhya.di.module.HomeModule;
import br.com.sankhya.di.module.UserDetailModule;
import br.com.sankhya.facade.ManagementBO;
import br.com.sankhya.model.UserItem;
import br.com.sankhya.network.response.Datum;
import br.com.sankhya.view.common.Common;
import br.com.sankhya.viewmodel.UserDatailViewmodel;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class UserDetailActivity extends AppCompatActivity implements UserDatailViewmodel.UserDetailListener {

    ActivityUserDetailBinding binding;
    private Datum data;
    private  UserItem userItem;

    @Inject
    UserDatailViewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail);
        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        data = (Datum) intent.getSerializableExtra(Common.USERS);
        Common.select_user = data;
//        if (Common.select_user.getFavourite() == null){
//            Toast.makeText(this, "Não é favorito", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this, "É favorito", Toast.LENGTH_SHORT).show();
//        }
        initIjection();
        initView();
        binding.setUserDetail(viewmodel);
    }

    private void initIjection() {
        DaggerUserDetailComponent.builder()
                .appComponent(((ReqResApplication) getApplication()).getAppComponent())
                .userDetailModule(new UserDetailModule(this))
                .build().inject(this);
    }

    private void initView() {
//        binding.imgWallpaperItem.

        binding.collapsing.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        binding.collapsing.setExpandedTitleTextAppearance(R.style.ExpanableAppBar);
        binding.collapsing.setTitle(data.getFirstName()+" "+data.getLastName());
        binding.tvModel.setText(data.getFirstName()+" "+data.getLastName());
        Picasso.with(this)
                .load(data.getAvatar())
                .into(binding.ivPhoto);

        Picasso.with(this)
                .load(data.getAvatar())
                .into(binding.imgWallpaperItem);
    }


    @Override
    public void onClickSaveDetail() {
        userItem = new UserItem();
        userItem.setFirstName(data.getFirstName());
        userItem.setLastName(data.getLastName());
        userItem.setAvatar(data.getAvatar());
        ManagementBO.getInstance().addUser(userItem);
        Toast.makeText(this, "Dados salvos!", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }


}
