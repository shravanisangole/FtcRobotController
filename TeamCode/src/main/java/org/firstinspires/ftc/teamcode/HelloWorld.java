package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp
public class HelloWorld extends OpMode {
    //Test
    @Override
    public void init() {
        telemetry.addData("Hello", "Spart10 Code Team!");
    }

    @Override
    public void loop() {

    }
}
