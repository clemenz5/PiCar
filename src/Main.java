
import utils.GPIOPinPair;
import com.diozero.util.SleepUtil;
import engine.EngineController;
import sensors.SoundSensor;
import sensors.SoundSensorCallback;

public class Main  {


    public static void main(String[] args) {
        SoundSensorCallback sensorCallback = distance -> System.out.println(distance);

        SoundSensor soundSensor = new SoundSensor(sensorCallback, 27, 17);
        //EngineController engineController = new EngineController(new GPIOPinPair(1,2), new GPIOPinPair(1,2), new GPIOPinPair(1,2), new GPIOPinPair(1,2));
        //engineController.forward(1);
        while(true){
            soundSensor.measure();
            SleepUtil.sleepMillis(800);
        }
    }
}