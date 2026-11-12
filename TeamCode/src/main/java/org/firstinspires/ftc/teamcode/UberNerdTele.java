package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Nerds TeleOp (Pedro)")
public class UberNerdTele extends OpMode {

    private Driver driver;
    private Intake intake;
    private Scoopy scoopy;
    private Outtake outtake;

    private LimelightController limelight;
    private ShootController shootController;

     enum State {
        DRIVE,
        SHOOT
    }

    State state = State.DRIVE;

    @Override
    public void init() {

        RobotContext ctx = new RobotContext(hardwareMap, gamepad1, gamepad2, telemetry);
        driver = new Driver(ctx);
        intake = new Intake(ctx);
        scoopy = new Scoopy(ctx);
        outtake = new Outtake(ctx);
        limelight = new LimelightController(ctx);
        shootController = new ShootController(telemetry, driver, outtake, limelight);
    }

    @Override
    public void start(){
        driver.setInitialPose();

    }

    @Override
    public void loop() {
        telemetry.addData("Current State", state);
        switch (state) {
            case DRIVE:
                driver.drive();
                intake.run();
                outtake.run();
                scoopy.run();
                if (gamepad1.right_stick_button && gamepad1.left_stick_button) {
                    shootController.startShootSequence();
                    state = State.SHOOT;
                }
                break;
            case SHOOT:
                shootController.update();
                break;

        }
        telemetry.update();
    }

}