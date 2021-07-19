package idir.embag.Formater;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import idir.embag.Formater.NumbersEnum;



public class CheckFormater {
 
    public static String CURRENCY = "Dinars Algerienne";
    
    public static String amountSecurityFormater(String value){
        String pattern = "***";
        return pattern+value+pattern;
    } 

    public static String amountNumToString(String value){

        return "";
    }

    public static String NumParser(String value){
        String number = value.replace(".", "");
        String[] stringNumbers = number.split("");
        int length = stringNumbers.length ;
        String result = "";
       
        
        if (length < 4){
            result += HunderedParser(stringNumbers );
        }
        else {
            if (length < 7){
                result += ThousandParser(stringNumbers);
            }
            else {
                result += MillionParser(stringNumbers);

            }
        }
        System.out.println(result);

        return result;
    }

    public static String DefaultParser(int index , int limit, String[] stringNumbers){
        int length = stringNumbers.length < limit ? stringNumbers.length : limit;
        String result = "";
        int power = 0 ;
        while (index < length ){
            result += Parse(stringNumbers[index] , power) ;
            index++;
            power++;
        }
        
        return result;
    }
    public static String Parse (String Number , int power ){
        String result = "";
            try{
                int num = Integer.parseInt(Number);
                if(power == 0) {
                    if (num > 0){
                        result += NumbersEnum.Units[num] + NumbersEnum.HUNDRED ;
                    } 
                } 
                else if (power == 1 ){
                    if (num > 1){
                        result +=  NumbersEnum.Tens[num] + "-";
                }
            }
                else {
                    if (num > 1){
                        result +=  NumbersEnum.Units[num] +" ";
                    }
                } 
            }
            catch(Exception e){
                System.out.println(e);
            }
        return result ;
    }

    public static void CleanArray(String[] Array){ 
        for (int i = 0 ; i <Array.length ; i++){
            Array[i] = "";
        }
    }

    public static String[]  CloneArray(String[] sourceArray , int length){ 
        String[] outputArray = new String [length];
        for (int i = 0 ; i <length ; i++){
            outputArray[i] = sourceArray[i];
        }
        return outputArray ;
    }

    public static void RetrieveStringNum(int index , int power){
     

    }

    public static String MillionParser(String[] stringNumbers){
        String tempResult = "";
        tempResult += HunderedParser(stringNumbers) +  NumbersEnum.Millions;
        tempResult +=  DefaultParser(3,6,stringNumbers) +  NumbersEnum.THOUSANDS;
        tempResult += DefaultParser(6,9,stringNumbers);
        if(tempResult != ""){
            return tempResult ;
        }
        return "";
    }
    
    public static String ThousandParser(String[] stringNumbers){
        String tempResult = "";
        tempResult += HunderedParser(stringNumbers) +  NumbersEnum.THOUSANDS;
        tempResult += DefaultParser(3,6,stringNumbers);
        if(tempResult != ""){
            return tempResult  ;
        }
        return tempResult;
    }

    public static String HunderedParser(String[] stringNumbers){
        String tempResult =  DefaultParser(0,3,stringNumbers);
        if(tempResult != ""){
            return tempResult ;
        }
        return "";
    }
   


}
