package br.com.sankhya.database.localdatabase;


import java.util.List;

import br.com.sankhya.database.UserRecents;
import br.com.sankhya.database.datasource.IRecentsDataSource;
import br.com.sankhya.network.response.Datum;
import io.reactivex.Flowable;

public class RecentsDataSource implements IRecentsDataSource{

    private RecentsDAO recentsDAO;
    private static RecentsDataSource instance;

    public RecentsDataSource(RecentsDAO recentsDAO) {
        this.recentsDAO = recentsDAO;
    }

    public static RecentsDataSource getInstance(RecentsDAO recentsDAO) {
        if (instance == null)
            instance = new RecentsDataSource(recentsDAO);
        return instance;
    }

    @Override
    public Flowable<List<Datum>> getAllRecents() {
        return recentsDAO.getAllRecents();
    }

    @Override
    public void insertRecents(Datum... recents) {
        recentsDAO.insertRecents(recents);
    }

    @Override
    public void updateRecents(Datum... recents) {
        recentsDAO.updateRecents(recents);
    }

    @Override
    public void deleteRecents(Datum... recents) {
        recentsDAO.deleteRecents(recents);
    }

    @Override
    public void deleteAllRecents() {
        recentsDAO.deleteAllRecents();
    }
}
