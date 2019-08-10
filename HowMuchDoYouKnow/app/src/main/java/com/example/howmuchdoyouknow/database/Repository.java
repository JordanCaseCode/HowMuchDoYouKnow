package com.example.howmuchdoyouknow.database;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

//create repository to add level of abstraction from database
public class Repository {
    private QuestionsDao questionsDao;
    private LiveData<List<Question>> mQuestions;

    //constructor for the repository
    public Repository(Application application) {
        QuestionDatabase db = QuestionDatabase.getDatabase(application);
        questionsDao = db.questionsDao();
        mQuestions = questionsDao.getAllQuestions();
    }


    //set up abstraction for methods through repository using wrappers
    public LiveData<List<Question>> getAllQuestions() {
        return mQuestions;
    }

    public void deleteAll() {
        new deleteAllAsyncTask(questionsDao).execute();
    }

    public void insert(Question question) {
        new insertAsyncTask(questionsDao).execute(question);
    }

    //NOTE: might cause error because not on asynctask thread
    public LiveData<List<Question>> getQuestionsByGenre(String genre) {
        return questionsDao.getGenreQuestions(genre);
    }


    //implementation of all AsyncTasks for database
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

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private QuestionsDao mAsyncTaskDao;

        public deleteAllAsyncTask(QuestionsDao dao) {
            mAsyncTaskDao = dao;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    //not sure how to handle this one with querying... will come back to it
    private static class genreQuestionsAsyncTask extends AsyncTask<String, Void, LiveData<List<Question>>> {
        private QuestionsDao mAsyncTaskDao;
        private LiveData<List<Question>> genreQuestions;

        public genreQuestionsAsyncTask(QuestionsDao questionsDao) {
            mAsyncTaskDao = questionsDao;

        }

        @Override
        protected LiveData<List<Question>> doInBackground(String... strings) {
            return genreQuestions = mAsyncTaskDao.getGenreQuestions(strings[0]);
        }

        //attempting to set the member variable for the repository to the genre questions then ill return it when called
        @Override
        protected void onPostExecute(LiveData<List<Question>> listLiveData) {
            super.onPostExecute(listLiveData);

        }
    }
}
