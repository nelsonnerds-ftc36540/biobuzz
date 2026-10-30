package org.firstinspires.ftc.teamcode;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class plugTest extends OpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor intake;
    DcMotor outtake;
    Servo scoopy;
    Servo llPivot;
    double scoopyUpPos = 0.875;
    double scoopyDownPos = 0.495; // with jiggle
    double ballDetectionAngle = 0.275;
    double hiveDetectionAngle = 0.95;
    boolean scoopyDown = false;
    boolean limelightDown = false;

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        intake = hardwareMap.get(DcMotor.class, "intake");
        scoopy = hardwareMap.get(Servo.class, "scoopy");
        llPivot = hardwareMap.get(Servo.class, "llPivot");
        outtake = hardwareMap.get(DcMotor.class, "outtake");
    }

    @Override
    public void loop() {
        telemetry.addData("Front Left", frontLeft.getPower());
        telemetry.addData("Front Right", frontRight.getPower());
        telemetry.addData("Back Left", backLeft.getPower());
        telemetry.addData("Back Right", backRight.getPower());

        if (gamepad1.dpad_left) {
            frontLeft.setPower(.5);
        } else {
            frontLeft.setPower(0);
        }
        if (gamepad1.dpad_up) {
            frontRight.setPower(.5);
        } else {
            frontRight.setPower(0);
        }
        if (gamepad1.dpad_down) {
            backLeft.setPower(.5);
        } else {
            backLeft.setPower(0);
        }
        if (gamepad1.dpad_right) {
            backRight.setPower(.5);
        } else {
            backRight.setPower(0);
        }
        if (gamepad1.a) {
            intake.setPower(.5);
        } else {
            intake.setPower(0);
        }
        if (gamepad1.y) {
            outtake.setPower(.3);
        } else {
            outtake.setPower(0);
        }
        if (gamepad1.rightTriggerWasPressed()) {
            if (scoopyDown) {
                scoopy.setPosition(scoopyUpPos);
                scoopyDown = false;
            } else {
                scoopy.setPosition(scoopyDownPos);
                scoopyDown = true;
            }
        }
        if (gamepad1.leftTriggerWasPressed()) {
            if (limelightDown) {
                llPivot.setPosition(hiveDetectionAngle);
                limelightDown = false;
            } else {
                llPivot.setPosition(ballDetectionAngle);
                limelightDown = true;
            }
        }
    }
}
