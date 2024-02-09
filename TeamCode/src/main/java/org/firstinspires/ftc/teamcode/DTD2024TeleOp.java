package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp
public class DTD2024TeleOp extends LinearOpMode {
	// Drivetrain motors
	private DcMotor frontLeftMotor;
	private DcMotor frontRightMotor;
	private DcMotor backLeftMotor;
	private DcMotor backRightMotor;

	//Subsystem motors
	private DcMotor placementMotor;

	// Input
	private double forwardBackValue = 0;
	private double sideToSideValue = 0;
	private double rotationValue = 0;

	private double placementLinearSpeed = 1;

	// Input tuning
	private double joystickDeadzone = 0.05;

	private double translateSpeed = -1;
	private double turnSpeed = 1;
	private double strafeSpeedTune = -0.1;

	private double linearPower = 0;


	@Override
	public void runOpMode() {
		frontLeftMotor = hardwareMap.get(DcMotor.class, "frontleft"); // Port 0
		frontRightMotor = hardwareMap.get(DcMotor.class, "frontright"); // Port 1
		backLeftMotor = hardwareMap.get(DcMotor.class, "backleft"); // Port 2
		backRightMotor = hardwareMap.get(DcMotor.class, "backright"); // Port 3

		placementMotor = hardwareMap.get(DcMotor.class, "placementmotor");

		// Tell driver it is initialized
		telemetry.addData("Status", "Initialized");
		telemetry.update();

		// Wait until the robot is running
		waitForStart();

		while(opModeIsActive()) {
			// Tell driver it is running
			telemetry.addData("Status", "Running!");
			telemetry.update();

			// Gamepad input, if statements are for deadzones
			forwardBackValue = Math.abs(gamepad1.left_stick_y) >= joystickDeadzone // Condition
					? (gamepad1.left_stick_y * Math.abs(gamepad1.left_stick_y) - joystickDeadzone) * translateSpeed // True
					: 0; // False

			sideToSideValue = Math.abs(gamepad1.left_stick_x) >= joystickDeadzone // Condition
					? (gamepad1.left_stick_x * Math.abs(gamepad1.left_stick_x) - joystickDeadzone) * translateSpeed // True
					: 0; // False

			rotationValue = Math.abs(gamepad1.right_stick_x) >= joystickDeadzone // Condition
					? (gamepad1.right_stick_x * Math.abs(gamepad1.right_stick_x) - joystickDeadzone) * turnSpeed // True
					: 0; // False


			linearPower = Math.abs(gamepad2.right_stick_y) >= joystickDeadzone // Condition
					? (gamepad2.right_stick_y * Math.abs(gamepad2.right_stick_y) - joystickDeadzone) * placementLinearSpeed // True
					: 0; // False


			// Placement go brrrrrr
			placementMotor.setPower(linearPower);


			// Drive go brrrrrr (mecanum math as well)
			frontLeftMotor.setPower(forwardBackValue - rotationValue + sideToSideValue + strafeSpeedTune * sideToSideValue);
			frontRightMotor.setPower(-forwardBackValue - rotationValue + sideToSideValue + strafeSpeedTune * sideToSideValue);
			backLeftMotor.setPower(forwardBackValue - rotationValue - sideToSideValue);
			backRightMotor.setPower(-forwardBackValue - rotationValue - sideToSideValue);
		}
	}
}
