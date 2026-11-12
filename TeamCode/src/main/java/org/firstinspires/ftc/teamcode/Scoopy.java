package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class Scoopy extends Subsystem {
    private final Servo scoopy;
    private final double scoopyUpPos = 0.875;
    private final double scoopyDownPos = 0.5; // with jiggle
    private boolean scoopyDown = false;
    public Scoopy(RobotContext ctx){
        super(ctx);
        scoopy = hardwareMap.get(Servo.class, "scoopy");
        scoopy.setPosition(scoopyUpPos);
    }
    public void run(){
        if (gamepad1.rightTriggerWasPressed()) {
            if (scoopyDown) {
                scoopy.setPosition(scoopyUpPos);
                scoopyDown = false;
            } else {
                scoopy.setPosition(scoopyDownPos);
                scoopyDown = true;
            }
        }
        telemetry.addData("Servo Position", scoopy.getPosition());
        telemetry.addData("Scoopy Position", scoopyDown ? "Down" : "Up");
    }
}
