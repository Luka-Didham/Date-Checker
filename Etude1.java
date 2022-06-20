import java.util.Scanner; 
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

/**Class which takes in text entered by the user and returns if said text is a valid date */
public class Etude1 {
    
   static final int MINYEAR = 1753;
   static final int MAXYEAR = 3000;
   static String day;
   static String month;
   static String year;
   static int dayInt;
   static int monthInt;
   static int yearInt; 
   static ArrayList<String> months = new ArrayList<String>(); 
   
  /**
  * Main method which takes in the user input then calls methods which etablish the tokens used to split the string, and checks for correct format of dd mm yyyy. 
  * Outputs entered date if date is valid and passes all checks. Else will print out error message and await new date input. 
  */

   public static void main(String[] args){ 
     //Must be in correct order
      months.add("jan");
      months.add("feb");
      months.add("mar");
      months.add("apr");
      months.add("may");
      months.add("jun");
      months.add("jul");
      months.add("aug");
      months.add("sep");
      months.add("oct");
      months.add("nov");
      months.add("dec");
      
      System.out.println("Please input date for validity checking"); 
      Scanner input = new Scanner(System.in);
      String inputDate;
      while(input.hasNextLine()){
         inputDate = input.nextLine();
         if(!inputDate.isBlank()){
            if(splitString(inputDate)){
               if(checkDay()){
                  if(checkYear()){
                     if(checkMonth()){
                        System.out.println(outputSuccess()); 
                     }else{
                        System.out.println(inputDate + " - INVALID: Invalid month");
                     }
                  }else{
                     System.out.println(inputDate + " - INVALID: Invalid year");
                  }
               }else{
                  System.out.println(inputDate + " - INVALID: Invalid day");
               }
            }else{
               System.out.println(inputDate + " - INVALID: Unable to split entered string into day, month and year");
            }
         }else{
            System.out.println(inputDate + " - INVALID: Output was Blank");
         }
      }
      input.close();
   }
   
  /**
  * Check day method which takes in the values passed from the main method. Then checks for valid day/month/year and leapyear conditions. 
  * @param inputDate String which represents day, month, and year before being split. 
  * @return returns a boolean which represents if splitting the string was successful.   
  */
   public static boolean splitString(String inputDate){
   
      String[] tokens = new String[3];  
         
         
      if(inputDate.indexOf('/')>-1){
         if(inputDate.charAt(inputDate.length()-1)!='/'){
            if(inputDate.indexOf(' ')==-1&&inputDate.indexOf('-')==-1){
               tokens = inputDate.split("/");
            }else{
               System.out.println(inputDate + " - INVALID: Multiple token seperators found");
               return false;
            }
         }else{
            System.out.println(inputDate + " - INVALID: Tailing token seperator detected");
            return false;
         }
      }else{
         if(inputDate.indexOf(' ')>-1){
            if(inputDate.charAt(inputDate.length()-1)!=' '){
               if(inputDate.indexOf('/')==-1&&inputDate.indexOf('-')==-1){
                  tokens = inputDate.split(" ");
               }else{
                  System.out.println(inputDate + " - INVALID: Multiple token seperators found");
                  return false;
               }
            }else{
               System.out.println(inputDate + " - INVALID: Tailing token seperator detected");
               return false;
            }
         }else{ 
            if(inputDate.indexOf('-')>-1){
               if(inputDate.charAt(inputDate.length()-1)!='-'){
                  if(inputDate.indexOf(' ')==-1&&inputDate.indexOf('/')==-1){
                     tokens = inputDate.split("-");
                  }else{
                     System.out.println(inputDate + " - INVALID: Multiple token seperators found");
                     return false;
                  }
               }else{
                  System.out.println(inputDate + " - INVALID: Tailing token seperator detected");
                  return false;
               }
            }
         }
      }
         
      if(tokens[1]!= null){
         if(tokens.length>3){
            System.out.println(inputDate + " - INVALID: Too many tokens or token seperators found");
            return false;
         }else{
            if(tokens.length<3){
               System.out.println(inputDate + " - INVALID: Too few tokens or token seperators found"); 
               return false;
            }else{
               day = tokens[0];
               month = tokens[1];
               year = tokens[2];
               return true;  
            }
         }
      }else{
         return false; 
      }
   }
   
