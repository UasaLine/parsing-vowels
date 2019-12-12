package parsingVowels;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parsing {

    public static List<List<SimbolForFront>> splitTheStringIntoWords(String line) {

        String[] wordArr = line.split(" ");

        List<List<SimbolForFront>> listWithProcessedWord = new ArrayList<>();

        Arrays.stream(wordArr)
                .forEach((word) -> listWithProcessedWord.add(parseCharacters(word)));

        return listWithProcessedWord;
    }

    public static List<List<SimbolForFront>> processingListWords(ArrayList<ArrayList<SimbolForFront>> listWord) {

        List<List<SimbolForFront>> listWithProcessedWord = new ArrayList<>();

        listWord.stream().forEach((word) -> listWithProcessedWord.add(parsingVowels(word)));

        return listWithProcessedWord;
    }

    public static List<SimbolForFront> parsingVowels(List<SimbolForFront> word) {

        List<SimbolForFront> listResult = new ArrayList<SimbolForFront>();
        char lastProcessedSimbol = " ".charAt(0);

        for (SimbolForFront simbol : word) {

            char curSim = simbol.getSimbol().charAt(0);

            if (vowels(curSim)) {

                Pare pare = replaceVowel(curSim, word, word.indexOf(simbol));

                if (pare.getBefore() != "") {
                    listResult.add(new SimbolForFront(0, pare.getBefore(), simbol.isPreposition()));
                }

                listResult.add(new SimbolForFront(1, pare.getValue().toUpperCase(), simbol, simbol.isPreposition()));

                if (!lastVowelsSimbol(word, word.indexOf(simbol))) {
                    listResult.add(new SimbolForFront(0, "-",simbol.isPreposition()));
                }
                lastProcessedSimbol = curSim;
            } else {

                if (lastProcessedSimbol == curSim) {
                    continue;
                }
                String strSimbol = replaceConsonant(curSim, word, word.indexOf(simbol));
                listResult.add(new SimbolForFront(0, strSimbol,simbol.isPreposition()));
                lastProcessedSimbol = curSim;
            }

        }

        return listResult;
    }

    public static List<SimbolForFront> parseCharacters(String line) {

        line = line.toLowerCase();
        boolean isPreposition = thisWordIsAnPreposition(line);
        char[] lineArr = line.toCharArray();
        List<SimbolForFront> listResult = new ArrayList<SimbolForFront>();

        for (int i = 0; i < lineArr.length; i++) {

            char curSim = lineArr[i];

            if (vowels(curSim)) {
                String simbol = String.valueOf(curSim);
                listResult.add(new SimbolForFront(1, simbol, i, curSim, isPreposition));
            } else {
                String simbol = String.valueOf(curSim);
                listResult.add(new SimbolForFront(0, simbol, i, curSim, isPreposition));
            }
        }

        return listResult;
    }

    private static Pare replaceVowel(char charSim, List<SimbolForFront> arr, int i) {


        Pare pare = new Pare();

        if (charSim == "я".charAt(0)) {
            if (checkOnTsya(arr, i)) {
                pare.setValue("а");
                pare.setBefore("");
            } else if (firstInAWord(arr, i) && checkConsonant(arr, i) && beforeSoftSign(arr, i)) {
                pare.setValue("а");
                pare.setBefore("й");
            } else if ((firstInAWord(arr, i) && arr.size()==1)){
                pare.setValue("а");
                pare.setBefore("й");
            } else if (firstInAWord(arr, i) || beforeVowel(arr, i)){
                pare.setValue("а");
                pare.setBefore("й");
            } else {
                pare.setValue("а");
                pare.setBefore("ь");
            }

        } else if (charSim == "ё".charAt(0)) {
            pare.setValue("o");
            if (checkConsonant(arr, i)) {
                pare.setBefore("й");
            } else if (firstInAWord(arr, i) || beforeVowel(arr, i)) {
                pare.setValue("о");
                pare.setBefore("й");
            } else {
                pare.setBefore("ь");
            }
        } else if (charSim == "ю".charAt(0)) {
            pare.setValue("у");
            if (checkConsonant(arr, i)) {
                pare.setBefore("й");
            } else if (firstInAWord(arr, i) || beforeVowel(arr, i)) {
                pare.setValue("у");
                pare.setBefore("й");
            } else {
                pare.setBefore("ь");
            }
        } else if (charSim == "е".charAt(0)) {
            pare.setValue("э");
            if (checkConsonant(arr, i)) {
                pare.setBefore("й");
            } else if (firstInAWord(arr, i) || beforeVowel(arr, i)) {
                pare.setValue("э");
                pare.setBefore("й");
            } else {
                pare.setBefore("ь");
            }
        } else if (charSim == "о".charAt(0)) {
            if (arr.get(i).isAccent() != 1) {
                pare.setValue("а");
                pare.setBefore("");
            } else {
                pare.setValue("о");
                pare.setBefore("");
            }
        } else if (charSim == "и".charAt(0)) {
            if (checkJSH(arr, i)) {
                pare.setValue("ы");
                pare.setBefore("");
            } else {
                pare.setValue(String.valueOf(charSim));
            }
        } else {
            pare.setValue(String.valueOf(charSim));
        }

        return pare;
    }

    private static boolean beforeSoftSign(List<SimbolForFront> arr, int i) {
        if (i > 0 && arr.get(i - 1).getSimbol().equals("ь")) {
            return true;
        }
        return false;
    }

    private static boolean checkOnTsya(List<SimbolForFront> arr, int i) {

        if (i > 1) {
            if ((arr.get(i - 1).getSimbol().equals("с")) && (arr.get(i - 2).equals("т"))) {
                return true;
            }
        }

        if (i > 2) {
            if (arr.get(i - 1).getSimbol().equals("с") &&
                    arr.get(i - 2).getSimbol().equals("ь") &&
                    arr.get(i - 3).getSimbol().equals("т")) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkConsonant(List<SimbolForFront> arr, int i) {

        if (i > 0) {
            if (vowels(arr.get(i - 1).getSimbol().charAt(0))) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkJSH(List<SimbolForFront> arr, int i) {
        if (i > 0) {
            if ((arr.get(i - 1).getSimbol().equals("ц") ||
                    (arr.get(i - 1).getSimbol().equals("ж")) ||
                    (arr.get(i - 1).getSimbol().equals("ш")))) {
                return true;
            }
        }
        return false;
    }

    private static String replaceConsonant(char charSim, List<SimbolForFront> lineArr, int i) {

        boolean lastSimbol = lastSimbol(lineArr, i);

        String simbol = "";
        String addSimbol = "";

        if (charSim == "ц".charAt(0)) {
            simbol = "тс";
            addSimbol = "";
        } else if ((charSim == "г".charAt(0)) && lastSimbol) {
            simbol = "к";
            addSimbol = "";
        } else if ((charSim == "д".charAt(0)) &&
                !lastSimbol &&
                lineArr.get(i + 1).getSimbol().equals("ь") &&
                lastSimbol(lineArr, i + 1)) {
            simbol = "т";
            addSimbol = "";
        } else if (charSim == "ь".charAt(0) && i != 0 &&
                lineArr.get(i - 1).getSimbol().equals("т") &&
                !lastSimbol &&
                lineArr.get(i + 1).getSimbol().equals("с")) {
            simbol = "";
            addSimbol = "";
        } else if (charSim == "з".charAt(0) && lastSimbol) {
            simbol = "с";
            addSimbol = "";
        } else {
            simbol = String.valueOf(charSim);
        }

        return addSimbol + simbol;
    }

    public static Boolean beforeVowel(List<SimbolForFront> arr, int i){
        if (arr.size()>1){
            return vowels(arr.get(i-1).getSimbol().charAt(0));
        }
        return false;
    }

    public static Boolean vowels(char symbol) {

        //А, и, е, е, о, у, ы, э, ю, я
        if (symbol == "а".charAt(0)) {
            return true;
        }
        if (symbol == "и".charAt(0)) {
            return true;
        }
        if (symbol == "е".charAt(0)) {
            return true;
        }
        if (symbol == "ё".charAt(0)) {
            return true;
        }
        if (symbol == "о".charAt(0)) {
            return true;
        }
        if (symbol == "у".charAt(0)) {
            return true;
        }
        if (symbol == "ы".charAt(0)) {
            return true;
        }
        if (symbol == "э".charAt(0)) {
            return true;
        }
        if (symbol == "ю".charAt(0)) {
            return true;
        }
        if (symbol == "я".charAt(0)) {
            return true;
        }
        return false;
    }

    public static Boolean lastSimbol(List<SimbolForFront> arr, int i) {

        int length = arr.size();
        if (i == length - 1) {
            return true;
        }
        return false;

    }

    public static Boolean lastVowelsSimbol(List<SimbolForFront> arr, int i) {

        int length = arr.size();

        boolean lastSimbol = false;
        if (i == length - 1) {
            return true;
        }
        boolean vowels = true;
        for (int j = i + 1; j < length; j++) {
            if (vowels(arr.get(j).getSimbol().charAt(0))) {
                vowels = false;
            }
        }
        return vowels;
    }

    private static boolean firstInAWord(List<SimbolForFront> arr, int i) {
        if (i == 0) {
            return true;
        }
        return false;
    }

    private static boolean thisWordIsAnPreposition(String line) {

        if (line.length() > 5) {
            return false;
        } else if (line.length() == 1) {
            return true;
        } else if ("это".equals(line)) {
            return true;
        } else if ("от".equals(line)) {
            return true;
        } else if ("под".equals(line)) {
            return true;
        } else if ("над".equals(line)) {
            return true;
        } else if ("перед".equals(line)) {
            return true;
        } else if ("до".equals(line)) {
            return true;
        } else if ("после".equals(line)) {
            return true;
        } else if ("около".equals(line)) {
            return true;
        }

        return false;
    }
}
