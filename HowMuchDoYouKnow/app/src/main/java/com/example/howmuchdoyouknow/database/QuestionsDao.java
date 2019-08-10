package com.example.howmuchdoyouknow.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionsDao {


    //add a question
    @Insert
    void Insert(Question question);
    //used for pre-populating db
    @Insert
    void insertAll(Question[] questions);

    //update a question
    @Update
    void updateQuestion(Question question);

    //delete a question with this name
    @Query("DELETE FROM Questions WHERE question LIKE :question")
    void deleteSelected(String question);

    //delete the entire database
    @Query("DELETE FROM Questions")
    void deleteAll();


    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.


    //select all of the questions
    @Query("SELECT * FROM Questions")
    LiveData<List<Question>> getAllQuestions();



    //select all questions that have this type of genre
    @Query("SELECT * FROM Questions WHERE mGenre LIKE :genre")
    LiveData<List<Question>> getGenreQuestions(String genre);






}
