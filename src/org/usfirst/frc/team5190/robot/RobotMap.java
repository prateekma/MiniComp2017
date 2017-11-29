package org.usfirst.frc.team5190.robot;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Jaguar;

public class RobotMap
{
    static final int FRONT_LEFT = 0;
    static final int FRONT_RIGHT = 1;
    static final int REAR_LEFT = 2;
    static final int REAR_RIGHT = 3;

    static final boolean IS_MENTORS_BOT = true;

    public static final double P_STRAIGHT = 0.2;
    public static final double I_STRAIGHT = 0;
    public static final double D_STRAIGHT = 0;

    public static final double P_BALANCE = 0.035;
    public static final double I_BALANCE = 0;
    public static final double D_BALANCE = 0.3;

    public static final double MIN_PITCH = 5;

    public static AHRS gyro;

    public static Jaguar jFrontLeft;
    public static Jaguar jFrontRight;
    public static Jaguar jRearLeft;
    public static Jaguar jRearRight;

    public static CANTalon frontLeft;
    public static CANTalon frontRight;
    public static CANTalon rearLeft;
    public static CANTalon rearRight;
}
