package br.com.sankhya.business;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

import br.com.sankhya.model.UserItem;
import br.com.sankhya.network.response.Datum;
import br.com.sankhya.persistence.DataBaseHelper;


public class AmbienteManager implements OnGerentialsBusinessLogic {

    public static final String KEY = "ModelBO";
    private DataBaseHelper dataBaseHelper;
    private static AmbienteManager SINGLETON;

    public static AmbienteManager getInstance() {
        if (SINGLETON == null) {
            SINGLETON = new AmbienteManager();
        }
        return SINGLETON;
    }

    public void startSession(Context ctx) {
        dataBaseHelper = new DataBaseHelper(ctx);
        dataBaseHelper.getWritableDatabase();
    }


    //*****************     User   ***********************
    @Override
    public void insertUser(UserItem user) throws SQLException, ObjectAlreadyExistException {
        Dao<UserItem, Long> dao = dataBaseHelper.getDao(UserItem.class);
        dataBaseHelper.getDao(UserItem.class).createOrUpdate(user);
    }

    @Override
    public List<UserItem> getUsers() throws SQLException {
        List<UserItem> result = null;
        QueryBuilder queryBuilder = dataBaseHelper.getDao(UserItem.class).queryBuilder();
        result = queryBuilder.query();
        return result;
    }

    @Override
    public List<UserItem> getSearchUsers() throws SQLException {
        Dao<UserItem, Long> dao =  dataBaseHelper.getDao( UserItem.class );
        List<UserItem> result = dao.queryForEq( "first_name" , true);
        return result;
    }


    @Override
    public void updateUser(UserItem user) throws SQLException {
        UpdateBuilder updateBuilder = dataBaseHelper.getDao(UserItem.class).updateBuilder();
        updateBuilder.where().eq("management_id",user.getId());
        updateBuilder.updateColumnValue("first_name",user.getFirstName());
        updateBuilder.updateColumnValue("last_name",user.getLastName());
        updateBuilder.updateColumnValue("favourite",user.getFavourite());
        updateBuilder.updateColumnValue("avatar",user.getAvatar());
        updateBuilder.update();

    }

    @Override
    public void deleteUser(UserItem user) throws SQLException {
        dataBaseHelper.getDao(UserItem.class).delete(user);
    }


}
