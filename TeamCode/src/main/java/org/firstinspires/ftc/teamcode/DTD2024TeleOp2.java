package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;


@TeleOp
public class DTD2024TeleOp2 extends LinearOpMode {
    // Config
    private double placementLinearSpeed = 1;

    // Input tuning
    private double joystickDeadzone = 0.05;

    private double translateSpeed = -1;
    private double turnSpeed = 1;
    private double strafeSpeedTune = 0.1;

    // Motor tuning
    private double linearPower = 0;

    // Input
    private double forwardBackValue = 0;
    private double sideToSideValue = 0;
    private double rotationValue = 0;


    @Override
    public void runOpMode() {
        // Setup
        Gamepad helmGamepad = gamepad1;
        Gamepad weaponsGamepad = gamepad2;



        // Tell driver it is initialized
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait until the robot is running
        waitForStart();


        while(opModeIsActive()) {
            WiringConnections m_wiringConnections = new WiringConnections();

            DTD2024MecanumChassis m_chassis = new DTD2024MecanumChassis(
                    hardwareMap.get(DcMotor.class, m_wiringConnections.frontLeftMotorName),
                    hardwareMap.get(DcMotor.class, m_wiringConnections.frontRightMotorName),
                    hardwareMap.get(DcMotor.class, m_wiringConnections.backLeftMotorName),
                    hardwareMap.get(DcMotor.class, m_wiringConnections.backRightMotorName),
                    strafeSpeedTune);

            // Initializes the hanger with the specified motor
            DTD2024Hanger m_hanger = new DTD2024Hanger(hardwareMap.get(DcMotor.class, m_wiringConnections.hangerName));

            // Gamepad input, if statements are for deadzones
            forwardBackValue = Math.abs(helmGamepad.left_stick_y) >= joystickDeadzone // Condition
                    ? (helmGamepad.left_stick_y * Math.abs(helmGamepad.left_stick_y) - joystickDeadzone) * translateSpeed // True
                    : 0; // False

            sideToSideValue = Math.abs(helmGamepad.left_stick_x) >= joystickDeadzone // Condition
                    ? (helmGamepad.left_stick_x * Math.abs(helmGamepad.left_stick_x) - joystickDeadzone) * translateSpeed // True
                    : 0; // False

            rotationValue = Math.abs(helmGamepad.right_stick_x) >= joystickDeadzone // Condition
                    ? (helmGamepad.right_stick_x * Math.abs(helmGamepad.right_stick_x) - joystickDeadzone) * turnSpeed // True
                    : 0; // False


            linearPower = Math.abs(weaponsGamepad.right_stick_y) >= joystickDeadzone // Condition
                    ? (weaponsGamepad.right_stick_y * Math.abs(weaponsGamepad.right_stick_y) - joystickDeadzone) * placementLinearSpeed // True
                    : 0; // False

            telemetry.addData("Status", "\nLX: " + helmGamepad.left_stick_x + "\nLY: " + helmGamepad.left_stick_y + "\nRX: " + helmGamepad.right_stick_x + "\nRY: " + helmGamepad.right_stick_y + "\nSanity: -5");
            telemetry.update();

            m_chassis.setForwardBackValue(forwardBackValue);
            m_chassis.setSideToSideValue(sideToSideValue);
            m_chassis.setRotationValue(rotationValue);

            // Moves the hanger
            m_hanger.move(linearPower);
        }
    }
}
