
import utils.GPIOPinPair;
import com.diozero.util.SleepUtil;
import engine.EngineController;
import sensors.SoundSensor;
import sensors.SoundSensorCallback;

public class Main  {


    public static void main(String[] args) {
        SoundSensorCallback sensorCallback = distance -> System.out.println(distance);

        SoundSensor soundSensor = new SoundSensor(sensorCallback, 27, 17);
        EngineController engineController = new EngineController(new GPIOPinPair(1,2), new GPIOPinPair(1,2), new GPIOPinPair(1,2), new GPIOPinPair(1,2));
        engineController.forward(1);
        while(true){
            soundSensor.measure();
            SleepUtil.sleepMillis(800);
        }


    }

      /*
      Stepper Motor
        try (DigitalOutputDevice in1 = new DigitalOutputDevice(14); DigitalOutputDevice in2 = new DigitalOutputDevice(17); DigitalOutputDevice in3 = new DigitalOutputDevice(27); DigitalOutputDevice in4 = new DigitalOutputDevice(22)) {
            for(int v = 0; v<5; v++){
                for(int i = 0; i<128; i++){

                    in1.setValue(1);
                    in2.setValue(0);
                    in3.setValue(0);
                    in4.setValue(0);

                    SleepUtil.sleepMillis(3);

                    in1.setValue(0);
                    in2.setValue(1);
                    in3.setValue(0);
                    in4.setValue(0);

                    SleepUtil.sleepMillis(3);

                    in1.setValue(0);
                    in2.setValue(0);
                    in3.setValue(1);
                    in4.setValue(0);

                    SleepUtil.sleepMillis(3);

                    in1.setValue(0);
                    in2.setValue(0);
                    in3.setValue(0);
                    in4.setValue(1);

                    SleepUtil.sleepMillis(3);
                }

                for(int i = 0; i<128; i++){
                    in1.setValue(0);
                    in2.setValue(0);
                    in3.setValue(0);
                    in4.setValue(1);

                    SleepUtil.sleepMillis(3);

                    in1.setValue(0);
                    in2.setValue(0);
                    in3.setValue(1);
                    in4.setValue(0);

                    SleepUtil.sleepMillis(3);

                    in1.setValue(0);
                    in2.setValue(1);
                    in3.setValue(0);
                    in4.setValue(0);

                    SleepUtil.sleepMillis(3);

                    in1.setValue(1);
                    in2.setValue(0);
                    in3.setValue(0);
                    in4.setValue(0);

                    SleepUtil.sleepMillis(3);
                }
            }
        }
*/


}