package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class outtakeTest extends OpMode {

    DcMotor outtake;
    double speed = 0.1;

    @Override
    public void init() {
        outtake = hardwareMap.get(DcMotor.class, "outtake");
    }

    @Override
    public void start() {
        outtake.setPower(speed);
    }

    @Override
    public void loop() {
        telemetry.addData("Speed", speed);
        if (gamepad1.dpadRightWasPressed() && speed < 1) {
            speed += 0.1;
            outtake.setPower(speed);
        }
        if (gamepad1.dpadLeftWasPressed() && speed > 0) {
            speed -= 0.1;
            outtake.setPower(speed);
        }
        if (gamepad1.a) {
            speed = 0;
        }
    }
}
