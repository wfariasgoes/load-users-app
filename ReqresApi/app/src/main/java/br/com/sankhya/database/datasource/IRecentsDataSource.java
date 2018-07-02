package br.com.sankhya.database.datasource;


import java.util.List;

import br.com.sankhya.database.UserRecents;
import br.com.sankhya.network.response.Datum;
import io.reactivex.Flowable;

public interface IRecentsDataSource {
    Flowable<List<Datum>> getAllRecents();
    void insertRecents(Datum... recents);
    void updateRecents(Datum... recents);
    void deleteRecents(Datum... recents);
    void deleteAllRecents();
}
