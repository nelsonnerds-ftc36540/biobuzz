package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Nerds TeleOp (Pedro)")
public class UberNerdTele extends OpMode {

    private Driver driver;
    private Intake intake;
    private Scoopy scoopy;
    private Outtake outtake;

    @Override
    public void init() {

        RobotContext ctx = new RobotContext(hardwareMap, gamepad1, gamepad2, telemetry);
        driver = new Driver(ctx);
        intake = new Intake(ctx);
        scoopy = new Scoopy(ctx);
        outtake = new Outtake(ctx);
    }

    @Override
    public void start(){
        driver.setInitialPose();

    }

    @Override
    public void loop() {
        driver.drive();
        // intake
        intake.run();
        // shoot
        outtake.run();
        // scoop
        scoopy.run();
    }

}