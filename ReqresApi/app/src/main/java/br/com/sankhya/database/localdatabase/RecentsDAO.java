package br.com.sankhya.database.localdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import java.util.List;

import br.com.sankhya.database.UserRecents;
import br.com.sankhya.network.response.Datum;
import io.reactivex.Flowable;

@Dao
public interface RecentsDAO {

    @Query("SELECT * FROM recents ORDER BY saveTime DESC LIMIT 10")
    Flowable<List<Datum>> getAllRecents();

    @Insert
    void insertRecents(Datum... recents);

    @Update
    void updateRecents(Datum... recents);

    @Delete
    void deleteRecents(Datum... recents);

    @Query("DELETE FROM recents")
    void deleteAllRecents();
}
