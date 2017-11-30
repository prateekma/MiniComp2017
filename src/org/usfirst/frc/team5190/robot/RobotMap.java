package org.usfirst.frc.team5190.robot;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

public class RobotMap
{
    private static final int FRONT_LEFT = 0;
    private static final int FRONT_RIGHT = 1;
    private static final int REAR_LEFT = 2;
    private static final int REAR_RIGHT = 3;

    public static final double P_STRAIGHT = 0.2;
    public static final double I_STRAIGHT = 0;
    public static final double D_STRAIGHT = 0;

    public static final double P_BALANCE = 0.035;
    public static final double I_BALANCE = 0;
    public static final double D_BALANCE = 0.3;

    public static final double MIN_PITCH = 5;

    public static AHRS gyro;

    public static CANTalon frontLeft    = new CANTalon(FRONT_LEFT);
    public static CANTalon frontRight   = new CANTalon(FRONT_RIGHT);
    public static CANTalon rearLeft     = new CANTalon(REAR_LEFT);
    public static CANTalon rearRight    = new CANTalon(REAR_RIGHT);
}
