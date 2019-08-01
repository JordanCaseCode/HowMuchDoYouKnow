package com.example.howmuchdoyouknow.Database;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

//create repository to add level of abstraction from database
public class Repository {
    private QuestionsDao questionsDao;
    private LiveData<List<Question>> mQuestions;

    //constructor for the repository
    Repository(Application application) {
        QuestionDatabase db = QuestionDatabase.getDatabase(application);
        questionsDao = db.questionsDao();
        mQuestions = questionsDao.getAllQuestions();
    }


    //set up abstraction for methods through repository
    LiveData<List<Question>> getAllQuestions() {
        return mQuestions;
    }


    //make abstraction for inserting on background thread due to UI crash
    public void insert(Question question) {
        new insertAsyncTask(questionsDao).execute(question);
    }

    private static class insertAsyncTask extends AsyncTask<Question, Void, Void> {

        private QuestionsDao mAsyncTaskDao;

        insertAsyncTask(QuestionsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Question... params) {
            mAsyncTaskDao.Insert(params[0]);
            return null;
        }
    }


}
