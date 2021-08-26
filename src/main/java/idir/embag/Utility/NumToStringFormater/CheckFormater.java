package idir.embag.Utility.NumToStringFormater;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class CheckFormater {
   
    private static String securityBlock = "************************************************************************";
    private static Boolean specialCase = false ;
    private static final String[] CURRENCY = {"DA " , " STC"};
    private static ArrayList<Integer> CURRENCY_INSERTION_INDEX = new ArrayList<Integer>();


    private static String amountSecurityFormater(String value){
        String pattern = "****";
        return pattern+value+pattern;
    } 

    public static String spaceFormater(String value){
        int length = value.length();
        String result = value;

        if (length == 6){
            result =  value.substring(0,3) + " " +  value.substring(3)  ;
        }
        else if (length > 6){
            result = value.substring(0,3) + " " +  value.substring(3, 6) + " " +  value.substring(6);
        }
        
        return result;     
    }

    public static String[] NumParser(String value , Label currentLabel ){
        value = value.replace(",", ",0");
        String[] stringNums = value.split(",");

        ArrayList<String> principleNums = NumbersFilter(stringNums,0);
        ArrayList<String> secondaryNums = NumbersFilter(stringNums,1);

        int length = principleNums.size() ;
        
        String[] result = {"",""};

        UnitParser(secondaryNums,1);

        if (length < 4){
            UnitParser(principleNums,1);
            mergeArrayList(principleNums , secondaryNums);
            CheckTruncation(principleNums, currentLabel ,result);
        }
        else {
            if (length < 7){
                UnitParser(principleNums,2);
                mergeArrayList(principleNums , secondaryNums);
                CheckTruncation(principleNums, currentLabel ,result);
                                
            }
            else {
                UnitParser(principleNums,3);
                mergeArrayList(principleNums , secondaryNums);
                CheckTruncation(principleNums, currentLabel ,result);
            }
        }
        reset();
        return result;
    }

    private static ArrayList<String> NumbersFilter(String[] stringNums , int index ){ 
    
        ArrayList<String> arrayList =  new ArrayList<String>();
        if (index < stringNums.length ){
            Matcher pattern = Pattern.compile("[0-9]").matcher(stringNums[index]);
            while (pattern.find()) {
                arrayList.add(pattern.group());
            }
            return arrayList;
        }
        arrayList.add("");

        return arrayList;
    }

    private static void mergeArrayList( ArrayList<String> principleNums ,  ArrayList<String> secondaryNums){
        int arrayLength = 0 ;
        arrayLength = principleNums.size();
        CURRENCY_INSERTION_INDEX.add(arrayLength);
       
        if (secondaryNums.size() > 1){
            arrayLength = secondaryNums.size() + principleNums.size() + 1;
            CURRENCY_INSERTION_INDEX.add(arrayLength);
            principleNums.addAll(secondaryNums);
        }
    }


    private static int[] DefaultParser(int index , int limit, ArrayList<String> stringNums){
        int length = stringNums.size() < limit ? stringNums.size() : limit;
        int power = 0 ;
        
        while (index < length ){
            String tempValue = stringNums.get(index);
            if(!tempValue.equals("")){stringNums.set(index ,Parse(tempValue, power));}
            index++;
            power++;
        }
        specialCase = false ;
        int[] cache = {index-1 , power -1 };
        return cache;
    }


    private static void UnitParser(ArrayList<String> stringNums , int unitCount){
        int CURRENT_UNIT = 0 ;
        for (int i = 0 ; i < unitCount ; i++){
            int[] unitParsingCache = DefaultParser(CURRENT_UNIT, CURRENT_UNIT+3, stringNums) ;
            String tempValue = stringNums.get(unitParsingCache[0]);
            stringNums.set(unitParsingCache[0], tempValue + UnitDecoder(i,unitCount));
            CURRENT_UNIT += 3 ;
        }
    }

    private static String UnitDecoder(int current , int length){
        String[] unitEnums = {"",NumbersEnum.THOUSANDS , NumbersEnum.Millions};
        int resultIndex = 0 ;
        int[] LENGTH_THREE_CASE = {2,1,0};
        int[] LENGTH_TWO_CASE = {1,0,2};
        if (length == 2){
            resultIndex = LENGTH_TWO_CASE[current];
        }
        else if (length == 3){
            resultIndex = LENGTH_THREE_CASE[current];
        }

        return unitEnums[resultIndex];
    }

    private static String Parse (String Number , int power ){
        String result = "";
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
                    if ((num == 1) || (num == 9) ){ specialCase = true ;}
                }
                else {
                    if (num > 0 && !specialCase){
                        result +=  NumbersEnum.Units[num] +" ";
                    }
                    else if (specialCase){
                        result += NumbersEnum.TenSpecialCase[num]+" " ;
                    }
                }

        return result ;
    }

    private static void CheckTruncation(ArrayList<String> nums ,Label currentLabel , String[] result ){
        Integer startIndex = 0 ;
        Integer endIndex = 0 ;
        int labelIndex = 0 ;
        addCurrency(nums);
        int limit = nums.size();
        double labelWidth = currentLabel.getWidth();

        String value = stringNumbersManager(nums, startIndex ,endIndex);
        MockText text = new MockText(value);
        
        Boolean overRun = false ;

        while((!overRun) && (endIndex < limit)){
            endIndex++;
            value =  stringNumbersManager(nums, startIndex ,endIndex);
            text.update(value);
            overRun = text.getLayoutBounds().getWidth() > labelWidth ;

            if (overRun) {
                result[labelIndex] = stringNumbersManager(nums, startIndex , endIndex -1);
                labelIndex =  1 ;
                result[labelIndex] = stringNumbersManager(nums,  endIndex -1, limit);
                return ;
            }
        }
        result[labelIndex] = stringNumbersManager(nums,  startIndex , limit);

        return ;
    }



  private static String stringNumbersManager(ArrayList<String> numbers,Integer startIndex ,Integer endIndex ){
        String result = "";

        for(int i = startIndex ; i < endIndex ; i++){
            result += numbers.get(i);
            startIndex++;
        }

        return result;
    }

   private static void addCurrency(ArrayList<String> numbers){
    int index  = 0 ;
    int CURRENCY_COUNT = CURRENCY_INSERTION_INDEX.size() ;
       for (int i = 0 ; i < CURRENCY_COUNT ; i++) {
           index = CURRENCY_INSERTION_INDEX.get(i);
           numbers.add(index, CURRENCY[i]);
       }
   }

   private static void reset(){
    specialCase = false ;
    CURRENCY_INSERTION_INDEX.clear();
   }
    
}
