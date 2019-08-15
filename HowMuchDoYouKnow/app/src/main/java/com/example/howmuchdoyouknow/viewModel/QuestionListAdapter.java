package com.example.howmuchdoyouknow.viewModel;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    //remove question from list
    public void removeItem(int position) {
        mQuestions.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mQuestions.size());
    }




    //Inner class of a single question holder and creating references to each view inside layout
    class QuestionViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionItemView;
        private final TextView answerA;
        private final TextView answerB;
        private final TextView answerC;
        private final TextView answerD;
        private final Button a;
        private final Button b;
        private final Button c;
        private final Button d;
        private final Button reveal;
        private String answer="";

        private QuestionViewHolder(View itemView) {
            super(itemView);
            questionItemView = itemView.findViewById(R.id.question);
            reveal = itemView.findViewById(R.id.revealAnswer);
            answerA = itemView.findViewById(R.id.textA);
            answerB = itemView.findViewById(R.id.textB);
            answerC = itemView.findViewById(R.id.textC);
            answerD = itemView.findViewById(R.id.textD);
            a = itemView.findViewById(R.id.buttonA);
            b = itemView.findViewById(R.id.buttonB);
            c = itemView.findViewById(R.id.buttonC);
            d = itemView.findViewById(R.id.buttonD);
            a.setOnClickListener(answerClicked);
            b.setOnClickListener(answerClicked);
            c.setOnClickListener(answerClicked);
            d.setOnClickListener(answerClicked);
            answerA.setOnClickListener(answerClicked);
            answerB.setOnClickListener(answerClicked);
            answerC.setOnClickListener(answerClicked);
            answerD.setOnClickListener(answerClicked);
            reveal.setOnClickListener(revealAnswer);
        }


        //TODO:Add functionality for if someone clicks an answer
        //click method for setting the answer and showing the reveal answer
        private View.OnClickListener answerClicked = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(view == a || view == answerA){
                    reveal.setVisibility(view.VISIBLE);
                //Toast.makeText(answerA.getContext(),answerA.getText().toString(),Toast.LENGTH_SHORT).show();
                answer = answerA.getText().toString();
                }
                if(view == b|| view == answerB){
                   // Toast.makeText(answerB.getContext(),answerB.getText().toString(),Toast.LENGTH_SHORT).show();
                    reveal.setVisibility(view.VISIBLE);
                    answer = answerB.getText().toString();
                }
                if(view == c|| view == answerC){
                   // Toast.makeText(answerC.getContext(),answerC.getText().toString(),Toast.LENGTH_SHORT).show();
                    reveal.setVisibility(view.VISIBLE);
                    answer = answerC.getText().toString();
                }
                if(view == d|| view == answerD){
                    //Toast.makeText(answerD.getContext(),answerD.getText().toString(),Toast.LENGTH_SHORT).show();
                    reveal.setVisibility(view.VISIBLE);
                    answer = answerD.getText().toString();
                }
            }
        };
        //Click method to reveal answer and remove the reveal button
        private View.OnClickListener revealAnswer = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer == ""){
                    Toast.makeText(view.getContext(),"No Answer Selected",Toast.LENGTH_LONG).show();
                    view.setVisibility(view.INVISIBLE);
                }
                Toast toast = Toast.makeText(view.getContext(),answer,Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                view.setVisibility(view.INVISIBLE);
            }
        };
    }
}
