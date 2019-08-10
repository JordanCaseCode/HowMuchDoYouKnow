package com.example.howmuchdoyouknow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.howmuchdoyouknow.database.Question;
import com.example.howmuchdoyouknow.viewModel.HorizontalSpaceItemDecoration;
import com.example.howmuchdoyouknow.viewModel.QuestionListAdapter;
import com.example.howmuchdoyouknow.viewModel.QuestionsViewModel;

import java.util.List;

public class Questions extends AppCompatActivity {
//create member variable for viewmodel because all the activity's interactions are with the WordViewModel only
    private QuestionsViewModel mQuestionsViewModel;
    private String genre = "";
    private final int HORIZONTAL_ITEM_SPACE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        //pull in genre from the passed intent from main
        Intent intent = getIntent();
        genre = intent.getStringExtra("Genre");

        //add recycle view to activity
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final QuestionListAdapter adapter = new QuestionListAdapter(this);
        //set recyclerview horizontal and remove scrolling option
        //will set ability to swipe up to remove question from list and go to next question
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        });
        recyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(HORIZONTAL_ITEM_SPACE));
        recyclerView.setAdapter(adapter);
        //get a ViewModel from the ViewModelProviders class
        mQuestionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        //add an observer for the LiveData returned by getQuestionsByGenre()
        mQuestionsViewModel.getGenreQuestions(genre).observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                // Update the cached copy of the questions in the adapter.
                adapter.setmQuestions(questions);
            }
        });
    }
}
