package br.com.sankhya.view.fragment;


import android.app.ActivityOptions;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.sankhya.R;
import br.com.sankhya.ReqResApplication;
import br.com.sankhya.databinding.FragmentHomeBinding;
import br.com.sankhya.di.module.DaggerHomeComponent;
import br.com.sankhya.di.module.HomeModule;
import br.com.sankhya.model.UserItem;
import br.com.sankhya.network.response.Datum;
import br.com.sankhya.view.activity.UserDetailActivity;
import br.com.sankhya.view.adapter.HomeItemAdapter;
import br.com.sankhya.view.common.Common;
import br.com.sankhya.viewmodel.HomeViewmodel;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeViewmodel.HomeListener, HomeItemAdapter.HomeItemListener{

    FragmentHomeBinding binding;
    private HomeItemAdapter adapter;
    private List<UserItem> userItems;

    @Inject
    HomeViewmodel viewmodel;

    private static HomeFragment INSTANCE = null;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment getInstance() {
        if(INSTANCE == null)
            INSTANCE =  new HomeFragment();
        return INSTANCE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        userItems = new ArrayList<>();
        initIjection();
        initView();
        viewmodel.onLoadPagers();
        return binding.getRoot();
    }

    private void initView() {

    }

    private void initIjection() {
        DaggerHomeComponent.builder()
                .appComponent(((ReqResApplication) getActivity().getApplication()).getAppComponent())
                .homeModule(new HomeModule(this))
                .build().inject(this);
    }

    @Override
    public void onSucess(List<Datum> data) {
        binding.recyclerCategoria.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager( getActivity(),2);
        binding.recyclerCategoria.setLayoutManager(gridLayoutManager);
        adapter = new HomeItemAdapter(this, data, getContext());
        binding.recyclerCategoria.setAdapter(adapter);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onClickPreferences() {

    }

    @Override
    public void showProgress() {
        binding.recyclerCategoria.showShimmerAdapter();
    }

    @Override
    public void hideProgress() {
        binding.recyclerCategoria.hideShimmerAdapter();
    }

    @Override
    public void onClickSelect(View view, int layoutPosition) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Datum selectedCountry = adapter.getSelectedCountry(layoutPosition);

            View imgThumb = view.findViewById(R.id.imgWallpaperItem);

//            String transitionName = "wallpaper";
//            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
//                    Pair.create(imgThumb, transitionName));

            Intent intent = new Intent(getActivity(), UserDetailActivity.class);
            intent.putExtra(Common.USERS, selectedCountry);

                startActivity(intent);
        }

    }
}
