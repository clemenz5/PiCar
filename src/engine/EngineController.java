package engine;

import utils.GPIOPinPair;
import utils.Strings;

/**
 * This class is designed to be an Interface to the engines
 */
public class EngineController {
    private Engine frontLeftEngine;
    private Engine frontRightEngine;
    private Engine backLeftEngine;
    private Engine backRightEngine;

    /**
     * Pass the pins for the Engines. If the Engines are not working correctly e.g. spinning in the wrong direction,
     * swap the pins in the Tupel
     *
     * @param frontLeft  the pins controlling the front Left engine
     * @param frontRight the pins controlling the front Right engine
     * @param backLeft   the pins controlling the back Left engine
     * @param backRight  the pins controlling the back Right engine
     */
    public EngineController(GPIOPinPair frontLeft, GPIOPinPair frontRight, GPIOPinPair backLeft, GPIOPinPair backRight) {
        frontLeftEngine = new Engine(frontLeft.getPin1(), frontLeft.getPin2(), 0);
        frontRightEngine = new Engine(frontRight.getPin1(), frontRight.getPin2(), 0);
        backLeftEngine = new Engine(backLeft.getPin1(), backLeft.getPin2(), 0);
        backRightEngine = new Engine(backRight.getPin1(), backRight.getPin2(), 0);
    }

    /**
     * This method is designed to let the car go forward
     *
     * @param velocity the speed it shall go with
     */
    public void forward(float velocity) {
        if (velocity < 0 || velocity > 1) {
            throw new IllegalArgumentException(Strings.VELOCITY_RANGE);
        }
        frontLeftEngine.forward(velocity);
        frontRightEngine.forward(velocity);
        backLeftEngine.forward(velocity);
        backRightEngine.forward(velocity);
    }

    /**
     * This method is designed to let the car go backward
     *
     * @param velocity the speed it shall go with
     */
    public void backward(float velocity) {
        if (velocity < 0 || velocity > 1) {
            throw new IllegalArgumentException(Strings.VELOCITY_RANGE);
        }
        frontLeftEngine.backward(velocity);
        frontRightEngine.backward(velocity);
        backLeftEngine.backward(velocity);
        backRightEngine.backward(velocity);
    }

    /**
     * This Method is designed to let the car turn (will cancel any other directional movement)
     *
     * @param velocity the speed
     */
    public void turnLeft(float velocity) {
        if (velocity < 0 || velocity > 1) {
            throw new IllegalArgumentException(Strings.VELOCITY_RANGE);
        }
        frontLeftEngine.backward(velocity);
        backLeftEngine.backward(velocity);
        frontRightEngine.forward(velocity);
        backRightEngine.forward(velocity);
    }

    /**
     * This Method is designed to let the car turn (will cancel any other directional movement)
     *
     * @param velocity the speed
     */
    public void turnRight(float velocity) {
        if (velocity < 0 || velocity > 1) {
            throw new IllegalArgumentException(Strings.VELOCITY_RANGE);
        }
        frontRightEngine.backward(velocity);
        backRightEngine.backward(velocity);
        frontLeftEngine.forward(velocity);
        backLeftEngine.forward(velocity);
    }
}
