package sensors;

import com.diozero.api.*;

public class SoundSensor {
    private DigitalInputDevice echo;
    private DigitalOutputDevice trigger;
    private SoundSensorCallback callback;
    private long startTime = 0;
    private long endTime = 0;

    public SoundSensor(SoundSensorCallback sensorCallback, int triggerPin, int echoPin) {
        callback = sensorCallback;
        trigger = new DigitalOutputDevice(triggerPin, true, true);
        echo = new DigitalInputDevice(echoPin);

        echo.addListener(digitalInputEvent -> {
            if (digitalInputEvent.isActive() && startTime == 0) {
                startTime = System.nanoTime();
                endTime = 0;
            } else if (!digitalInputEvent.isActive() && endTime == 0) {
                endTime = System.nanoTime();
                callback.onMeasureResult(calcDistance(endTime - startTime));
                startTime = 0;
            }

        });
    }

    private double calcDistance(long time) {
        return (330 / (2 * Math.pow(10.0, 7.0)) * (time));
    }

    public void measure() {
        trigger.off();
        trigger.on();
    }

}
