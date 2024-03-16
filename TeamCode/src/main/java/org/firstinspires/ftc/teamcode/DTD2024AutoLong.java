package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.Timer;
import java.util.TimerTask;

// 2s forward - short
// 2s left 5s forward - long

//@Disabled
@Autonomous
public class DTD2024AutoLong extends LinearOpMode {
    // Timer
    private Timer m_timer;
    final long SIDEWAYS_DELAY = 2000L; //in ms
    final long FORWARD_DELAY = 5000L; //in ms

    // Drivetrain motors
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    // Overall speeeed
    private double moveSpeed = 0.5;
    private double strafeSpeedTune = 0.15;

    private void waitTime(long time) {
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;

        while (elapsedTime < time) {
            elapsedTime = System.currentTimeMillis() - startTime;

            // Tell driver about the robot
            telemetry.addData("Status", "Sanity: " + (-elapsedTime));
            telemetry.update();
        }
    }


    // Actually run the robot
    public void runOpMode() {
        // Tell driver about the robot
        telemetry.addData("Status", "Sanity: -5.0");
        telemetry.update();
        WiringConnections m_wiringConnections = new WiringConnections();

        DTD2024MecanumChassis chassis = new DTD2024MecanumChassis(
                hardwareMap.get(DcMotor.class, m_wiringConnections.frontLeftMotorName),
                hardwareMap.get(DcMotor.class, m_wiringConnections.frontRightMotorName),
                hardwareMap.get(DcMotor.class, m_wiringConnections.backLeftMotorName),
                hardwareMap.get(DcMotor.class, m_wiringConnections.backRightMotorName),
                strafeSpeedTune);

        // Wait until the robot is running
        waitForStart();

        // Move Sideways
        chassis.setSideToSideValue(-moveSpeed);

        waitTime(SIDEWAYS_DELAY);

        // Move Sideways
        chassis.setForwardBackValue(moveSpeed);

        waitTime(FORWARD_DELAY);

        // Stop
        chassis.setSideToSideValue(0);
    }
}
