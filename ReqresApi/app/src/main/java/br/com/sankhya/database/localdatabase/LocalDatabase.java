package br.com.sankhya.database.localdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


import br.com.sankhya.database.UserRecents;
import br.com.sankhya.network.response.Datum;

import static br.com.sankhya.database.localdatabase.LocalDatabase.DATABASE_VERSION;


public abstract class LocalDatabase extends RoomDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ReqresApi";

    public abstract RecentsDAO recentsDAO();

    private static LocalDatabase instance;

    public static LocalDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder( context, LocalDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
