package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class DTD2024MecanumChassis {
    // Drivetrain motors
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;

    // Input
    private double forwardBackValue = 0;
    private double sideToSideValue = 0;
    private double rotationValue = 0;

    private double strafeSpeedTune = 0;

    /**
     * Creates a new chassis and assigns 4 motors based on config in WiringConnections
     * @param strafeSpeedTune rotational tune for moving sideways
     */
    public DTD2024MecanumChassis (
            DcMotor frontLeftMotor,
            DcMotor frontRightMotor,
            DcMotor backLeftMotor,
            DcMotor backRightMotor,
            double strafeSpeedTune) {

        // Setup
        WiringConnections m_wiringConnections = new WiringConnections();

        this.strafeSpeedTune = strafeSpeedTune;

        this.frontLeftMotor = frontLeftMotor; // Port 0
        this.frontRightMotor = frontRightMotor; // Port 1
        this.backLeftMotor = backLeftMotor; // Port 2
        this.backRightMotor = backRightMotor; // Port 3
    }

    /**
     * Makes it drive forward/backward
     * @param value how fast forward/back
     */
    public void setForwardBackValue(double value) {
        this.forwardBackValue = value;
        drive();
    }

    /**
     * Makes it drive sideways
     * @param value how fast sideways
     */
    public void setSideToSideValue(double value) {
        this.sideToSideValue = value;
        drive();
    }

    /**
     * Makes it turn
     * @param value how fast
     */
    public void setRotationValue(double value) {
        this.rotationValue = value;
        drive();
    }

    /**
     * Make it go brrrrrr
     */
    private void drive() {
        // Drive go brrrrrr (mecanum math as well)
        frontLeftMotor.setPower(forwardBackValue - rotationValue + sideToSideValue + strafeSpeedTune * sideToSideValue);
        frontRightMotor.setPower(-forwardBackValue - rotationValue + sideToSideValue + strafeSpeedTune * sideToSideValue);
        backLeftMotor.setPower((forwardBackValue - rotationValue - sideToSideValue));
        backRightMotor.setPower(-forwardBackValue - rotationValue - sideToSideValue);
    }
}
