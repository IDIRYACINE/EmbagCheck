package idir.embag.Utility.NumToStringFormater;
/*
    a unit is three numbers example : 096 , 122
*/


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Label;


public class CheckFormater {
   
    private static String securityBlock = "************************************************************************";
    private static Boolean specialCase = false ;
    private static final String[] CURRENCY = {"DA " , " CTS"};
    private static ArrayList<Integer> CURRENCY_INSERTION_INDEX = new ArrayList<Integer>();
    private static ArrayList<String> principleNums ;
    private static ArrayList<String> secondaryNums ;
    private static int unitCount;


    private static String amountSecurityFormater(String value){
        String pattern = "****";
        return pattern+value+pattern;
    } 

    public static String spaceFormater(String value){
        splitMainAndSecondaryCurrency(value);
        int length = principleNums.size();
        calculateUnitCount(length);
        String result = value;

        if ( length > 3){
            result = "";
            int index = 0 ;
            int CURRENT_UNIT = 0 ;
            int CURRENT_UNIT_INDEX = 0 ;
            int loss = unitCount * 3 - length;

            int limit;
            while(index < length){
                if (CURRENT_UNIT_INDEX>2){return result += value.substring(index);}
                limit = CURRENT_UNIT + 3 - loss ;
                result += value.substring(index,limit) + " ";
                index = limit ;
                CURRENT_UNIT +=3;
                CURRENT_UNIT_INDEX++;
            }
            if (secondaryNums.size()>1){
                result += ".";
                for(int i = 0 ; i < secondaryNums.size();i++){
                    result += secondaryNums.get(i);
                }
            }
        }
        return result;     
    }

    public static String[] numToStringFormat(String value , Label currentLabel ){        
        String[] result = {"",""};
        numParser(secondaryNums,1);
        numParser(principleNums,unitCount);
        mergeMainAndSecondaryCurrency(principleNums , secondaryNums);
        CheckTruncation(principleNums, currentLabel ,result);
    
        reset();
        return result;
    }

    private static void calculateUnitCount(int length) {
        int divisor = length/3 ;
        int mod = length % 3 ;
        if((mod ==0) && (divisor == 0)){mod = 1;}
        if (mod > 1){mod = 1 ;}
        unitCount = ((3 * divisor) + (3 * mod)) / 3;
    }

    private static void splitMainAndSecondaryCurrency(String value) {
        String[] stringNums = value.split("\\.");
        principleNums = numbersFilter(stringNums,0);
        secondaryNums = numbersFilter(stringNums,1);
    }

    private static ArrayList<String> numbersFilter(String[] stringNums , int index ){ 
    
        ArrayList<String> arrayList =  new ArrayList<String>();
        if (index < stringNums.length ){
            Matcher pattern = Pattern.compile("[0-9]").matcher(stringNums[index]);
            while (pattern.find()) {
                arrayList.add(pattern.group());
            }
            return arrayList;
        }
        

        return arrayList;
    }

    private static void mergeMainAndSecondaryCurrency( ArrayList<String> principleNums ,  ArrayList<String> secondaryNums){
        int arrayLength = 0 ;
        arrayLength = principleNums.size();
        CURRENCY_INSERTION_INDEX.add(arrayLength);
        
        if (secondaryNums.size() > 0){
            arrayLength = secondaryNums.size() + principleNums.size() +1;
            System.out.println(secondaryNums);
            if(!secondaryNums.get(0).equals("")){CURRENCY_INSERTION_INDEX.add(arrayLength);}
            principleNums.addAll(secondaryNums);
        }
    }


    private static int[] unitParser(int index , int limit, ArrayList<String> stringNums){
        int length = stringNums.size() < limit ? stringNums.size() : limit;
        int power = 0 ;
        for (int i =  3 - length - index ; i > 0 ; i--){
           power++;
        }
        int num;
        while (index < length ){            
            num = Integer.parseInt(stringNums.get(index));
            specialCase = ((num == 1) || (num == 9) || (num == 7)) && (power == 1);
            stringNums.set(index ,DigitToString(num, power));
            if (specialCase){power = 2 ;}
            index++;
            power++;
        }

        specialCase = false ;
        int[] cache = {index-1 , power -1 };
        return cache;
    }


    private static void numParser(ArrayList<String> stringNums , int unitCount){
        if (stringNums.size()<1){return;}
        int CURRENT_UNIT = 0 ;
        int loss = unitCount * 3 - stringNums.size();
        int limit ; 
        int index = 0 ;
        for (int i = 0 ; i < unitCount ; i++){
            limit = CURRENT_UNIT + 3 - loss ;
            int[] unitParsingCache = unitParser(index, limit, stringNums) ;
            String tempValue = stringNums.get(unitParsingCache[0]);
            stringNums.set(unitParsingCache[0], tempValue + unitModifierSelector(i,unitCount-1));
            CURRENT_UNIT += 3 ;
            index = limit ;
        }
    }

    private static String unitModifierSelector(int current , int length){
        String[] unitEnums = {"",NumbersEnum.THOUSANDS , NumbersEnum.Millions};
        int resultIndex = 0 ;
        int[] LENGTH_THREE_CASE = {2,1,0};
        int[] LENGTH_TWO_CASE = {1,0,2};
        int[][] DECODE_MODES = {{0},LENGTH_TWO_CASE ,LENGTH_THREE_CASE};
        resultIndex = DECODE_MODES[length][current];
        return unitEnums[resultIndex];
    }

    private static String DigitToString(int  num , int power){
        String result = "";
        String[][] NUMS_CATALOG = {NumbersEnum.Hundreds , NumbersEnum.Tens , NumbersEnum.Units,NumbersEnum.TenSpecialCase};
        String[] UNIT_MODIFIER = {NumbersEnum.HUNDRED , " " , " "," "};
        result = NUMS_CATALOG[power][num] ;
        if((result !="")&&(result != "cent ")){
            result +=UNIT_MODIFIER[power];
        }
        return result;
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
    principleNums.clear();
    secondaryNums.clear();
    unitCount = 0 ;
   }
    
}
