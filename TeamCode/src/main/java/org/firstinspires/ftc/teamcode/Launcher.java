package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="Launcher", group="Spart10_Code")
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

        boolean canPress = true;
        while (opModeIsActive()) {
            if(gamepad1.a && canPress) {
                launch_three();
                canPress = false;
            }
            if(!gamepad1.a){
                canPress = true;
            }

        }

       telemetry.addLine("Done.");
       telemetry.update();
       telemetry.update();

    }

    private void launch_three(){
        launch();
        launch();
        launch();
    }
    private void launch() {
        launcher.setPower(FWD_SPEED);
        sleep(FWD_MS);
        launcher.setPower(-REV_SPEED);
        sleep(REV_MS);
        launcher.setPower(0);
    }
}
