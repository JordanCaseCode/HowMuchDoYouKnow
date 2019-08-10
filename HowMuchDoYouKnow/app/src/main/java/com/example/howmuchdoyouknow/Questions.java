package com.example.howmuchdoyouknow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

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
    private QuestionListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        //pull in genre from the passed intent from main
        Intent intent = getIntent();
        genre = intent.getStringExtra("Genre");

        //add recycle view to activity
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        //create adapter to use on recyclerView
        adapter = new QuestionListAdapter(this);
        //set recyclerview horizontal and remove scrolling option
        //will set ability to swipe up to remove question from list and go to next question
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(HORIZONTAL_ITEM_SPACE));
        recyclerView.setAdapter(adapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createItemTouchHelper());
        itemTouchHelper.attachToRecyclerView(recyclerView);
        //get a ViewModel from the ViewModelProviders class
        mQuestionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        //add an observer for the LiveData returned by getQuestionsByGenre()
        mQuestionsViewModel.getGenreQuestions(genre).observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                adapter.setmQuestions(questions);
            }
        });
        //create swipe commands

    }

    //create swiping ability to delete item from list
        private ItemTouchHelper.Callback createItemTouchHelper() {
            ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.UP) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    if (direction == ItemTouchHelper.UP) {
                        adapter.removeItem(viewHolder.getAdapterPosition());
                    }
                }

            };
            return itemTouchHelperCallback;
        }

}
