package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class ControlServo extends OpMode {

    TestServoMotors testServoMotors = new TestServoMotors();

    @Override
    public void init() {
        testServoMotors.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.a) {
            testServoMotors.setServ0(0);
        }
        else if (gamepad1.b){
            testServoMotors.setServ0(1.0);
        }
        else {
            testServoMotors.setServ0(0.5);
        }
    }
}
