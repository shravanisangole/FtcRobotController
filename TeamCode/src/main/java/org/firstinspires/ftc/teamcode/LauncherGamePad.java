package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Launcher", group="Spart10_Code")
public class LauncherGamePad extends LinearOpMode {

    private DcMotor launcher;

    private static final double FWD_SPEED = 0.30;
    private static final long   FWD_MS    = 2000;
    private static final double REV_SPEED = 0.95;
    private static final long   REV_MS    = 5000;

    @Override
    public void runOpMode() {

        launcher = hardwareMap.get(DcMotor.class, "launcher");
        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addLine("Ready! Press START to begin.");
        telemetry.update();

        waitForStart();

        // Fire once when you start
        fireOnce();

        telemetry.addLine("Press A to fire 3 times. Press STOP to end.");
        telemetry.update();

        boolean canPress = true;  // only allow one fire per full press

        while (opModeIsActive()) {

            // when A is pressed down, and we are allowed to press
            if (gamepad1.a && canPress) {
                fireThreeTimes();
                canPress = false;  // block until button released
            }

            // once A is released, allow next press
            if (!gamepad1.a) {
                canPress = true;
            }

            idle();
        }
    }

    private void fireOnce() {
        launcher.setPower(FWD_SPEED);
        sleep(FWD_MS);

        launcher.setPower(-REV_SPEED);
        sleep(REV_MS);

        launcher.setPower(0);
    }

    private void fireThreeTimes() {
        fireOnce();
        fireOnce();
        fireOnce();
    }
}