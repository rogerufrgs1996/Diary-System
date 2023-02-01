package application;


import java.text.ParseException;
import java.util.Scanner;

import entities.Day;
import entities.Person;

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
                        Person p = UI.createNewDiary(sc);
                        DataBase.toFile(p);
                        break;
                    case 2:
                        Person cPerson = UI.login(sc);
                        do{
                            UI.clearScreen();
                            optionLogin = UI.menuLogin(sc, cPerson);
                            switch(optionLogin){
                                case 1: 
                                    Day day = UI.writeDay(sc);
                                    DataBase.updateDiary(cPerson, day);
                                    break;
                                case 2:
                                    UI.printDay(sc, cPerson);
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
