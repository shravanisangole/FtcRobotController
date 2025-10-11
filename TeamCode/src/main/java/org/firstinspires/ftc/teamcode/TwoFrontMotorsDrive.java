package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="TwoFrontMotorsDrive", group="Tutorial")
public class TwoFrontMotorsDrive extends OpMode {

    // Declare motor objects
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;

    @Override
    public void init() {
        // Map hardware names (must match those set in the FTC Driver Hub config)
        leftFront = hardwareMap.get(DcMotor.class, "left");
        rightFront = hardwareMap.get(DcMotor.class, "right");

        // Reverse one motor so both move forward together
        rightFront.setDirection(DcMotor.Direction.REVERSE);

        // Stop motors when power is zero
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void loop() {
        // Get joystick value (negative because pushing up returns -1)
        double drive = -gamepad1.left_stick_y;

        // Set power to both motors
        leftFront.setPower(drive);
        rightFront.setPower(drive);

        // Show current power for debugging
        telemetry.addData("Drive Power", drive);
        telemetry.update();
    }
}