package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
//        return null;
    }

    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("frontLeft");
        c.frontRightName.set("frontRight");
        c.backLeftName.set("backLeft");
        c.backRightName.set("backRight");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backRightDirection.set(DcMotorSimple.Direction.REVERSE);
    });

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(2.3643990764467735);
        c.yPodOffset.set(5.516675213190515);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    // BEGIN
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
//                Controller primaryTranslationalForward = Controller.proportional(0.16993662121624764);
//                Controller secondaryTranslationalForward = Controller.proportional(0.06278704074925567);
                Controller primaryTranslationalForward = Controller.proportional(0.1);
                Controller secondaryTranslationalForward = Controller.proportional(0.025);
//                Controller primaryTranslationalLateral = Controller.proportional(0.22125848685860652);
//                Controller secondaryTranslationalLateral = Controller.proportional(0.0817490987586009);
                Controller primaryTranslationalLateral = Controller.proportional(.1);
                Controller secondaryTranslationalLateral = Controller.proportional(0.025);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.019033926642012974));
//                c.brake.set(Controller.proportionalFeedforward(0.016178837645711028));
                c.brake.set(Controller.proportionalFeedforward(0.01));

                c.headingFeedback.set(Controller.proportional(7.560037545403177));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.04067790203873278, 0.006475864287415708));

                c.linearBrakeCoefficients.set(Matrix.diag(0.05209168821504224, 0.05750705461163629));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0011327648989995524, 9.56549209193593E-4));

                c.maxAchievableForwardVelocity.set(55.32723063854007);
                c.maxAchievableStrafeVelocity.set(46.18222743180271);
                c.naturalForwardDeceleration.set(36.548583332289034);
                c.naturalStrafeDeceleration.set(61.24369196680778);
            }
    );
    //END
}