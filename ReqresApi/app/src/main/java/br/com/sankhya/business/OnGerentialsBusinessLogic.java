package br.com.sankhya.business;

import java.sql.SQLException;
import java.util.List;

import br.com.sankhya.model.UserItem;
import br.com.sankhya.network.response.Datum;


public interface OnGerentialsBusinessLogic {

    void insertUser(UserItem user) throws SQLException, ObjectAlreadyExistException;
    List<UserItem> getUsers() throws SQLException;
    List<UserItem> getSearchUsers() throws SQLException;
    void updateUser(UserItem user) throws SQLException;
    void deleteUser(UserItem user) throws SQLException;
}
