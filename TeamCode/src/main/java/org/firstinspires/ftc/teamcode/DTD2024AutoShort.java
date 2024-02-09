package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.Timer;
import java.util.TimerTask;

// 1.5 forward - short
// 1 left 3.5 forward - long

//@Disabled
@Autonomous
public class DTD2024AutoShort extends LinearOpMode {
	// Timer
	private Timer m_timer;
	final long FORWARD_DELAY = 2000L; //in ms

	// Drivetrain motors
	private DcMotor frontLeftMotor;
	private DcMotor frontRightMotor;
	private DcMotor backLeftMotor;
	private DcMotor backRightMotor;

	// Overall speeeed
	private double moveSpeed = 0.5;
	private double strafeSpeedTune = -0.1;

	private void waitTime(long time) {
		long startTime = System.currentTimeMillis();
		long elapsedTime = 0L;

		while (elapsedTime < time) {
			elapsedTime = System.currentTimeMillis() - startTime;
		}
	}

	// Actually run the robot
	public void runOpMode() {
		WiringConnections m_wiringConnections = new WiringConnections();

		DTD2024MecanumChassis chassis = new DTD2024MecanumChassis(
				hardwareMap.get(DcMotor.class, m_wiringConnections.frontLeftMotorName),
				hardwareMap.get(DcMotor.class, m_wiringConnections.frontRightMotorName),
				hardwareMap.get(DcMotor.class, m_wiringConnections.backLeftMotorName),
				hardwareMap.get(DcMotor.class, m_wiringConnections.backRightMotorName),
				strafeSpeedTune);

		// Wait until the robot is running
		waitForStart();

		// Move forward
		chassis.setForwardBackValue(moveSpeed);

		waitTime(FORWARD_DELAY);

		// Stop
		chassis.setForwardBackValue(0);
	}
}
