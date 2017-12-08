package org.usfirst.frc.team5190.robot;

/*
  FRC Team 5190
  Team 3rd Pick
 */

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;

public class RobotMap
{
    private static final int FRONT_LEFT = 1;
    private static final int FRONT_RIGHT = 12;
    private static final int REAR_LEFT = 20;
    private static final int REAR_RIGHT = 3;

    public static final double P_STRAIGHT = 0.2;
    public static final double I_STRAIGHT = 0.2;
    public static final double D_STRAIGHT = 0;

    public static final double P_BALANCE = 0.033;
    public static final double I_BALANCE = 0;
    public static final double D_BALANCE = 0.08; // 0.3 when worked

    public static final double MIN_PITCH = 5;

    public static final int MAX_RPM = 2800;
    public static final double DT_F_GAIN = calculateFGain(calculateVelocity(MAX_RPM, 1440.0));
    public static final double DT_P_ERROR = 0; //calculatePGain(.1, error)


    public static AHRS navX;

    public static CANTalon frontLeft = new CANTalon(FRONT_LEFT);
    public static CANTalon frontRight = new CANTalon(FRONT_RIGHT);
    public static CANTalon rearLeft = new CANTalon(REAR_LEFT);
    public static CANTalon rearRight = new CANTalon(REAR_RIGHT);


    public static double calculateVelocity(double rotationsPerMin, double nativeUnitsPerRevolution)
    {
        return rotationsPerMin * (1 / 60) * (1 / 10) * nativeUnitsPerRevolution;
    }

    public static double calculateFGain(double velocity)
    {
        return 1023 / velocity;
    }

    public static double calculatePGain(double throttleGain, double error)
    {
        return (throttleGain * 1023) / error;
    }
}
