package parsingVowels;

public class SimbolForFront {

    private int red;
    private String simbol;
    private char originalSimbol;
    private int position;
    private int strong;
    private int accent;

    public SimbolForFront() {
    }

    public SimbolForFront(int red, String simbol, char originalSimbol, int position, int strong, int accent) {
        this.red = red;
        this.simbol = simbol;
        this.originalSimbol = originalSimbol;
        this.position = position;
        this.strong = strong;
        this.accent = accent;
    }

    public char getOriginalSimbol() {
        return originalSimbol;
    }

    public void setOriginalSimbol(char originalSimbol) {
        this.originalSimbol = originalSimbol;
    }

    public int getAccent() {
        return accent;
    }

    public SimbolForFront(int red, String simbol) {
        this.red = red;
        this.simbol = simbol;
    }

    public SimbolForFront(int red, String simbol,int position,char originalSimbol) {
        this.red = red;
        this.simbol = simbol;
        this.position = position;
        this.originalSimbol = originalSimbol;
    }

    public SimbolForFront(int red, String simbol, char originalSimbol) {
        this.red = red;
        this.simbol = simbol;
        this.originalSimbol = originalSimbol;
    }
    public SimbolForFront(int red, String simbol, SimbolForFront simbolObject) {
        this.red = red;
        this.simbol = simbol;
        this.originalSimbol = simbolObject.originalSimbol;
        this.accent =  simbolObject.accent;
        this.position = simbolObject.position;
        this.strong = simbolObject.accent;
    }

    public int getStrong() {
        return strong;
    }

    public int isAccent() {
        return accent;
    }

    public void setAccent(int accent) {
        this.accent = accent;
    }

    public void setStrong(int strong) {
        this.strong = strong;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public String getSimbol() {
        return simbol;
    }

    public void setSimbol(String simbol) {
        this.simbol = simbol;
    }

    @Override
    public String toString() {
        return  " " + simbol;
    }
}
