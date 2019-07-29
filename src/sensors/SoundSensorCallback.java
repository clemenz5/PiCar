package sensors;

public interface SoundSensorCallback {
    /**
     * this returns the measured distance as soon as any results are ready
     *
     * @param distance the distance in cm
     */
    void onMeasureResult(double distance);
}
