package org.usfirst.frc.team5190.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.RobotDrive;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

import org.usfirst.frc.team5190.robot.Robot;
import org.usfirst.frc.team5190.robot.commands.TeleDriveWithJoystick;

public class JaguarDriveTrain extends DriveTrain
{
    RobotDrive robotDrive;

    public JaguarDriveTrain()
    {
        robotDrive = new RobotDrive(jFrontLeft, jRearLeft, jFrontRight, jRearRight);
        gyro = new AHRS(I2C.Port.kMXP);
    }

    public void drive()
	{
		robotDrive.arcadeDrive(Robot.oi.getJoystick());
	}
    
    @Override
    public void initDefaultCommand()
    {
    	setDefaultCommand(new TeleDriveWithJoystick());
    }
    
    public void end()
    {
    	robotDrive.drive(0, 0);
    }
}
