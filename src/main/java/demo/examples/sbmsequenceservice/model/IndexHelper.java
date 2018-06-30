package demo.examples.sbmsequenceservice.model;

public class IndexHelper {
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMinLeft() {
        return minLeft;
    }

    public void setMinLeft(int minLeft) {
        this.minLeft = minLeft;
    }

    private int value;
    private int minLeft;

    public IndexHelper(int value, int minLeft) {
        this.value = value;
        this.minLeft = minLeft;
    }
}
