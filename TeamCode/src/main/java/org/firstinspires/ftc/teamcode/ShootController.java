package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ShootController {

    Driver driver;
    Outtake outtake;
    LimelightController limelight;
    Telemetry telemetry;

    // TODO: TRANSFER

    private enum ShootState {
        INACTIVE,
        SEARCHING,
        ALIGNING,
        FEED_BALLS,
        COOL_DOWN,
    }

    ShootState state = ShootState.INACTIVE;

    private final ElapsedTime stateTimer = new ElapsedTime();

    private static final double PIVOT_SETTLE_TIME = 0.3;
    public ShootController(Telemetry telemetry, Driver driver, Outtake outtake, LimelightController limelight) {
        this.driver = driver;
        this.outtake = outtake;
        this.limelight = limelight;
        this.telemetry = telemetry;

    }

    public void startShootSequence(){
        if (state == ShootState.INACTIVE) {
            limelight.lookAtHive();
            setState(ShootState.SEARCHING);
        }
    }

    public void update() {
        telemetry.addData("llPivot position", limelight.llPivot.getPosition());
        telemetry.addData("llPivot hiveDetectionAngle", limelight.hiveDetectionAngle);
        telemetry.addData("limelight is looking at hive", limelight.isLookingAtHive());
        telemetry.addData("Current ShootController State", state);
        switch (state) {
            case INACTIVE:
                break;
            case SEARCHING:
                if (stateTimer.seconds() >= PIVOT_SETTLE_TIME) {

                    if (limelight.hasTarget()) {
                        setState(ShootState.ALIGNING);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void setState(ShootState newState) {
        stateTimer.reset();
        state = newState;
    }

}
