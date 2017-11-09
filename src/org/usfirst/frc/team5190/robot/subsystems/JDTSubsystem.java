package org.usfirst.frc.team5190.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.RobotDrive;
import org.usfirst.frc.team5190.robot.Robot;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

public class JDTSubsystem extends DTSubsystem
{
    @SuppressWarnings("WeakerAccess")
    RobotDrive robotDrive;

    public JDTSubsystem()
    {
        robotDrive = new RobotDrive(jFrontLeft, jRearLeft, jFrontRight, jRearRight);
        gyro = new AHRS(I2C.Port.kMXP);
    }

    public void drive()
	{
		robotDrive.arcadeDrive(Robot.oi.getJoystick());
	}
}
