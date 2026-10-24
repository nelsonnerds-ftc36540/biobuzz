package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class outtakeTest extends OpMode {

    DcMotor outtake;


    @Override
    public void init() {
        outtake = hardwareMap.get(DcMotor.class, "backRight");
        outtake.setPower(.1);
    }

    @Override
    public void loop() {

    }
}
