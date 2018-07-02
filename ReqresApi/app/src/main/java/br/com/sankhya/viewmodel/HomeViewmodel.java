package br.com.sankhya.viewmodel;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.sankhya.ReqResApplication;
import br.com.sankhya.network.UserLoadService;
import br.com.sankhya.network.response.Datum;
import br.com.sankhya.network.response.UserResponse;
import br.com.sankhya.view.common.Common;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewmodel {

    private HomeListener listener;
    private List<Datum> users = new ArrayList<>();
    private Datum data;
    private int finalI;

    public HomeViewmodel(HomeListener listener) {
        this.listener = listener;
    }

    public void onLoadPagers() {
        ReqResApplication.getInstance()
                .getApiClient()
                .getRetrofit().create(UserLoadService.class)
                .getCountPage(1)
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        onLoadUsers(response.body());
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {

                    }
                });

    }


    private void onLoadUsers(UserResponse body) {
//        data = data.getData().get();
        listener.showProgress();
        for (finalI = 0; finalI < body.getTotalPages(); finalI++){
            ReqResApplication.getInstance()
                    .getApiClient()
                    .getRetrofit().create(UserLoadService.class)
                    .getUsers(finalI)
                    .enqueue(new Callback<UserResponse>() {
                        @Override
                        public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                            listener.hideProgress();
                            for (Datum d : response.body().getData()) {
                                Log.v("POINT", "" + d.getId());
                                users.add(d);
                                listener.onSucess(users);

                            }
                        }

                        @Override
                        public void onFailure(Call<UserResponse> call, Throwable t) {

                        }
                    });
        }


    }

    public void onClickPreferences() {
        listener.onClickPreferences();
    }

    public interface HomeListener {
        void onSucess(List<Datum> data);
        void onError();
        void onClickPreferences();
        void showProgress();
        void hideProgress();
    }
}
