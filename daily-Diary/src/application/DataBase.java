package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entities.Day;
import entities.Person;

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
    

    public static void toFile(Person p){
        String past = "C:\\Users\\roger\\OneDrive\\Documentos\\dataDiary";        
        String path = past+"\\"+p.getName()+".txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
            bw.write(p.toString());
        }
        catch(IOException e){
            System.out.println("ERROR: "+ e.getMessage());
        }
    }

    public static void readDay(Date date, Person person){
        String name = person.getName();
        File file = findFile(name);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))){
            String line = br.readLine();
            String line1;
            line1 ="DATE: "+sdf.format(date);
            int count;
            while(line !=null){
                if(line.equals(line1)){
                    count = 0;
                    while(!line.equals("___")){
                        if(count ==0){
                            System.out.println(ANSI_GREEN + ANSI_BLACK_BACKGROUND + line.substring(0, 5)+ANSI_RESET
                            +ANSI_GREEN+line.substring(6) + ANSI_RESET);
                            count++;
                        }
                        else if(count ==1){
                            System.out.println(ANSI_PURPLE + ANSI_BLACK_BACKGROUND+line.substring(0, 6)+ANSI_RESET
                            +ANSI_PURPLE+line.substring(7) + ANSI_RESET);
                            count++;
                        }
                        else if(count ==2){
                            System.out.println(ANSI_YELLOW + ANSI_BLACK_BACKGROUND + line.substring(0, 5)+ANSI_RESET
                            +ANSI_YELLOW+line.substring(6)+ANSI_RESET);
                            count++;
                        }
                        else if(count ==3){
                            System.out.println(ANSI_BLUE + ANSI_BLACK_BACKGROUND + line+ ANSI_RESET);
                            count++;
                        }
                        else{
                            System.out.println(ANSI_RED + line+ ANSI_RESET);
                        }   
                        line = br.readLine();
                    }
                }
                line = br.readLine();
            }
        }
        catch(IOException e){
            System.out.println("ERROR: "+ e.getMessage());
        } 
    }
    private static File findFile(String name){
        String past = "C:\\Users\\roger\\OneDrive\\Documentos\\dataDiary";
        File diary = new File(past);
        File[] diarys = diary.listFiles(File::isFile);
        for(File d : diarys){
            if(d.getName().equals(name+".txt")){
                return d;
            }
        }
        throw new UIException("FIle dont exists!!");
    }


    public static void updateDiary(Person p, Day day){
        String past = "C:\\Users\\roger\\OneDrive\\Documentos\\dataDiary"; 
        String path = past+"\\"+p.getName()+".txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
            bw.write(day.toString());
        }
        catch(IOException e){
            System.out.println("ERROR: "+ e.getMessage());
        } 
    }
    
    public static Person validateNickName(String name, String readPassword){
        String past = "C:\\Users\\roger\\OneDrive\\Documentos\\dataDiary";
        File diary = new File(past);
        File[] diarys = diary.listFiles(File::isFile);
        for(File d : diarys){
            if(d.getName().equals(name+".txt")){
                String realPassword = extractPassword(d);
                validatePassword(realPassword , readPassword);
                return fileToPerson(name, readPassword);
            }
        }
        throw new UIException("LOGIN DONT EXISTS");
    }
    public static void validateNickName(String name){
        String past = "C:\\Users\\roger\\OneDrive\\Documentos\\dataDiary";
        File diary = new File(past);
        File[] diarys = diary.listFiles(File::isFile);
        boolean check = false;
        for(File d : diarys){
            if(d.getName().equals(name+".txt")){
                check=true;
            }
        }
        if(check){
           throw new UIException("NICKNAME ALREADY EXISTS");
        }
    }
   
    private static String extractPassword(File file){
        
        try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))){
            String line = br.readLine();
            String password;
            boolean check = false;
            while(!check){
                line = br.readLine();
                if(line.equals("PASSWORD")){
                    check =true;
                }
            }
            password = br.readLine();
            return password;
        }
        catch(IOException e){
            System.out.println("error: "+e.getMessage());
        }
        return null;

    }
    public static Person fileToPerson(String name, String password){
        return new Person(name, password);
    }

    private static void validatePassword(String realPassword, String readPassword){
        if(!readPassword.equals(realPassword)){
            throw new UIException("INVALID PASSWORD");
        }
    }
}
