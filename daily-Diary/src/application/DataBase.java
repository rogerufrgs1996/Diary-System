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
        boolean check = false;
        try (BufferedReader br = new BufferedReader(new FileReader(file.getPath()))){
            String line = br.readLine();
            String line1;
            while(!check){
                line =br.readLine();
                line1 ="DATE: "+sdf.format(date);
                if(line.equals(line1)){
                    while(line !=null){
                        System.out.println(line);
                        line = br.readLine();
                    }
                    check =true;
                }
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
