package org.usfirst.frc.team5190.robot.subsystems;

import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5190.robot.Robot;
import org.usfirst.frc.team5190.robot.commands.TeleDriveWithJoystick;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

public class DriveTrain extends Subsystem
{
	RobotDrive robotDrive;

	public DriveTrain()
	{
		robotDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);

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
