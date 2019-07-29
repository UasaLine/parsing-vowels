package parsingVowels;

public class Pare {
   private String before;
   private String value;

    public Pare(String before, String value) {
        this.before = before;
        this.value = value;
    }

    public Pare() {
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
