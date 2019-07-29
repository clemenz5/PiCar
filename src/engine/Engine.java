package engine;

import utils.Strings;
import com.diozero.api.PwmOutputDevice;

class Engine {
    private int pin1;
    private int pin2;
    private PwmOutputDevice engine1;
    private PwmOutputDevice engine2;
    Engine(int pin1, int pin2, float velocity) {
        this.pin1 = pin1;
        this.pin2 = pin2;


        engine1 = new PwmOutputDevice(pin1, 50000, 0);
        engine2 = new PwmOutputDevice(pin2, 50000, 0);
        if(velocity<=0 && velocity >= -1){
            backward(Math.abs(velocity));
        }else if(velocity>0 && velocity<=1){
            forward(velocity);
        }else{
            throw new IllegalArgumentException("velocity must be between -1 and 1");
        }


    }

    void forward(float velocity){
        if(velocity<0 || velocity>1){
            throw new IllegalArgumentException(Strings.VELOCITY_RANGE);
        }
        engine2.setValue(0);
        engine1.setValue(velocity);
    }

    void backward(float velocity){
        if(velocity<0 || velocity>1){
            throw new IllegalArgumentException(Strings.VELOCITY_RANGE);
        }
        engine1.setValue(0);
        engine2.setValue(velocity);
    }

    void stop(){
        engine1.setValue(0);
        engine2.setValue(0);
    }

    int getPin1() {
        return pin1;
    }

    void setPin1(int pin1) {
        this.pin1 = pin1;
    }

    int getPin2() {
        return pin2;
    }

    void setPin2(int pin2) {
        this.pin2 = pin2;
    }
}
