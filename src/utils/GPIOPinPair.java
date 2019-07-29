package utils;

public class GPIOPinPair {
    private int pin1;
    private int pin2;

    public GPIOPinPair(int pin1, int pin2) {
        this.pin1 = pin1;
        this.pin2 = pin2;
    }

    public int getPin1() {
        return pin1;
    }

    public void setPin1(int pin1) {
        this.pin1 = pin1;
    }

    public int getPin2() {
        return pin2;
    }

    public void setPin2(int pin2) {
        this.pin2 = pin2;
    }
}
