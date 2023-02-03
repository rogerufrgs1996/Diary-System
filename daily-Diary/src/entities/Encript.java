package entities;

import java.util.ArrayList;
import java.util.List;

public class Encript {
    
    public static String hide(String line){
        if(line !=null){
            char[] c = line.toCharArray();
            String strCript = "";
            String code;
            for (char a : c){
                code = Integer.toString(Integer.hashCode(a));
                strCript += code + ",";
            }
            return strCript;
        }
        else{
            return null;
        }
    }
    public static String show(String line){
        if(line !=null){
            String[] cods = line.split(",");
            List<Integer> codInt = new ArrayList<>();
            String lineDecod = "";
            for (String co : cods){
                int aux = Integer.parseInt(co);
                codInt.add(aux);
            }
            for (int i : codInt){
                int code = i;
                char[] uC = Character.toChars(code);
                lineDecod += Character.toString(uC[0]);
            }
            return lineDecod;

        }
        else{
            return "0";
        }
    }
    public static String cript(String strn){
        if(strn !=null){
            return Integer.toString(strn.hashCode());
        }
        else{
            return null;
        }
    }

}
