package br.com.sankhya.viewmodel;

public class UserDatailViewmodel {

    private UserDetailListener listener;

    public UserDatailViewmodel(UserDetailListener listener) {
        this.listener = listener;
    }

    public void onClickSaveDetail(){
        listener.onClickSaveDetail();
    }

    public interface UserDetailListener{
        void onClickSaveDetail();
//        void onClikDelete
    }
}
