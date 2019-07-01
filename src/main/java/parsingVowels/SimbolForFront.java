package parsingVowels;

public class SimbolForFront {
    int red;
    String Simbol;

    public SimbolForFront(int red, String simbol) {
        this.red = red;
        Simbol = simbol;
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
