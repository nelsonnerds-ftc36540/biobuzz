package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.List;

public class LimelightController extends Subsystem {
    public final Servo llPivot;
    public double ballDetectionAngle = 0.275;
    public double hiveDetectionAngle = 0.95;

    private final Limelight3A limelight;

    private enum Quadrant {
        BLUE_AUDIENCE,
        BLUE_SCORING,
        RED_AUDIENCE,
        RED_SCORING
    }

    public LimelightController(RobotContext ctx){
        super(ctx);
        llPivot = hardwareMap.get(Servo.class, "llPivot");
        llPivot.setPosition(0);
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0); // Replace 0 with your AprilTag pipeline index
    }
    public void lookAtHive(){
        llPivot.setPosition(hiveDetectionAngle);
    }

    public void lookAtFloor() {
        llPivot.setPosition(ballDetectionAngle);
    }

    public Boolean isLookingAtHive() {
        return llPivot.getPosition() == hiveDetectionAngle;
    }


    public boolean hasTarget() {
        LLResult result = limelight.getLatestResult();
        if (result == null || !result.isValid()) {
            return false;
        }
        List<LLResultTypes.FiducialResult> fiducials = result.getFiducialResults();
        return fiducials != null && !fiducials.isEmpty();
    }
}
