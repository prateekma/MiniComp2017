package org.usfirst.frc.team5190.robot;

/*
  FRC Team 5190
  Team 3rd Pick
 */

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

public class RobotMap
{
    // Motor Constants
    private static final int FRONT_LEFT = 1;
    private static final int FRONT_RIGHT = 12;
    private static final int REAR_LEFT = 20;
    private static final int REAR_RIGHT = 3;

    // PID Values for Straight Drive (autonomous)
    public static final double P_STRAIGHT = 0.2;
    public static final double I_STRAIGHT = 0.2;
    public static final double D_STRAIGHT = 0;

    // PID Values for Balance Drive (autonomous)
    public static final double P_BALANCE = 0.01;
    public static final double I_BALANCE = 0.2;
    public static final double D_BALANCE = 0.3;

    // Constant for autonomous
    public static final double MIN_PITCH = 5;

    // NavX Gyroscope
    public static AHRS gyro;

    // TalonSRX Motors
    public static CANTalon frontLeft    = new CANTalon(FRONT_LEFT);
    public static CANTalon frontRight   = new CANTalon(FRONT_RIGHT);
    public static CANTalon rearLeft     = new CANTalon(REAR_LEFT);
    public static CANTalon rearRight    = new CANTalon(REAR_RIGHT);
}
