package Validation;

public class ValidationFunction {
    public static boolean isStringName(String name){
        if(name.matches("^[A-Z a-z]+$")){
            return true;
        }
        return false;
    }
    public static boolean isNumber(String number){
        if(number.matches("^[0-9]+\\.[0-9]+$")){
            return true;
        }
        return false;
    }
}
