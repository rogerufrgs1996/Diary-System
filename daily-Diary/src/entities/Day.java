package entities;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.mood.Mood;

public class Day{
    private String title;
    private Date date;
    private Mood mood;
    private String moodStrn;
    private List<String> msg = new ArrayList<>();
    
    public List<String> getMsg() {
        return msg;
    }

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Day(String title, Date date, Mood mood,List<String> msg) {
        this.title = title;
        this.date = date;
        this.mood = mood;
        this.msg = msg;
    }
    public Day(String title, Date date, Mood mood, String moodString) {
        this.title = title;
        this.date = date;
        this.mood = mood;
        this.moodStrn = moodString;
    }
    public void lineOfText(String text) {
        msg.add(text);
    }
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Mood getMood() {
        return mood;
    }
    public void setMood(Mood mood) {
        this.mood = mood;
    }
    public EncriptDay dayToEncriptDay(){
        return new EncriptDay(title, sdf.format(date) , moodStrn, msg);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n"+sdf.format(date));
        sb.append("\n"+ title);
        sb.append("\n"+ mood);
        sb.append("\n");
        for (String msg : msg){
            sb.append(msg);
            sb.append("\n");
        }
        return sb.toString();
    }
    

    

}