package com.example.howmuchdoyouknow.database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.howmuchdoyouknow.Questions;

import java.util.List;

//define database to use the question entity, start at version 1 and not allow migration outward
@Database(entities = {Question.class}, version = 1, exportSchema = false)
public abstract class QuestionDatabase extends RoomDatabase {

    //abstract construction of dao
    public abstract QuestionsDao questionsDao();

    //create instance of database
    private static QuestionDatabase INSTANCE;

    //now use method to instantiate the database using a singleton pattern
    static QuestionDatabase getDatabase(final Context context) {
        //check to see if database is instantiated
        if (INSTANCE == null) {
            //if not, class lock the database so two threads cannot instantiate this database at the same time
            synchronized (QuestionDatabase.class) {
                //then make sure its still not instantiated
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), QuestionDatabase.class, "Question Database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    //create database callback for pre-populating the database
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
    //async class for inserting prepopulated data
    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void> {

        private final QuestionsDao questionsDao;

        public PopulateDbAsync(QuestionDatabase instance) {
            questionsDao = instance.questionsDao();
        }
        // insert data into database from the questiondata class
        @Override
        protected Void doInBackground(Void... voids) {
            questionsDao.insertAll(QuestionData.populateQuestionData());
            return null;
        }
    }
}
