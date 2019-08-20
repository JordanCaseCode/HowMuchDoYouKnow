package com.example.howmuchdoyouknow.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.howmuchdoyouknow.database.Question;
import com.example.howmuchdoyouknow.database.Repository;

import java.util.List;

//View model to update UI with questions
public class QuestionsViewModel extends AndroidViewModel {
    // Using LiveData and caching what getGenreQuestions returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private Repository mRepository;

    public QuestionsViewModel(Application application) {
        super(application);
        mRepository = new Repository(application);
    }
    //TODO: set up wrapper call to get multiple genres and bring them all into the adapter viewer for the recyclerview
    //Set up wrapper calls to repository to perform operations on database
    public LiveData<List<Question>> getGenreQuestions(String genre) {
        return mRepository.getQuestionsByGenre(genre);
    }
    public void insert(Question q) {
        mRepository.insert(q);
    }


}
