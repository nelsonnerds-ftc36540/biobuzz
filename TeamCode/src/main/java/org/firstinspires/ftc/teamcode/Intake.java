package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake extends Subsystem{
    private final DcMotor intake;
    private boolean intaking = false;
    private double intakeSpeed = 0.8;

    public Intake(RobotContext ctx) {
        super(ctx);
        intake = ctx.hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotor.Direction.REVERSE);
    }

    public void run() {
        if (gamepad1.aWasPressed()) {
            if (intaking) {
                intake.setPower(0);
                intaking = false;
            } else {
                intake.setPower(intakeSpeed);
                intaking = true;
            }
        }
        telemetry.addData("Intake Active", intaking);
    }
}

