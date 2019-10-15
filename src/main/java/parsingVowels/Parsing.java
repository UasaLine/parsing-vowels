package parsingVowels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parsing {

    public static List<List<SimbolForFront>> splitTheStringIntoWords(String line){

        String[] wordArr = line.split(" ");

        List<List<SimbolForFront>> listWithProcessedWord = new ArrayList<>();

        Arrays.stream(wordArr)
              .forEach((word)->listWithProcessedWord.add(parseCharacters(word)));

        return listWithProcessedWord;
    }

    public static List<SimbolForFront> parsingVowels(String line,int pos){

        line = line.toLowerCase();
        char[] lineArr = line.toCharArray();
        List<SimbolForFront> listResult = new ArrayList<SimbolForFront>();
        char lastProcessedSimbol = " ".charAt(0);

        for (int i=0;i<lineArr.length;i++){

            if (vowels(lineArr[i])){

                Pare pare = replaceVowel(lineArr,i,pos);

                if(pare.getBefore()!=""){
                    listResult.add(new SimbolForFront(0, pare.getBefore()));
                }

                listResult.add(new SimbolForFront(1,
                                                  pare.getValue().toUpperCase(),
                                                  lineArr[i]));

                if (!lastVowelsSimbol(i,lineArr)) {
                    listResult.add(new SimbolForFront(0, "-"));
                }
                lastProcessedSimbol = lineArr[i];
            }
            else {

                if (lastProcessedSimbol == lineArr[i]){
                      continue;
                }
                String simbol = replaceConsonant(lineArr,i);
                listResult.add(new SimbolForFront(0,simbol));
                lastProcessedSimbol = lineArr[i];
            }

        }

        return listResult;
    }

    public static List<SimbolForFront> parsingVowels(List<SimbolForFront> word){

        List<SimbolForFront> listResult = new ArrayList<SimbolForFront>();
        char lastProcessedSimbol = " ".charAt(0);

        for (SimbolForFront simbol:word){

            char curSim = simbol.getSimbol().charAt(0);

            if (vowels(curSim)){

                Pare pare = replaceVowel(word,word.indexOf(simbol));

                if(pare.getBefore()!=""){
                    listResult.add(new SimbolForFront(0, pare.getBefore()));
                }

                listResult.add(new SimbolForFront(1,
                        pare.getValue().toUpperCase(),
                        lineArr[i]));

                if (!lastVowelsSimbol(i,lineArr)) {
                    listResult.add(new SimbolForFront(0, "-"));
                }
                lastProcessedSimbol = lineArr[i];
            }
            else {

                if (lastProcessedSimbol == lineArr[i]){
                    continue;
                }
                String simbol = replaceConsonant(lineArr,i);
                listResult.add(new SimbolForFront(0,simbol));
                lastProcessedSimbol = lineArr[i];
            }

        }

        return listResult;
    }


    public static List<SimbolForFront> parseCharacters(String line){

        line = line.toLowerCase();
        char[] lineArr = line.toCharArray();
        List<SimbolForFront> listResult = new ArrayList<SimbolForFront>();

        for (int i=0;i<lineArr.length;i++){

            char curSim = lineArr[i];

            if (vowels(curSim)){
                String simbol = String.valueOf(curSim);
                listResult.add(new SimbolForFront(1,simbol,i,curSim));
            }
            else {
                String simbol = String.valueOf(curSim);
                listResult.add(new SimbolForFront(0, simbol,i,curSim));
            }
        }

        return listResult;
    }

    private static Pare replaceVowel(List<SimbolForFront> arr,int i){


        char charSim = arr[i];

        Pare pare = new Pare();


        if(charSim == "я".charAt(0)){
            if(checkOnTsya(arr,i)){
                pare.setValue("а");;
                pare.setBefore("");
            }
            else if (firstInAWord(arr,i)){
                pare.setValue("а");
                pare.setBefore("й");
            }
            else if(checkConsonant(arr,i) && beforeSoftSign(arr,i)){
                pare.setValue("а");
                pare.setBefore("й");
            }
            else {
                pare.setValue("а");
                pare.setBefore("ь");
            }

        }
        else if(charSim == "ё".charAt(0)){
                pare.setValue("o");
                if(checkConsonant(arr,i)){
                    pare.setBefore("й");
                }
                else if (firstInAWord(arr,i)){
                    pare.setValue("о");
                    pare.setBefore("й");
                }
                else {
                    pare.setBefore("ь");
                }
        }
        else if(charSim == "ю".charAt(0)){
            pare.setValue("у");
            if(checkConsonant(arr,i)){
                pare.setBefore("й");
            }
            else if (firstInAWord(arr,i)){
                pare.setValue("у");
                pare.setBefore("й");
            }
            else {
                pare.setBefore("ь");
            }
        }
        else if(charSim == "е".charAt(0)){
            pare.setValue("э");
            if(checkConsonant(arr,i)){
                pare.setBefore("й");
            }
            else if (firstInAWord(arr,i)){
                pare.setValue("э");
                pare.setBefore("й");
            }
            else {
                pare.setBefore("ь");
            }
        }
        else if(charSim == "о".charAt(0)){
            if (position!=i) {
                pare.setValue("а");
                pare.setBefore("");
            }
            else {
                pare.setValue("о");
                pare.setBefore("");
            }
        }
        else if(charSim == "и".charAt(0)){
            if(checkJSH(arr,i)){
                pare.setValue("ы");
                pare.setBefore("");
            }
            else {
                pare.setValue(String.valueOf(charSim));
            }
        }
        else {
            pare.setValue(String.valueOf(charSim));
        }

        return pare;
    }

    private static boolean beforeSoftSign(char[]arr,int i){
        if (i>0 && arr[i-1]=="ь".charAt(0)){
           return true;
        }
        return false;
    }

    private static boolean checkOnTsya(char[]arr,int i){

        if (i>1){
            if ((arr[i-1]=="с".charAt(0))&&(arr[i-2]=="т".charAt(0))){
                return true;
            }
        }

        if (i>2){
            if ((arr[i-1]=="с".charAt(0))&&(arr[i-2]=="ь".charAt(0))&&(arr[i-3]=="т".charAt(0))){
                return true;
            }
        }

        return false;
    }

    private static boolean checkConsonant(char[]arr,int i){

        if(i>0){
            if(vowels(arr[i-1])){
                return true;
            }
        }
        return false;
    }

    private static boolean checkJSH(char[]arr,int i){
        if(i>0){
            if((arr[i-1]=="ц".charAt(0))||(arr[i-1]=="ж".charAt(0))||(arr[i-1]=="ш".charAt(0))){
                return true;
            }
        }
        return false;
    }

    private static String replaceConsonant(char[] lineArr, int i){

        char charSim = lineArr[i];

        boolean lastSimbol = lastSimbol(i,lineArr);

        String simbol="";
        String addSimbol="";

        if(charSim == "ц".charAt(0)){
            simbol = "тс";
            addSimbol="";
        }
        else if((charSim == "г".charAt(0))&& lastSimbol){
            simbol = "к";
            addSimbol="";
        }
        else if((charSim == "д".charAt(0))&& !lastSimbol && lineArr[i+1]=="ь".charAt(0) && lastSimbol(i+1,lineArr)){
            simbol = "т";
            addSimbol="";
        }
        else if(charSim == "ь".charAt(0) && i!=0 && lineArr[i-1]=="т".charAt(0) && !lastSimbol && lineArr[i+1]=="с".charAt(0)){
            simbol = "";
            addSimbol="";
        }
        else {
            simbol = String.valueOf(charSim);
        }

        return addSimbol+simbol;
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

    public static Boolean lastSimbol(int i, char[] arr){

        int length = arr.length;
        if(i==length-1){
            return true;
        }
        return false;

    }

    public static Boolean lastVowelsSimbol(int i, char[] arr){

        int length = arr.length;

        boolean lastSimbol = false;
        if(i==length-1){
            return true;
        }
        boolean vowels = true;
        for (int j=i+1;j<arr.length;j++){
            if (vowels(arr[j])){
                vowels = false;
            }
        }
        return vowels;
    }

    private static boolean firstInAWord(char[]arr,int i){
        if (i==0){
            return true;
        }
        else if(i>0 && " ".equals(arr[i-1])){
            return true;
        }
        return false;
    }

}
