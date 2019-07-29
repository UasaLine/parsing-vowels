package parsingVowels;

public class SimbolForFront {
    private int red;
    private String Simbol;
    private int position;
    private int strong;

    public SimbolForFront(int red, String simbol) {
        this.red = red;
        Simbol = simbol;
    }

    public SimbolForFront(int red, String simbol,int position) {
        this.red = red;
        Simbol = simbol;
        this.position = position;
    }

    public SimbolForFront(int red, String simbol, int position, int strong) {
        this.red = red;
        Simbol = simbol;
        this.position = position;
        this.strong = strong;
    }

    public int getStrong() {
        return strong;
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
        return Simbol;
    }

    public void setSimbol(String simbol) {
        Simbol = simbol;
    }

    @Override
    public String toString() {
        return  " " + Simbol;
    }
}
