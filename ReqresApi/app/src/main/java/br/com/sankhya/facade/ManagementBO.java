package br.com.sankhya.facade;

import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.sankhya.ReqResApplication;
import br.com.sankhya.business.AmbienteManager;
import br.com.sankhya.business.ObjectAlreadyExistException;
import br.com.sankhya.model.UserItem;
import br.com.sankhya.network.response.Datum;

public class ManagementBO {


    private static ManagementBO instance;
    private static final Object SYNCOBJECT = new Object();
    private AmbienteManager mAmbienteManager;

//    private Country mCountrieSelection;


    public static ManagementBO getInstance() {
        synchronized (SYNCOBJECT) {
            if (instance == null) {
                instance = new ManagementBO();
            }
        }
        return instance;
    }



    /**          ********************** USER *******************                   **/

     /*
    * Adicionar User
     */
    public void addUser(final UserItem user) {
        mAmbienteManager = (AmbienteManager) ReqResApplication.getInstance().get(AmbienteManager.KEY);
        try {
            mAmbienteManager.insertUser(user);
        } catch (SQLException e) {
            Log.d("Erro ao adicionar uma nova pesquisa" , ""+e.getMessage());
        } catch (ObjectAlreadyExistException e) {
            e.getMessage();
        }
    }

    /*
    * Update
     */
    public void updateUser(final UserItem user) {
        try {
            mAmbienteManager.updateUser(user);
        } catch (SQLException e) {
            Log.i("","Erro ao atualizar Url " + e.getMessage());
        }
    }

    public List<UserItem> getUsers() {
        mAmbienteManager = (AmbienteManager) ReqResApplication.getInstance().get(AmbienteManager.KEY);
        List<UserItem> questions = new ArrayList<UserItem>();
        try {
            questions = mAmbienteManager.getUsers();
        } catch (SQLException e) {
            Log.i(""," Não foi possível consultar dados cadastrados. " + e.getMessage());
        }
        return questions;
    }

    public void deleteUser(final UserItem user) {
        mAmbienteManager = (AmbienteManager) ReqResApplication.getInstance().get(AmbienteManager.KEY);
        try {
            mAmbienteManager.deleteUser(user);
        } catch (SQLException e) {
            Log.i("Erro ao excluir um Item" ,e.getMessage());
        }
    }

}
