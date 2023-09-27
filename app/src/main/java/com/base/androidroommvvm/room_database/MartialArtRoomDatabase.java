package com.base.androidroommvvm.room_database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {MartialArt.class}, version = 1, exportSchema = false)

    public abstract class MartialArtRoomDatabase extends RoomDatabase {


    public abstract MartialArtDAO martialArtDAO();


    private static volatile MartialArtRoomDatabase INSTANCE;


    private static final int NUNBER_OF_THREADS = 4;


    public static final ExecutorService databaseWriterExecutor = Executors.newFixedThreadPool(NUNBER_OF_THREADS);


    public static MartialArtRoomDatabase getDatabase(Context context) {


        if (INSTANCE == null) {


            synchronized (MartialArtRoomDatabase.class) {


                INSTANCE = Room.databaseBuilder(context, MartialArtRoomDatabase.class, "martial_art_database").build();


            }

        }


        return INSTANCE;


    }

    private static RoomDatabase.Callback databaseCallback = new RoomDatabase.Callback() {



        @Override

        public void onCreate(@NonNull SupportSQLiteDatabase db) {

            super.onCreate(db);

            databaseWriterExecutor.execute(() -> {



                // Delete all database data

                MartialArtDAO dao = INSTANCE.martialArtDAO();

                dao.deleteAllMartialArt();



                // Add some default data

                MartialArt martialArt = new MartialArt("Boxing");

                dao.insertMartialArt(martialArt);



                martialArt = new MartialArt("Judo");

                dao.insertMartialArt(martialArt);



            });



        }

    };


}

