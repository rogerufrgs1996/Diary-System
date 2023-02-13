package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Day;
import entities.Diary;
import entities.Encript;
import entities.EncriptDay;
import entities.Person;
import entities.mood.Mood;

public class DataBase {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    

    public static void createFile(Person p){
        String past = "C:\\Users\\roger\\OneDrive\\Documentos\\dataDiary\\";
        String path = past+p.getNickName()+".txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
            bw.write(p.toString());
        }
        catch(IOException e){
            System.out.println("ERROR1: "+ e.getMessage());
        }
    }
    public static void printDay(Date date, Diary diary){
        List<Day> days = diary.getDays();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Day day : days){
            System.out.println();
            if(day.getDate().equals(date)){
                System.out.println(ANSI_GREEN + ANSI_BLACK_BACKGROUND + "DATE: "+ ANSI_RESET
                +ANSI_GREEN+ sdf.format(day.getDate()) + ANSI_RESET);
                System.out.println(ANSI_PURPLE + ANSI_BLACK_BACKGROUND + "TITLE: "+ ANSI_RESET
                + ANSI_PURPLE+ day.getTitle() + ANSI_RESET);
                System.out.println(ANSI_YELLOW + ANSI_BLACK_BACKGROUND + "MOOD: "+ANSI_RESET
                +ANSI_YELLOW+day.getMood()+ANSI_RESET);
                System.out.println(ANSI_BLUE + ANSI_BLACK_BACKGROUND + "DAY: "+ ANSI_RESET);
                for(String line : day.getMsg()){
                    System.out.println(ANSI_RED + line + ANSI_RESET);
                }
            }
        }
    }
    public static void updateDiary(Diary diary, EncriptDay encriptDay){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(diary.getPath(), true))){
            bw.write(encriptDay.toString());
        }
        catch(IOException e){
            System.out.println("ERROR: "+ e.getMessage());
        } 
    }
    public static Diary updateDayInDiary(String path){
        File diary = new File(path);
        return fileToDiary(diary);
    }
    public static boolean nickNameIsUnic(String nickName){
        String past = "C:\\Users\\roger\\OneDrive\\Documentos\\dataDiary";
        File diary = new File(past);
        File[] diarys = diary.listFiles(File::isFile);
        for(File d : diarys){
            if(d.getName().equals(nickName+".txt")){
                throw new UIException("NICKNAME ALREADY EXISTS!!");
            }
        }
        return true;   
    }
    public static Diary validateLogin(String rNickName, String rPassword){
        String past = "C:\\Users\\roger\\OneDrive\\Documentos\\dataDiary";
        File diary = new File(past);
        File[] diarys = diary.listFiles(File::isFile);
        String nickName = Encript.cript(rNickName);
        String password = Encript.cript(rPassword);
        for(File file : diarys){
            if(file.getName().equals(nickName+".txt")){
                if(validatePassword(file, password)){
                    return fileToDiary(file);
                }
            }
        }
        throw new UIException("PASSWORD or NICKNAME INCORRECT");
    }
    private static Diary fileToDiary(File file){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Day> days = new ArrayList<>();
        Date date =null;
        String title ="";
        Mood mood=null;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))){
            String line = br.readLine();
            String showLine ="";
            line = br.readLine();
            line = br.readLine();
            String name = Encript.show(line);
            Diary diary = new Diary(name, file.getPath());
            while(line != null){
                showLine = Encript.show(line);
                int cont =0;
                if(showLine.equals("**")){
                    Day day = new Day();
                    String str = br.readLine();
                    line =  Encript.show(str); 
                    while(!line.equals("__")){
                        switch(cont){
                            case 0:
                                date = sdf.parse(line);
                                cont++;
                                break;
                            case 1:
                                title = line;
                                cont++;
                                break;
                            case 2: 
                                mood = Mood.valueOf(line);
                                cont++;
                                break;
                            default:
                                day.lineOfText(line);
                        }
                        line =  Encript.show(br.readLine());  
                                 
                    }
                    day.receiveDay(title, date, mood);
                    days.add(day);
                    diary.setDays(days);
                }
                line = br.readLine();
            }
            return diary;
        }
        catch(IOException e){
            System.out.println("ERROR: "+ e.getMessage());
        }
        catch(ParseException e){
            System.out.println("ERROR: "+ e.getMessage());
        }
        throw new UIException("something goes wrong");   
    } 
    private static boolean validatePassword(File file, String password){
        try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))){
            String line = br.readLine();
            if(password.equals(line)){
                return true;
            }
            else{
                return false;
            }
        }
        catch(IOException e){
            System.out.println("ERROR2: "+ e.getMessage());
        }
        return false;
    }
}
