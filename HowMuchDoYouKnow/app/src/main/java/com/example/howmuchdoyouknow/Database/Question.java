package com.example.howmuchdoyouknow.Database;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Questions")
public class Question {

    //make a primary key (Not really used)
    @ColumnInfo(name = "primaryKey")
    @NonNull
    @PrimaryKey(autoGenerate = true)
    int mPrimaryKey;

    //must have a question
    @NonNull
    @ColumnInfo
    private String question;

    //must have a genre attached in order to index questions by type
    @NonNull
    @ColumnInfo
    private String mGenre;

    @ColumnInfo
    private String a;

    @ColumnInfo
    private String b;

    @ColumnInfo
    private String c;

    @ColumnInfo
    private String d;

    //on creating a question, you must give 4 answers (a-d) and a genre to index it
    public Question(String question, String genre, String a, String b, String c, String d) {
    this.question = question;
    this.mGenre = genre;
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
    }
    //you don't have to have a D answer. Minimum 3 answers
    @Ignore
    public Question(String question, String genre, String a, String b, String c) {
        this.question = question;
        this.mGenre = genre;
        this.a = a;
        this.b = b;
        this.c = c;
    }


    //getters and setters for all variables
    public String getA() { return a;}

    public String getB() { return b;}

    public String getC() { return c;}

    public String getD() { return d;}
    @NonNull
    public String getMGenre() {return mGenre;}
    @NonNull
    public String getQuestion() {return question;}


    public void setA(String a) {this.a = a;}

    public void setB(String b) {this.b = b;}

    public void setC(String c) {this.c = c;}

    public void setD(String d) {this.d = d;}

    public void setQuestion(@NonNull String question){this.question = question;}

    public void setMGenre(@NonNull String mGenre) {this.mGenre = mGenre;}

}
