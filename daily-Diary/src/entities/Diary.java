package entities;

import java.util.ArrayList;
import java.util.List;

public class Diary extends Person{
    private List<Day> days = new ArrayList<>();
    private String path;
    public Diary(String name, String path) {
        super(name);
        this.path = path;
    }
    public String getPath() {
        return path;
    }
    public void addDay(Day day){
        days.add(day);
    }
    @Override
    public String toString() {
        return "Diary []";
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
    public List<Day> getDays() {
        return days;
    }

    

    
}
