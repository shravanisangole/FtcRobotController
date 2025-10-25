package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Basic: Linear OpMode", group="Linear OpMode")
//@Disabled
public class Code extends LinearOpMode {

    // Declare OpMode members for each of the 4 motors.


    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor right_motor = null;

    private DcMotor left_motor = null;

    private DcMotor right_motor_ro = null;

    private DcMotor left_motor_ro = null;

    private DcMotor launcher_forward = null;

    private DcMotor launcher_backward = null;


    @Override
    public void runOpMode() {

        right_motor = hardwareMap.get(DcMotor.class, "right");
        left_motor = hardwareMap.get(DcMotor.class, "left");

        right_motor_ro = hardwareMap.get(DcMotor.class, "right");
        left_motor_ro = hardwareMap.get(DcMotor.class, "left");

        launcher_forward = hardwareMap.get(DcMotor.class, "launcher");
        launcher_backward = hardwareMap.get(DcMotor.class, "launcher");

        right_motor.setDirection(DcMotor.Direction.FORWARD);
        left_motor.setDirection(DcMotor.Direction.REVERSE);

        right_motor_ro.setDirection(DcMotor.Direction.FORWARD);
        left_motor_ro.setDirection(DcMotor.Direction.REVERSE);

        launcher_forward.setDirection(DcMotor.Direction.REVERSE);
        launcher_backward.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double forward_backward = -gamepad1.left_stick_y;

            double right = -gamepad1.right_stick_x;

            double left = gamepad1.right_stick_x;

            right_motor.setPower(forward_backward);
            left_motor.setPower(forward_backward);
            right_motor_ro.setPower(right);
            left_motor_ro.setPower(left);

            if (gamepad1.a) {
                launcher_forward.setPower(.5);
            } else if (gamepad1.b) {
                launcher_backward.setPower(-.7);
            } else {
                launcher_forward.setPower(0);
                launcher_backward.setPower(0);
            }

        }

    }
}