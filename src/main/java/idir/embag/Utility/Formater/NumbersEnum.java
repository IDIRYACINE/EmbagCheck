package idir.embag.Utility.Formater;

public class NumbersEnum {
    public static String HUNDRED = " cent ";
    public static String THOUSANDS = " mille ";
    public static String Millions = " million ";
    public static String[] Modifiers = {HUNDRED , THOUSANDS , Millions} ;
    public static String[]  Units = {
      "",
     "un",
    "deux",
    "trois",
    "quatre",
    "cinq",
    "six",
     "sept",
     "huit",
    "neuf"
  };
  public static String[] Tens = {
         "",
        "dix",
        "vingt",
        "trente",
        "quarante",
        "cinquante",
        "soixante",
        "Soixante-dix",
        "Quatre-vingts",
        "Quatre-vingts",
    };
  public static String[] TenSpecialCase = {
    "dix",
    "onze",
    "deuze",
    "treize",
    "quatorze",
    "quinze",
    "seize",
    "dix-sept",
    "dix-huit",
    "dix-neuf"
  }; 
   
}
