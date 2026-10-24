package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

public class StoppableTimer {
    private final ElapsedTime internalTimer = new ElapsedTime();
    private double accumulatedTime = 0.0;
    private boolean isRunning = false;

    // Start or resume the timer
    public void start() {
        if (!isRunning) {
            internalTimer.reset(); // Reset the raw clock to now
            isRunning = true;
        }
    }

    // Pause the timer and save elapsed time
    public void pause() {
        if (isRunning) {
            accumulatedTime += internalTimer.time();
            isRunning = false;
        }
    }

    // Reset the timer completely
    public void reset() {
        internalTimer.reset();
        accumulatedTime = 0.0;
        isRunning = false;
    }

    // Get the total elapsed time in seconds
    public double getTime() {
        if (isRunning) {
            return accumulatedTime + internalTimer.time();
        } else {
            return accumulatedTime;
        }
    }
}