  /**
  * Check day method which takes in the values passed from the main method. Then checks for valid day and correct formatting. 
  * @return returns a boolean which represents if day check is successful.   
  */
   public static boolean checkDay(){
      if(!(day.length()>2)){
         try{
            dayInt=Integer.parseInt(day);
            if(dayInt<1||dayInt>31){
               System.out.println(day + " " + month + " " + yearInt + " - INVALID: Day impossible for any month");
               return false; 
            }
         }catch(NumberFormatException nfe){
            System.out.println(day + " " + month + " " + yearInt + " - INVALID: Can not convert day (String) to day (Int)");
            return false; 
         }
      }else{
         System.out.println(day + " " + month + " " + yearInt + " - INVALID: Day token contains too many digits."); 
         return false; 
      } 
      return true; 
   }

  /**
  * Check to see if year is a leap year.  
  * @return returns a boolean which represents if year is a leap year or not.     
  */
   public static boolean checkLeapYear(){
   
      if(yearInt%4==0){
         if(!(yearInt%100==0)){
            return true;
         }else{
            if(yearInt%400==0){
               return true; 
            }
         }
      }
      return false; 
   }

  /**
  * Check month method which checks for valid month, and valid days in month taking into consideration leapyear conditions. 
  * @return returns a string which is if the month check is valid.  
  */
   public static boolean checkMonth(){
      
      Pattern monthNum = Pattern.compile("\\d\\d|\\d"); 
      Matcher matchNum = monthNum.matcher(month);
      Pattern monthWord = Pattern.compile("[a-z][a-z][a-z]|[A-z][a-z][a-z]|[A-Z][A-Z][A-Z]");
      Matcher matchWord = monthWord.matcher(month);
      
      if(matchNum.matches()){
         try{
            monthInt = Integer.parseInt(month);
            if(monthInt>12||monthInt<1){
               System.out.println(day + " " + month +" "+ year + " - INVALID: Month must be within range 1 to 12");
               return false;
            }
         }  
         catch(NumberFormatException nfe){
            System.out.println(day + " " + month +" "+ year + " - INVALID: Cannot convert month in String fromat to int number format.");
            return false;
         }
         
      }else{
         if(matchWord.matches()){
            month = month.toLowerCase(); 
            if(months.contains(month)){
               monthInt = months.indexOf(month)+1;
            }else{
               System.out.println(day + " " + month +" "+ year + " - INVALID: Cannot convert month in String format to int number format.");
               return false; 
            }
         }else{
            System.out.println(day + " " + month +" "+ year + " - INVALID: Cannot convert month in String format to int number format.");
            return false; 
         }
      }
      switch(monthInt){
         case 4: case 6: case 9: case 11:
            if(dayInt>30){
               System.out.println(day + " " + month +" "+ year + " - INVALID: Day does not exsist in given month");
               return false; 
            }
            break;
         case 2: 
            if(dayInt>28){
               if(!checkLeapYear()||dayInt>29){
                  System.out.println(day + " " + month +" "+ year + " - INVALID: Day does not exsist in given month. Leap year: " + checkLeapYear());
                  return false; 
               }
            }
            break;
      }
      return true; 
   }
      
  /**
  * Returns properly formatted string using the arraylist "months". 
  * @return returns a string which is the properly formatted final date. 
  */
   public static String outputSuccess(){
      StringBuilder monthOutput = new StringBuilder(months.get(monthInt-1));
      monthOutput.setCharAt(0, Character.toUpperCase(monthOutput.charAt(0)));
      return String.format("%02d", dayInt) + " " + monthOutput + " " + yearInt;  
   } 
               
  /**
  * Check year method which checks for valid year conditions. 
  * @return returns a boolean which represents if the year passed the year check.  
  */
   public static boolean checkYear(){
      try{
         if(year.length()>1){
            if(year.charAt(0)=='0' && year.length()>2){
               System.out.println(day + " " + month +" "+ year + " - INVALID: Incorrect year format, cannot have more than one leading 0");
               return false; 
            }else{
               yearInt = Integer.parseInt(year);
               if(yearInt<100&&yearInt>49){
                  yearInt+= 1900; 
               }
               if(yearInt<100&&yearInt<=49){
                  yearInt+=2000;
               }
               if(yearInt<MINYEAR||yearInt>MAXYEAR){
                  System.out.println(day + " " + month + " " + yearInt + " - INVALID: Year out of range.");
                  return false; 
               }
            }
         }else{
            System.out.println(day + " " + month +" "+ year + " - INVALID: Year must have atleast two digits");
            return false; 
         }
      }catch(NumberFormatException nfe){
         System.out.println(day + " " + month +" "+ year + " - INVALID: Cannot convert year in String fromat to int number format.");
         return false; 
      }       
      return true;  
      
   }

}
