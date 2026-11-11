package org.firstinspires.ftc.teamcode;

import com.pedropathing.api.Paths;
import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import com.pedropathing.follower.Follower;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous
public class UberNerdAuto extends OpMode {
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();


    //START
    private final Pose start = poseFactory.of(56, 8.5, 270);
    // FACING FLOWER
    private final Pose facingRedScoringSideFlower = poseFactory.of(23, 48, 180);
    // UNDER FLOWER
    private final Pose underRedScoringSideFlower = poseFactory.of(20, 48, 180);

    // SHOOTING BACK BLUE
    private final Pose shootingRedAudienceSideHive = poseFactory.of(56, 123, 90);
    private final Pose point4Control1 = poseFactory.of(13.6221, 82.4561, 0);
    public Path startToRedScoringSideFlower() {
        return Paths.line(start, facingRedScoringSideFlower).linear(start, facingRedScoringSideFlower);
    }

    public Path facingToUnderRedScoringSideFlower() {
        return Paths.line(facingRedScoringSideFlower, underRedScoringSideFlower).linear(facingRedScoringSideFlower, underRedScoringSideFlower);
    }

    public Path underToFacingRedScoringSideFlower() {
        return Paths.line(underRedScoringSideFlower, facingRedScoringSideFlower).linear(underRedScoringSideFlower, facingRedScoringSideFlower);
    }

    public Path facingRedScoringSideFlowerToShootingRedAudienceSideHive() {
        return Paths.curve(facingRedScoringSideFlower, point4Control1, shootingRedAudienceSideHive).linear(facingRedScoringSideFlower, shootingRedAudienceSideHive);
    }

    public Command autoRoutine() {
        return sequential(
                follow(follower, startToRedScoringSideFlower()),
                follow(follower, facingToUnderRedScoringSideFlower()),
                follow(follower, underToFacingRedScoringSideFlower()),
                follow(follower, facingRedScoringSideFlowerToShootingRedAudienceSideHive())
        );
    }

    @Override
    public void init() {

        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();

    }

    @Override
    public void start() {
        schedule(autoRoutine());
    }

    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();

        telemetry.addData("X", follower.pose().x());
        telemetry.addData("Y", follower.pose().y());
        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.update();
    }

    @Override
    public void stop() {
        OpModeStorage.autonomousEndPose = follower.pose(); //saves your position in that file
    }
}
