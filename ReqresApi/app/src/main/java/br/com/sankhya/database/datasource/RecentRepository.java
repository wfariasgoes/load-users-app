package br.com.sankhya.database.datasource;


import java.util.List;

import br.com.sankhya.database.UserRecents;
import br.com.sankhya.network.response.Datum;
import io.reactivex.Flowable;

public class RecentRepository implements IRecentsDataSource{

    private IRecentsDataSource mLocalDataSource;
    private static RecentRepository instance;

    public RecentRepository(IRecentsDataSource mLocalDataSource) {
        this.mLocalDataSource = mLocalDataSource;
    }

    public static RecentRepository getInstance(IRecentsDataSource mLocalDataSource) {
        if (instance == null)
            instance = new RecentRepository(mLocalDataSource);
        return instance;
    }

    @Override
    public Flowable<List<Datum>> getAllRecents() {
        return mLocalDataSource.getAllRecents();
    }

    @Override
    public void insertRecents(Datum... recents) {
        mLocalDataSource.insertRecents(recents);
    }

    @Override
    public void updateRecents(Datum... recents) {
        mLocalDataSource.updateRecents(recents);
    }

    @Override
    public void deleteRecents(Datum... recents) {
        mLocalDataSource.deleteRecents(recents);
    }

    @Override
    public void deleteAllRecents() {
        mLocalDataSource.deleteAllRecents();
    }
}
