package org.usfirst.frc.team5190.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5190.robot.Robot;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

public class BALSubsystem extends PIDSubsystem
{
	public BALSubsystem()
	{
		super("BALSubsystem", P_BALANCE, I_BALANCE, D_BALANCE);

		this.getPIDController().reset();

		this.setOutputRange(-0.3, 0.3);
		this.setAbsoluteTolerance(BAL_TOLERANCE);
		this.setSetpoint(initialPitch);
	}

	@Override
	protected double returnPIDInput()
	{
		double in = gyro.getPitch();

		SmartDashboard.putNumber("BAL PITCH: ", in);
		return in;
	}

	@Override
	protected void usePIDOutput(double output)
	{
		SmartDashboard.putNumber("BAL OUT: ", -output);
		Robot.driveTrain.robotDrive.drive(-output, 0);
	}

	@Override
	protected void initDefaultCommand()
	{

	}

	@Override
	public boolean onTarget()
	{
		return false;
	}
}
