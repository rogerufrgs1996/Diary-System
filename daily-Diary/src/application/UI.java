package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import entities.Day;
import entities.Diary;
import entities.Encript;
import entities.Person;
import entities.mood.Mood;

public class UI {

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

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static Diary writeDay(Scanner sc, Diary diary){
        sc.nextLine();
        System.out.print(ANSI_BLACK + ANSI_YELLOW_BACKGROUND+"TITLE : "+ANSI_RESET);
        String title = sc.nextLine();
        System.out.print(ANSI_BLACK + ANSI_YELLOW_BACKGROUND+"MOOD : "+ANSI_RESET);
        String mood = sc.nextLine();
        Day day = new Day(title, new Date() , Mood.valueOf(mood), mood);
        boolean end = false;
        System.out.print(ANSI_BLACK + ANSI_YELLOW_BACKGROUND+"TEXT : \n"+ANSI_RESET);
        System.out.print("lines: \n");
        int lineCount =1;
        while(!end){
            System.out.print(lineCount+": ");
            String strn1 = sc.nextLine();
            end = strn1.isEmpty();
            if(!end){
               day.lineOfText(strn1);
               lineCount++;
            }
        }
        DataBase.updateDiary(diary, day.dayToEncriptDay());
        return DataBase.updateDayInDiary(diary.getPath());
    }
    public static void readDay(Scanner sc, Diary diary) throws ParseException{
        sc.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print(ANSI_BLACK + ANSI_YELLOW_BACKGROUND+"READING DAY ");
        System.out.print("TYPE DATE OF DAY(dd/MM/yyyy): "+ANSI_RESET);
        System.out.println();
        String date = sc.nextLine();
        clearScreen();
        DataBase.printDay(sdf.parse(date), diary);
        System.out.println();
        System.out.println(ANSI_BLACK + ANSI_YELLOW_BACKGROUND+"TYPE TO BACK TO MENU  "+ANSI_RESET);
        sc.next();
    }

    public static void readAllDiary(Scanner sc, Diary diary){
        DataBase.printDay(diary);
        System.out.println();
        System.out.println(ANSI_BLACK + ANSI_YELLOW_BACKGROUND+"TYPE TO BACK TO MENU  "+ANSI_RESET);
        sc.next();
    }



    public static int menu(Scanner sc){
        System.out.println(ANSI_BLACK + ANSI_YELLOW_BACKGROUND+"CREATE NEW DIARY : (1)");
        System.out.println("LOGIN : (2)");
        System.out.println("EXIT : (0)");
        System.out.print("TYPE :"+ANSI_RESET);
        int option = sc.nextInt();
        System.out.println();
        
    return option;
    }
    public static int menuLogin(Scanner sc, Person cPerson){
        System.out.println(ANSI_WHITE+ANSI_RED_BACKGROUND+"Welcome to the diary of "+ cPerson.getName()+ANSI_RESET);
        System.out.println(ANSI_BLACK + ANSI_YELLOW_BACKGROUND+"WRITE ABOUT YOUR DAY : (1)");
        System.out.println("READ DAY(2): ");
        System.out.println("READ ALL DIARY(3): ");
        System.out.println("EXIT : (0)");
        System.out.print("TYPE -> "+ANSI_RESET);
        int option = sc.nextInt();
        System.out.println();
    return option;
    }
    public static Diary login(Scanner sc){
        sc.nextLine();
        System.out.println(ANSI_WHITE+ANSI_RED_BACKGROUND);
        System.out.print("NICKNAME: ");
        String rNickName = sc.nextLine();
        System.out.print("PASSWORD: "+ANSI_RESET);
        String rPassword = sc.nextLine();
        return DataBase.validateLogin(rNickName, rPassword);
    }
    public static void firstSign(Scanner sc){
        sc.nextLine();
        System.out.println(ANSI_WHITE+ANSI_RED_BACKGROUND);
        System.out.print("Nickname: "+ANSI_RESET);
        String nickName = sc.nextLine();
        try{
            DataBase.nickNameIsUnic(Encript.cript(nickName));
            System.out.print(ANSI_WHITE+ANSI_RED_BACKGROUND+"NAME: "+ANSI_RESET);
            String name = sc.nextLine();
            System.out.print(ANSI_WHITE+ANSI_RED_BACKGROUND+"PASSWORD: "+ANSI_RESET);
            String password = sc.nextLine();
            DataBase.createFile(new Person(Encript.hide(name), Encript.cript(password), Encript.cript(nickName)));
        } catch (UIException e) {
            System.err.println(e.getMessage());
            System.out.println(ANSI_BLACK + ANSI_YELLOW_BACKGROUND+"TYPE TO BACK TO MENU  "+ANSI_RESET);
            sc.next();
        }
        
    }
}
