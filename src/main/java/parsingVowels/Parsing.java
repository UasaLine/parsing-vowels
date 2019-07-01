package parsingVowels;

import java.util.ArrayList;
import java.util.List;

public class Parsing {

    public static List<SimbolForFront> parsingVowels(String line){

        line = line.toLowerCase();
        char[] lineArr = line.toCharArray();
        List<SimbolForFront> listResult = new ArrayList<SimbolForFront>();

        for (int i=0;i<lineArr.length;i++){
            Boolean lastSimbol = lastSimbol(i,lineArr.length);
            if (vowels(lineArr[i])){
                String simbol = String.valueOf(lineArr[i]);
                simbol = simbol.toUpperCase();
                listResult.add(new SimbolForFront(1,simbol));
                if (!lastSimbol) {
                    listResult.add(new SimbolForFront(0, "-"));
                }
            }
            else {
                listResult.add(new SimbolForFront(0,String.valueOf(lineArr[i])));
            }

        }

        return listResult;
    }

    public static Boolean vowels(char symbol){

        //А, и, е, е, о, у, ы, э, ю, я
        if ( symbol == "а".charAt(0)){
            return true;
        }
        if ( symbol == "и".charAt(0)){
            return true;
        }
        if ( symbol == "е".charAt(0)){
            return true;
        }
        if ( symbol == "ё".charAt(0)){
            return true;
        }
        if ( symbol == "о".charAt(0)){
            return true;
        }
        if ( symbol == "у".charAt(0)){
            return true;
        }
        if ( symbol == "ы".charAt(0)){
            return true;
        }
        if ( symbol == "э".charAt(0)){
            return true;
        }
        if ( symbol == "ю".charAt(0)){
            return true;
        }
        if ( symbol == "я".charAt(0)){
            return true;
        }
        return false;
    }

    public static Boolean lastSimbol(int i, int length){

        if(i==length-1){
            return true;
        }

        return false;
    }
}
