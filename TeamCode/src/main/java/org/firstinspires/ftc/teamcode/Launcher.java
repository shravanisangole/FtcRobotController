package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Launcher", group="Spart10_Code")
public class Launcher extends LinearOpMode {

    private DcMotor launcher;

    private static final double FWD_SPEED   = 0.30;
    private static final long FWD_MS        = 2000;
    public static final double REV_SPEED    = 0.95;
    private static final long REV_MS = 5000;

    @Override
    public void runOpMode() {

        launcher = hardwareMap.get(DcMotor.class, "launcher");
        launcher.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        launcher.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addLine("Ready. Press START.");
        telemetry.update();
        waitForStart();
        int i = 0;
       while (i < 10) {

           launcher.setPower(FWD_SPEED);
           sleep(FWD_MS);

           launcher.setPower(-REV_SPEED);
           sleep(REV_MS);

           launcher.setPower(0);
           telemetry.addLine("Done.");
           telemetry.update();
           telemetry.update();
           i = i +1;
       }

    }
}
