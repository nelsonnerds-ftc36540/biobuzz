package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensor {
    NormalizedColorSensor colorSensor;

    public enum DetectedColor {
        RED,
        YELLOW,
        BLUE,
        UNKNOWN
    }

    public void init(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(NormalizedColorSensor.class, "sensor_color");
        colorSensor.setGain(15);
    }

    public DetectedColor getDetectedColor(Telemetry telemetry) {

        NormalizedRGBA normalizedRGBA = colorSensor.getNormalizedColors();

        float normRed, normBlue, normGreen;
        normRed = normalizedRGBA.red / normalizedRGBA.alpha;
        normBlue = normalizedRGBA.blue / normalizedRGBA.alpha;
        normGreen = normalizedRGBA.green / normalizedRGBA.alpha;

        telemetry.addData("red", normRed);
        telemetry.addData("blue", normBlue);
        telemetry.addData("green", normGreen);

        return DetectedColor.UNKNOWN;
    }
}
