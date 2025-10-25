package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Drive", group="Demo")
public class TwoMotorArcade extends OpMode {
    private DcMotor left, right;

    private static final double MAX_THROTTLE = 0.6;
    private static final double MAX_TURN = 0.6;

    public void init() {
        left = hardwareMap.get(DcMotor.class, "left");
        right = hardwareMap.get(DcMotor.class, "right");

        left.setDirection(DcMotor.Direction.REVERSE);
        right.setDirection(DcMotor.Direction.FORWARD);

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addLine("Use: Left stick Y = forward/backward, Right stick x = turn");
        telemetry.update();
    }
    public void loop() {
        double throttle = -gamepad1.left_stick_y;
        double turn = gamepad1.right_stick_x;

        if(Math.abs(throttle) < 0.05) throttle = 0.0;
        if(Math.abs(turn) < 0.05) turn = 0.0;

        throttle *= MAX_THROTTLE;
        turn *= MAX_TURN;

        double leftPower = throttle + turn;
        double rightPower = throttle - turn;

        double maxMag = Math.max(1.0, Math.max(Math.abs(leftPower), Math.abs(rightPower)));
        leftPower /= maxMag;
        rightPower /= maxMag;

        left.setPower(leftPower);
        right.setPower(rightPower);

        telemetry.addData("throttle (LY)", "%.2f", throttle);
        telemetry.addData("turn (RX)", "%.2f", turn);
        telemetry.addData("leftPower", "%.2f", leftPower);
        telemetry.addData("rightPower", "%.2f", rightPower);
        telemetry.update();
    }
}
