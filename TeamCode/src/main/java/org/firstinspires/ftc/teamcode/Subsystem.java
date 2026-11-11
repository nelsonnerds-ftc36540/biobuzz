package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Subsystem {
    protected final HardwareMap hardwareMap;
    protected final Gamepad gamepad1;
    protected final Gamepad gamepad2;
    protected final Telemetry telemetry;

    public Subsystem(RobotContext ctx) {
        this.hardwareMap = ctx.hardwareMap;
        this.gamepad1 = ctx.gamepad1;
        this.gamepad2 = ctx.gamepad2;
        this.telemetry = ctx.telemetry;
    }
}
