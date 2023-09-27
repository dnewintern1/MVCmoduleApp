package com.base.androidroommvvm.room_database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MartialArtRepository {

    private MartialArtDAO martialArtDAO;

    private LiveData<List<MartialArt>> allMartialArts;


    public MartialArtRepository(Application application) {


        MartialArtRoomDatabase martialArtRoomDatabase = MartialArtRoomDatabase.getDatabase(application);

        martialArtDAO = martialArtRoomDatabase.martialArtDAO();


        allMartialArts = martialArtDAO.getAllMartialArtsInAnAlphabeticalOrder(); // Room executes all queries on a separate thread so the UI thread is not blocked.

    }


    public LiveData<List<MartialArt>> getAllMartialArts() {


        return allMartialArts;

    }


    public void insertMartialArt(MartialArt martialArt) {


        MartialArtRoomDatabase.databaseWriterExecutor.execute(() -> martialArtDAO.insertMartialArt(martialArt));


    }


    public void deleteMartialArt(MartialArt martialArt) {


        MartialArtRoomDatabase.databaseWriterExecutor.execute(() -> martialArtDAO.deleteMartialArt(martialArt));
    }
}
