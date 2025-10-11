package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import java.awt.font.NumericShaper;

@TeleOp(name="JoystickMotorControl", group="Test")
public class JoyStickMotorControl extends OpMode {

    private DcMotor motor;

    @Override
    public void init() {
        motor = hardwareMap.get(DcMotor.class, "front_left_drive");
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {
        double power = -gamepad1.left_stick_y;
        power = Range.clip(power, -1.0, 1.0);
        motor.setPower(power);

        telemetry.addData("Status", "Loop running!");

        telemetry.addData("Joystick", gamepad1.left_stick_y);
        telemetry.addData("Power", power);
        telemetry.addData("Gamepad A", gamepad1.a);
        telemetry.addData("Gamepad B", gamepad1.b);
        telemetry.addData("Left Stick X", gamepad1.left_stick_x);
        telemetry.addData("Right Trigger", gamepad1.right_trigger);
        telemetry.update();
    }
}
