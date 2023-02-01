package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import entities.Day;
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

    public static Day writeDay(Scanner sc){
        sc.nextLine();
        System.out.print(ANSI_BLACK + ANSI_YELLOW_BACKGROUND+"TITLE : "+ANSI_RESET);
        String title = sc.nextLine();

        System.out.print(ANSI_BLACK + ANSI_YELLOW_BACKGROUND+"MOOD : "+ANSI_RESET);
        String mood = sc.nextLine();

        Day day = new Day(title, new Date() , Mood.valueOf(mood));
        boolean end =false;
        System.out.print(ANSI_BLACK + ANSI_YELLOW_BACKGROUND+"TEXT : \n"+ANSI_RESET);
        System.out.print("lines: \n");
        int lineCount =1;
        while(!end){
            System.out.print(lineCount+": ");
            String strn = sc.nextLine();
            day.lineOfText(strn);
            end = strn.isEmpty();
            lineCount++;
        }
    return day;
    }

    public static void printDay(Scanner sc, Person person) throws ParseException{
        sc.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print(ANSI_BLACK + ANSI_YELLOW_BACKGROUND+"READING DAY ");
        System.out.print("TYPE DATE OF DAY(dd/MM/yyyy): "+ANSI_RESET);
        System.out.println();
        String date = sc.nextLine();
        clearScreen();
        DataBase.readDay(sdf.parse(date), person);
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
        System.out.println("EXIT : (0)");
        System.out.print("TYPE -> "+ANSI_RESET);
        int option = sc.nextInt();
        System.out.println();
    return option;
    }

    public static Person login(Scanner sc){
        sc.nextLine();
        System.out.println(ANSI_WHITE+ANSI_RED_BACKGROUND);
        System.out.print("NAME: ");
        String name = sc.nextLine();
        System.out.print("PASSWORD: "+ANSI_RESET);
        String readPassword = sc.nextLine();
        return DataBase.validateNickName(name, readPassword);
    }

    public static Person createNewDiary(Scanner sc){
        sc.nextLine();
        System.out.println(ANSI_WHITE+ANSI_RED_BACKGROUND);
        System.out.print("NAME: "+ANSI_RESET);
        String name = sc.nextLine();
        DataBase.validateNickName(name);
        System.out.print(ANSI_WHITE+ANSI_RED_BACKGROUND+"PASSWORD: "+ANSI_RESET);
        String password = sc.nextLine();
        return new Person(name, password);
    }
}
