package com.example.howmuchdoyouknow.viewModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.howmuchdoyouknow.R;
import com.example.howmuchdoyouknow.database.Question;

import java.util.List;

//creating adapter for recycleView
public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.QuestionViewHolder>  {

    private final LayoutInflater mInflater;
    private List<Question> mQuestions; // Cached copy of words
    //Create constructor to initalize the inflater over the current activity UI
    public QuestionListAdapter(Context context) {mInflater=LayoutInflater.from(context);}

    //On creation, inflate the recycleview layout and return the holder view of it
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycleview_item,parent,false);
        return new QuestionViewHolder(itemView);
    }
    //once it's created and you have references to each view inside the layout from the holder provided by questionViewHolder,
    //attach and bind all views with their respective data from the question
    @Override
    public void onBindViewHolder(@NonNull QuestionListAdapter.QuestionViewHolder holder, int position) {
        if(mQuestions != null) {
            Question current = mQuestions.get(position);
            holder.questionItemView.setText(current.getQuestion());
            holder.answerA.setText(current.getA());
            holder.answerB.setText(current.getB());
            holder.answerC.setText(current.getC());
            holder.answerD.setText(current.getD());
        }else {
            holder.questionItemView.setText("Error finding questions");
        }
    }

    //update on change but idk if this is needed
    public void setmQuestions(List<Question> questions){
       mQuestions = questions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mQuestions != null){
            return mQuestions.size();
        }else {
            return 0;
        }
    }




    //Inner class of a single question holder and creating references to each view inside layout
    class QuestionViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionItemView;
        private final TextView answerA;
        private final TextView answerB;
        private final TextView answerC;
        private final TextView answerD;

        private QuestionViewHolder(View itemView) {
            super(itemView);
            questionItemView = itemView.findViewById(R.id.question);
            answerA = itemView.findViewById(R.id.textA);
            answerB = itemView.findViewById(R.id.textB);
            answerC = itemView.findViewById(R.id.textC);
            answerD = itemView.findViewById(R.id.textD);
        }

    }
}
