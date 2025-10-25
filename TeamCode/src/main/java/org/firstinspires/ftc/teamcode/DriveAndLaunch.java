package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="DriveAndLaunch", group="Demo")
public class DriveAndLaunch extends OpMode {

    private DcMotor left, right, launcher;

    private static final double MAX_THROTTLE = 0.6;
    private static final double MAX_TURN = 0.6;

    private static final double FWD_SPEED = 0.30;
    private static final long FWD_MS = 2000;
    private static final double REV_SPEED = 0.95;
    private static final long REV_MS = 5000;

    private boolean canPress = true;

    @Override
    public void init() {
        // Map motors
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");
        launcher = hardwareMap.get(DcMotor.class, "launcher");

        // Set directions
        left.setDirection(DcMotor.Direction.REVERSE);
        right.setDirection(DcMotor.Direction.FORWARD);

        // Brake when stopped
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addLine("Use: Left stick Y = move, Right stick X = turn, A = launch 3 times");
        telemetry.update();
    }

    @Override
    public void loop() {
        // --- Drive Control ---
        double throttle = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;

        if (Math.abs(throttle) < 0.05) throttle = 0.0;
        if (Math.abs(turn) < 0.05) turn = 0.0;

        throttle *= MAX_THROTTLE;
        turn *= MAX_TURN;

        double leftPower = throttle + turn;
        double rightPower = throttle - turn;

        double maxMag = Math.max(1.0, Math.max(Math.abs(leftPower), Math.abs(rightPower)));
        left.setPower(leftPower / maxMag);
        right.setPower(rightPower / maxMag);

        // --- Launcher Control ---
        if (gamepad1.a && canPress) {
            launchThree();
            canPress = false;
        }
        if (!gamepad1.a) {
            canPress = true;
        }

        // --- Telemetry ---
        telemetry.addData("Throttle (LY)", "%.2f", throttle);
        telemetry.addData("Turn (RX)", "%.2f", turn);
        telemetry.addData("Left Power", "%.2f", leftPower);
        telemetry.addData("Right Power", "%.2f", rightPower);
        telemetry.update();
    }

    private void launchThree() {
        for (int i = 0; i < 3; i++) {
            launch();
        }
    }

    private void launch() {
        launcher.setPower(FWD_SPEED);
        sleepMs(FWD_MS);
        launcher.setPower(-REV_SPEED);
        sleepMs(REV_MS);
        launcher.setPower(0);
    }

    private void sleepMs(long time) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < time) {
            telemetry.addData("Sleeping for (ms)", time);
        }
    }
}
