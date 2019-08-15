package com.example.howmuchdoyouknow.database;

public class QuestionData {

    public static Question[] populateQuestionData() {
        return new Question[]{
                new Question("What is your favorite position?", "sex", "Doggy", "cowgirl", "missionary", "pile driver"),
                new Question("What age did you lose your V-card?", "sex", "Never have, lol", "5-10", "10-15", "15-20"),
                new Question("How many people have you slept with?", "sex", "0, I'm a saint", "1-4", "5-10", "10+"),
                new Question("What are you into?", "sex", "BDSM", "Group sex", "Standard package sex", "Drunk sex"),
                new Question("What political party do you most agree with?","politics","republicans","democrats","independents","anarchists"),
                new Question("Which political scandal was your favorite?","politics","xyz-affair","watergate","Iran-contra","bill Clinton Affair"),
                new Question("Which of these presidents did you like the most? ","politics","Obama","George Bush","Bill Clinton","Ronald Regan"),
                new Question("How many times have you voted?","politics","0","1","2-3","3+")
//TODO Add at least 10 quetions for each category. Follow the format for the current questions


        };
    }
}


