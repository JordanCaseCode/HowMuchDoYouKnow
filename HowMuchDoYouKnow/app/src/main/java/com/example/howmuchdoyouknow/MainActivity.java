package com.example.howmuchdoyouknow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //create member variables for genre selectors
    private Boolean genre1clicked = false;
    private Boolean genre2clicked = false;
    private Boolean genre3clicked = false;
    private Boolean genre4clicked = false;
    private Boolean genre5clicked = false;
    private Boolean genre6clicked = false;
    //create key strings for saveInstanceState
    private String G1STATE = "GENRE1STATE";
    private String G2STATE = "GENRE2STATE";
    private String G3STATE = "GENRE3STATE";
    private String G4STATE = "GENRE4STATE";
    private String G5STATE = "GENRE5STATE";
    private String G6STATE = "GENRE6STATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null) {
            resetSelectors(savedInstanceState);
        }
    }


    //reset selectors if app has been destroyed from orientation change
    private void resetSelectors(Bundle savedInstanceState) {
        genre1clicked = savedInstanceState.getBoolean(G1STATE);
        genre2clicked = savedInstanceState.getBoolean(G2STATE);
        genre3clicked = savedInstanceState.getBoolean(G3STATE);
        genre4clicked = savedInstanceState.getBoolean(G4STATE);
        genre5clicked = savedInstanceState.getBoolean(G5STATE);
        genre6clicked = savedInstanceState.getBoolean(G6STATE);
    }


    //when genres selected, go to questions generated
    public void goToQuestions(View view) {
        //if a genre has been selected, start the intent
        if(isAnyGenreSelected()) {
            // TODO:Add in functionality for sending what genre is selected to database to get questions
            Intent goToQIntent = new Intent(this, Questions.class);
            startActivity(goToQIntent);
        } else {
            Toast.makeText(this,"Must Select Genre Before",Toast.LENGTH_SHORT).show();
        }
    }
    //simple check to see if any genre is selected
    private boolean isAnyGenreSelected() {
        //check if any selector is set to true
        boolean selected = false;
        if(genre1clicked | genre2clicked | genre3clicked | genre4clicked | genre5clicked | genre6clicked) {
            selected = true;
        }
        return selected;
    }


    //create key,value pairs for each genre clicked state
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(G1STATE,genre1clicked);
        outState.putBoolean(G2STATE,genre2clicked);
        outState.putBoolean(G3STATE,genre3clicked);
        outState.putBoolean(G4STATE,genre4clicked);
        outState.putBoolean(G5STATE,genre5clicked);
        outState.putBoolean(G6STATE,genre6clicked);
        super.onSaveInstanceState(outState);
    }

    //TODO:Add functionality for switch to make floating button dimmed and non-clickable
    //if a genre is clicked, set boolean to next state( true-> false or false ->true)
    public void genreSelected(View view) {
        int id = view.getId();
        String toast = "";
        switch (id) {
            case R.id.genre1:
                if (genre1clicked == false) {
                    genre1clicked = true;
                    toast = "politics selected";
                } else {
                    genre1clicked = false;
                    toast = "politics unselected";
                }
                break;
            case R.id.genre2:
                if (genre2clicked == false) {
                    genre2clicked = true;
                    toast = "sex selected";
                } else {
                    genre2clicked = false;
                    toast = "sex unselected";
                }
                break;
            case R.id.genre3:
                if (genre3clicked == false) {
                    genre3clicked = true;
                    toast = "love selected";
                } else {
                    genre3clicked = false;
                    toast = "love unselected";
                }
                break;
            case R.id.genre4:
                if (genre4clicked == false) {
                    genre4clicked = true;
                    toast = "personality selected";
                } else {
                    genre3clicked = false;
                    toast = "personality unselected";
                }
                break;
            case R.id.genre5:
                if (genre5clicked == false) {
                    genre5clicked = true;
                    toast = "ethics selected";
                } else {
                    genre5clicked = false;
                    toast = "ethics unselected";
                }
                break;
            case R.id.genre6:
                if (genre6clicked == false) {
                    genre6clicked = true;
                    toast = "Life & Death selected";
                } else {
                    genre6clicked = false;
                    toast = "Life & Death unselected";
                }
                break;
            default:
                break;
        }
        if (toast != "") {
            Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
        }
    }
}

