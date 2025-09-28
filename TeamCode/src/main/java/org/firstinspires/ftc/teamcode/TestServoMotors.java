package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TestServoMotors {

    private Servo serv0;

    public void init(HardwareMap hwMap) {

        serv0 = hwMap.get(Servo.class, "Serv0");
    }

    public void setServ0(double angle) {
        serv0.setPosition(angle);
    }
}
