import com.diozero.api.DigitalOutputDevice;
import com.diozero.api.PwmOutputDevice;
import com.diozero.devices.LED;
import com.diozero.devices.PwmLed;
import com.diozero.util.SleepUtil;
import com.sun.xml.internal.ws.api.pipe.Engine;

public class Main {
    public static void main(String[] args) {
        try (PwmOutputDevice forwardEngine = new PwmOutputDevice(27, 50000, 0f); PwmOutputDevice backwardsEngine = new PwmOutputDevice(22, 50000, 0f); PwmOutputDevice leftEngine = new PwmOutputDevice(18, 50000, 0f); PwmOutputDevice rightEngine = new PwmOutputDevice(17, 50000, 0f)) {
            forward(forwardEngine, backwardsEngine, 1f);
            left(leftEngine, rightEngine);
            SleepUtil.sleepSeconds(8);
            forward(forwardEngine, backwardsEngine, 0f);

            for (int i = 0; i < 3; i++) {
                left(leftEngine, rightEngine);
                SleepUtil.sleepSeconds(0.5);
                right(leftEngine, rightEngine);
                SleepUtil.sleepSeconds(0.5);
            }


        }


    }

    private static void forward(PwmOutputDevice forwardEngine, PwmOutputDevice backwardsEngine, float velocity) {
        forwardEngine.setValue(velocity);
        backwardsEngine.setValue(0f);
    }

    private static void backwards(PwmOutputDevice forwardEngine, PwmOutputDevice backwardsEngine, float velocity) {
        forwardEngine.setValue(0f);
        backwardsEngine.setValue(velocity);
    }

    private static void right(PwmOutputDevice leftEngine, PwmOutputDevice rightEngine) {
        leftEngine.setValue(0f);
        rightEngine.setValue(1f);
    }

    private static void left(PwmOutputDevice leftEngine, PwmOutputDevice rightEngine) {
        leftEngine.setValue(1f);
        rightEngine.setValue(0f);
    }


}