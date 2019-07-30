package engine;

import com.diozero.api.DigitalOutputDevice;
import com.diozero.util.SleepUtil;

/**
 * This class serves as interface to control the Stepper Motor
 * The motor does a full rotation in 2048 Steps
 */
public class StepperMotor {
    private enum State {
        ONE, TWO, THREE, FOUR
    }

    private DigitalOutputDevice in1;
    private DigitalOutputDevice in2;
    private DigitalOutputDevice in3;
    private DigitalOutputDevice in4;
    //this value represents the current degree of the motor
    private double degree = 0.0;
    private final double ANGLE_PER_STEP = 0.17578125;
    private State currentState = State.ONE;

    public StepperMotor(int pin1, int pin2, int pin3, int pin4) {
        in1 = new DigitalOutputDevice(pin1);
        in2 = new DigitalOutputDevice(pin2);
        in3 = new DigitalOutputDevice(pin3);
        in4 = new DigitalOutputDevice(pin4);
    }


    public void turnStepsClockwise(int steps, long gapTime) {
        int startStep;
        switch (currentState) {
            case ONE:
                startStep = 2;
                break;
            case TWO:
                startStep = 3;
                break;
            case THREE:
                startStep = 4;
                break;
            case FOUR:
                startStep = 1;
                break;
            default:
                startStep = 1;
        }
        for (int i = startStep; i <= steps + startStep; i++) {
            step(i % 4);
            degree += ANGLE_PER_STEP;
            System.out.println("Stepper Motor Angle: " + degree);
            SleepUtil.sleepMillis(gapTime);
        }

        switch ((steps + startStep) % 4) {
            case 0:
                break;
            case 1:
                switch (currentState) {
                    case ONE:
                        currentState = State.TWO;
                        break;
                    case TWO:
                        currentState = State.THREE;
                        break;
                    case THREE:
                        currentState = State.FOUR;
                        break;
                    case FOUR:
                        currentState = State.ONE;
                        break;
                    default:

                }
                break;
            case 2:
                switch (currentState) {
                    case ONE:
                        currentState = State.THREE;
                        break;
                    case TWO:
                        currentState = State.FOUR;
                        break;
                    case THREE:
                        currentState = State.ONE;
                        break;
                    case FOUR:
                        currentState = State.TWO;
                        break;
                    default:

                }
                break;
            case 3:
                switch (currentState) {
                    case ONE:
                        currentState = State.FOUR;
                        break;
                    case TWO:
                        currentState = State.ONE;
                        break;
                    case THREE:
                        currentState = State.TWO;
                        break;
                    case FOUR:
                        currentState = State.THREE;
                        break;
                    default:

                }
                break;
            default:

        }
    }

    public void turnStepsCounterclockwise(int steps, long gapTime) {
        int startStep;

        switch (steps % 4) {
            case 0:
                switch (currentState) {
                    case ONE:
                        startStep = 0;
                        break;
                    case TWO:
                        startStep = 1;
                        break;
                    case THREE:
                        startStep = 2;
                        break;
                    case FOUR:
                        startStep = 3;
                        break;
                    default:
                        startStep = 1;
                        break;
                }
                break;
            case 1:
                switch (currentState) {
                    case ONE:
                        startStep = -1;
                        break;
                    case TWO:
                        startStep = 0;
                        break;
                    case THREE:
                        startStep = 1;
                        break;
                    case FOUR:
                        startStep = 2;
                        break;
                    default:
                        startStep = 1;
                        break;
                }
                break;
            case 2:
                switch (currentState) {
                    case ONE:
                        startStep = -2;
                        break;
                    case TWO:
                        startStep = -1;
                        break;
                    case THREE:
                        startStep = 0;
                        break;
                    case FOUR:
                        startStep = 1;
                        break;
                    default:
                        startStep = 1;
                        break;
                }
                break;
            case 3:
                switch (currentState) {
                    case ONE:
                        startStep = -3;
                        break;
                    case TWO:
                        startStep = -2;
                        break;
                    case THREE:
                        startStep = -1;
                        break;
                    case FOUR:
                        startStep = 0;
                        break;
                    default:
                        startStep = 1;
                        break;
                }
                break;
            default:
                startStep = 1;
                break;

        }
        for (int i = steps + startStep; i > startStep; i--) {
            step(i % 4);
            degree -= ANGLE_PER_STEP;
            System.out.println("Stepper Motor Angle: " + degree);
            SleepUtil.sleepMillis(gapTime);
        }

        switch ((steps + startStep) % 4) {
            case 0:
                break;
            case 1:
                switch (currentState) {
                    case ONE:
                        currentState = State.FOUR;
                        break;
                    case TWO:
                        currentState = State.ONE;
                        break;
                    case THREE:
                        currentState = State.TWO;
                        break;
                    case FOUR:
                        currentState = State.THREE;
                        break;
                    default:

                }
                break;
            case 2:
                switch (currentState) {
                    case ONE:
                        currentState = State.THREE;
                        break;
                    case TWO:
                        currentState = State.FOUR;
                        break;
                    case THREE:
                        currentState = State.ONE;
                        break;
                    case FOUR:
                        currentState = State.TWO;
                        break;
                    default:

                }
                break;
            case 3:
                switch (currentState) {
                    case ONE:
                        currentState = State.TWO;
                        break;
                    case TWO:
                        currentState = State.THREE;
                        break;
                    case THREE:
                        currentState = State.FOUR;
                        break;
                    case FOUR:
                        currentState = State.ONE;
                        break;
                    default:

                }
                break;
            default:

        }
    }

    public void setCurrentDegree(double currentDegree) {
        degree = currentDegree;
    }

    /**
     * get the current angle of the motor
     *
     * @return the angle
     */
    public double getDegree() {
        return degree;
    }

    /**
     * Use this method to do one specific step of the motor
     * you need to walk trough the steps 1-4 in correct order and loop them to make it move
     *
     * @param i must be between 1 and 4 to actually do something
     */
    private void step(int i) {
        switch (i) {
            case 1:
                in1.setValue(1);
                in2.setValue(0);
                in3.setValue(0);
                in4.setValue(0);
                break;
            case 2:
                in1.setValue(0);
                in2.setValue(1);
                in3.setValue(0);
                in4.setValue(0);
                break;
            case 3:
                in1.setValue(0);
                in2.setValue(0);
                in3.setValue(1);
                in4.setValue(0);
                break;
            case 4:
                in1.setValue(0);
                in2.setValue(0);
                in3.setValue(0);
                in4.setValue(1);
                break;
            default:
                in1.setValue(0);
                in2.setValue(0);
                in3.setValue(0);
                in4.setValue(0);
                break;
        }
    }
}
