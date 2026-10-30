package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Limelight Test")
public class LimelightTest extends OpMode {
    double ballDetectionAngle = 0.275;
    double hiveDetectionAngle = 0.95;
    private Servo llPivot;

    @Override
    public void init() {
        llPivot = hardwareMap.get(Servo.class, "ll_pivot");
    }

    @Override
    public void loop() {

        double startingPosition = llPivot.getPosition();
        telemetry.addData("Position", startingPosition);
        telemetry.addData("Direction", llPivot.getDirection());

        if (gamepad1.leftBumperWasPressed()) {
            llPivot.setPosition(ballDetectionAngle);
        }
        if (gamepad1.rightBumperWasPressed()) {
            llPivot.setPosition(hiveDetectionAngle);
        }

        updateTelemetry(telemetry);
    }

}
