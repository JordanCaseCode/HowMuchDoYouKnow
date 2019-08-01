package com.example.howmuchdoyouknow.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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

                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
