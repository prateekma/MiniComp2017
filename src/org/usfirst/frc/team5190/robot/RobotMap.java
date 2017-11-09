package org.usfirst.frc.team5190.robot;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Jaguar;

public class RobotMap
{
    // Motor constants
    private static final int FRONT_LEFT = 0;
    private static final int FRONT_RIGHT = 14;
    private static final int REAR_LEFT = 1;
    private static final int REAR_RIGHT = 15;

    // Straight drive PID constants
    public static final double P_STRAIGHT = 0.01;
    public static final double I_STRAIGHT = 0.01;
    public static final double D_STRAIGHT = 0.03;

    // Balance drive PID constants
    public static final double P_BALANCE = 0.04;
    public static final double I_BALANCE = 0.01;
    public static final double D_BALANCE = 0.03;

    // Other important PID constants
    public static final double MAX_POWER = 0.15;
    public static final double MAX_PITCH = 9;

    // Gyroscope
    public static AHRS gyro;

    // Motors for testing purposes
    public static Jaguar jFrontLeft = new Jaguar(FRONT_LEFT);
    public static Jaguar jFrontRight = new Jaguar(FRONT_RIGHT);
    public static Jaguar jRearLeft = new Jaguar(REAR_LEFT);
    public static Jaguar jRearRight = new Jaguar(REAR_RIGHT);

    // CANTalon motors
    public static CANTalon frontLeft = new CANTalon(FRONT_LEFT);
    public static CANTalon frontRight = new CANTalon(FRONT_RIGHT);
    public static CANTalon rearLeft = new CANTalon(REAR_LEFT);
    public static CANTalon rearRight = new CANTalon(REAR_RIGHT);
}
