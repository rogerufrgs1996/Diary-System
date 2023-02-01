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
    List<String> msg = new ArrayList<>();

    public Day( String title, Date date, Mood mood) {
        this.title = title;
        this.date = date;
        this.mood = mood;
    }
    public void lineOfText(String text) {
        msg.add(text);
    }
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nDATE: "+ sdf.format(date));
        sb.append("\nTITLE: "+ title);
        sb.append("\nMOOD: "+ mood);
        sb.append("\nDAY: ");
        sb.append("\n");
        for (String msg : msg){
            sb.append(msg);
            sb.append("\n");
        }
        sb.append("_______________________________________________________________________");
        return sb.toString();
    }
    

    

}