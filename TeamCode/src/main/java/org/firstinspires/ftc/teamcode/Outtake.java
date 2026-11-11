package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class Outtake extends Subsystem {

    private final DcMotorEx outtakeMotor;
    private double power = 0;
    public Outtake(RobotContext ctx){
        super(ctx);
        outtakeMotor = ctx.hardwareMap.get(DcMotorEx.class, "outtake");
    }
    public void run(){
        if(gamepad1.dpadRightWasPressed() && power < 1){
            power += .1;
        }
        if(gamepad1.dpadLeftWasPressed() && power > 0){
            power -= .1;
        }
        if(gamepad1.dpad_up){
            power = 0;
        }
        outtakeMotor.setPower(power);
        telemetry.addData("Outtake Speed (Target)", power);
        telemetry.addData("Outtake Speed (Actual)", outtakeMotor.getVelocity());
    }
}
