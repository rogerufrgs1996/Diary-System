package application;


import java.text.ParseException;
import java.util.Scanner;
import entities.Diary;


public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option =1;
        int optionLogin;
        do{
            
            try{
                UI.clearScreen();
                option = UI.menu(sc);  
                switch(option){
                    case 1:
                        UI.firstSign(sc);
                        break;
                    case 2:
                        Diary diary = UI.login(sc);
                        do{
                            UI.clearScreen();
                            optionLogin = UI.menuLogin(sc, diary);
                            switch(optionLogin){
                                case 1: 
                                    diary = UI.writeDay(sc, diary);
                                    break;
                                case 2:
                                    UI.readDay(sc, diary);
                                    break;
                                case 3: 
                                    UI.readAllDiary(sc, diary);
                                    break;
                            }
                        }while(optionLogin!=0);
                        break;           
                }
            }
            catch(UIException e){
                System.out.println(e.getMessage());
            }
            catch(ParseException e){
                System.out.println(e.getMessage());
            }
            catch(RuntimeException e){
                System.out.println(e.getMessage());
            }
        }while(option!=0);

    }
}
