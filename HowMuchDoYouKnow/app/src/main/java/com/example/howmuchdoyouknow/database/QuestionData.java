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
                new Question("What is your best personality trait?","personality","Empathy","Intelligence","Honesty","Other"),
                new Question("What is your most valued personality trait?","personality","Humor","Intelligence","Honesty","Other"),
                new Question("How 'cool' are you on a scale of 1-10?","personality","1-3","4-6","7-9","10"),
                new Question("Do you think drugs should be decriminalized?","ethics","Yes","No","In some places","Not now but in the future"),
                new Question("Do you think the death penalty should be legal?","ethics","Yes, for any murder","Yes, if the judge sees fit","In the most heinous cases","Never"),
                new Question("Do you think its okay to cheat in school?","ethics","Yes","No, but I dont care if others do","In some situations","Never"),
                new Question("How many people have you be in love with?","love","1","2","3","4+"),
                new Question("What age did you first fall in love","love","13-17","18-20","21-24","Never"),
                new Question("What is your love language","love","Gifts","Physical","Words","Other"),
                new Question("What is a good age to die","life&death","60-80","80-100","Live fast, die young","Never"),
                new Question("Which adjective describes a perfect life to you?","life&death","Wealthy","Carefree","Growth","Loving"),
                new Question("Where do you go when you die?","life&death","Heaven or Hell","Reincarnation","Become a part of the universe","A ghost")

//TODO Add at least 10 quetions for each category. Follow the format for the current questions


        };
    }
}


