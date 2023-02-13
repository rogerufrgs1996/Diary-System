package entities;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.List;
public class EncriptDay {
    private String title;
    private String date;
    private String mood;
    private List<String> msg = new ArrayList<>();
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public EncriptDay(String title, String date, String mood, List<String> msg) {
        this.title = Encript.hide(title);
        this.date = Encript.hide(date);
        this.mood = Encript.hide(mood);
        for(String line : msg){
            this.msg.add(Encript.hide(line));
        }
    }
    public void lineOfText(String text) {
        msg.add(text);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n"+Encript.hide("**"));
        sb.append("\n"+date);
        sb.append("\n"+ title);
        sb.append("\n"+ mood);
        for (String msg : msg){
            sb.append("\n"+msg);
        }
        sb.append("\n");
        sb.append(Encript.hide("__"));
        return sb.toString();
    }
}

