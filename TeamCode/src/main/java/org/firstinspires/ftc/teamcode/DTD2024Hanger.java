package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DTD2024Hanger {
    private DcMotor hangerMotor;

    // Constructor to initialize the motor
    public DTD2024Hanger(DcMotor hangerMotor) {
        // Setup
        WiringConnections m_wiringConnections = new WiringConnections();
        this.hangerMotor = hangerMotor;
    }

    // Sets the output of the motor
    public void move(double speed) {
        hangerMotor.setPower(speed);
    }
}
