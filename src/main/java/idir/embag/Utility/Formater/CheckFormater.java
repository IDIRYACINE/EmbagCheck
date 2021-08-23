package idir.embag.Utility.Formater;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import idir.embag.Utility.Formater.NumbersEnum;



public class CheckFormater {
    private static String currencyString = "DA"; 
    private static String securityBlock = "************************************************************************";
    private static Boolean specialCase = false ;

    private static String amountSecurityFormater(String value){
        String pattern = "****";
        return pattern+value+pattern;
    } 

    public static String[] NumParser(String value){
        String number = value.replace(".", "");
        String[] stringNumbers = number.split("");
        int length = stringNumbers.length ;
        String[] result = {"",""};
        String[] tempResult = {"","",""};

        if (length < 4){
            result[0] = HunderedParser(stringNumbers );
            result[0] += currencyString;
            result[1] = securityBlock;
        }
        else {
            if (length < 7){
                tempResult = ThousandParser(stringNumbers);
                result[0] = amountSecurityFormater(tempResult[0]);
                result[1] = tempResult[1];
                result[1] += currencyString;
                result[1] = amountSecurityFormater(result[1]);
            }
            else {
                tempResult = MillionParser(stringNumbers);
                result[0] = amountSecurityFormater(tempResult[0]);

                result[1] = tempResult[1];
                result[1] += tempResult[2];
                result[1] += currencyString;
                result[1] = amountSecurityFormater(result[1]);

            }
        }
        
        return result;
    }

    private static String DefaultParser(int index , int limit, String[] stringNumbers){
        int length = stringNumbers.length < limit ? stringNumbers.length : limit;
        String result = "";
        int power = 0 ;
        while (index < length ){
            result += Parse(stringNumbers[index] , power) ;
            index++;
            power++;
        }
        specialCase = false ;
        return result;
    }

    private static String Parse (String Number , int power ){
        String result = "";
            try{
                int num = Integer.parseInt(Number);
                if(power == 0) {
                    if (num > 0){
                        result += NumbersEnum.Units[num] + NumbersEnum.HUNDRED ;
                    } 
                } 
                else if (power == 1 ){
                    if (num > 1 ){
                        result +=  NumbersEnum.Tens[num] + "-";
                    }
                    else if (num == 1 || num == 9 ){ specialCase = true ;}
                }
                else {
                    if (num > 0 && !specialCase){
                        result +=  NumbersEnum.Units[num] +" ";
                    }
                    else if (specialCase){
                        result += NumbersEnum.TenSpecialCase[num]+" " ;
                    }
                } 
            }
            catch(Exception e){
                
            }
        return result ;
    }

    private static void CleanArray(String[] Array){ 
        for (int i = 0 ; i <Array.length ; i++){
            Array[i] = "";
        }
    }

    private static String[]  CloneArray(String[] sourceArray , int length){ 
        String[] outputArray = new String [length];
        for (int i = 0 ; i <length ; i++){
            outputArray[i] = sourceArray[i];
        }
        return outputArray ;
    }

    private static String[] MillionParser(String[] stringNumbers){
        String tempResult[] = {"","",""};
        tempResult[0]= HunderedParser(stringNumbers) +  NumbersEnum.Millions;
        tempResult[1]=  DefaultParser(3,6,stringNumbers) +  NumbersEnum.THOUSANDS;
        tempResult[2]= DefaultParser(6,9,stringNumbers);
        return tempResult;
    }
    
    private static String[] ThousandParser(String[] stringNumbers){
        String tempResult[] = {"",""};
        tempResult[0]= HunderedParser(stringNumbers) +  NumbersEnum.THOUSANDS;
        tempResult[1]= DefaultParser(3,6,stringNumbers);
        return tempResult;
    }

    private static String HunderedParser(String[] stringNumbers){
        String tempResult =  DefaultParser(0,3,stringNumbers);
        if(tempResult != ""){
            return tempResult ;
        }
        return "";
    }
   


}
