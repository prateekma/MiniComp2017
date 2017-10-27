package org.usfirst.frc.team5190.robot.subsystems;

import com.ctre.CANTalon.TalonControlMode;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5190.robot.Robot;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

public class DriveTrain extends Subsystem
{
	private RobotDrive robotDrive;

	public DriveTrain()
	{
		frontLeft.changeControlMode(TalonControlMode.PercentVbus);
		rearLeft.changeControlMode(TalonControlMode.Follower);
		rearLeft.set(frontLeft.getDeviceID());

		frontRight.changeControlMode(TalonControlMode.PercentVbus);
		rearRight.changeControlMode(TalonControlMode.Follower);
		rearRight.set(frontRight.getDeviceID());

		robotDrive = new RobotDrive(frontLeft, frontRight);

		gyro = new AHRS(I2C.Port.kMXP);
	}

	public void drive()
	{
		robotDrive.arcadeDrive(Robot.oi.getController().getY(Hand.kLeft), Robot.oi.getController().getX(Hand.kLeft));
	}

	public void drive(double left, double right)
	{
		robotDrive.setLeftRightMotorOutputs(left, right);
	}

	@Override
	public void initDefaultCommand()
	{
		this.drive();
	}
}
