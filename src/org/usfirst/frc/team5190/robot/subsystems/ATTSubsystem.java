package org.usfirst.frc.team5190.robot.subsystems;

/*
  FRC Team 5190
  Team 3rd Pick
 */

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.usfirst.frc.team5190.robot.Robot;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

public class ATTSubsystem extends PIDSubsystem
{
	private double setPoint;
	private double horizontalPitch;
	private double tolerance;

	private boolean printedPID = false;

	public enum Stage
	{
		STRAIGHT_DRIVE, BALANCE_DRIVE
	}

	private Stage current = Stage.STRAIGHT_DRIVE;

	public ATTSubsystem()
	{
		super("Teeter Totter", P_STRAIGHT, I_STRAIGHT, D_STRAIGHT);
		this.reset();
	}

	public void reset()
	{
		// Debug
		System.out.println("ATT Subsystem Reset.");
		System.out.println("P: " + P_STRAIGHT + "  |  " + "I: " + I_STRAIGHT + "  |  " + "D: " + D_STRAIGHT);
		debugConsole(0);

		// Reset and register "zero" pitch
		navX.reset();
		horizontalPitch = navX.getPitch();

		// Set stage
		current = Stage.STRAIGHT_DRIVE;
		setPoint = MIN_PITCH + horizontalPitch;
		tolerance = 0.5;

		if (this.getPIDController().isEnabled())
		{
			disable();
		}

		// Set constants
		this.getPIDController().setPID(P_STRAIGHT, I_STRAIGHT, D_STRAIGHT);
		this.getPIDController().setSetpoint(MIN_PITCH + horizontalPitch);
		this.setAbsoluteTolerance(tolerance);
		this.setOutputRange(-.51, .51);    // .3, .3
	}

	private void switchToBalanceDrive()
	{
		// Set stage
		current = Stage.BALANCE_DRIVE;
		setPoint = horizontalPitch;
		tolerance = 0.5;

		this.getPIDController().reset();

		// Set constants
		this.getPIDController().setPID(P_BALANCE, I_BALANCE, D_BALANCE);
		this.getPIDController().setSetpoint(setPoint);
		this.setAbsoluteTolerance(tolerance);
		this.setOutputRange(-.3, 0.3);    // -.4, .4

		this.enable();
	}

	private void debugConsole(double pidOut)
	{
		String stage = current.toString();
		String input = String.valueOf(returnPIDInput());
		String set = String.valueOf(setPoint);
		String output = String.valueOf(pidOut);

		if (!printedPID && pidOut != 0)
		{
			System.out.println("P: " + P_BALANCE + "  |  " + "I: " + I_BALANCE + "  |  " + "D: " + D_BALANCE);
			printedPID = true;
		}

		System.out.println("ST: " + stage + "  |  " + "IN: " + input + "  |  " + "SP: " + set + "  |  " + "OUT: " + output);
	}

	public void start()
	{
		System.out.println("ATT Subsystem Enabled.");
		this.enable();
	}

	public void stop()
	{
		System.out.println("ATT Subsystem Disabled.");
		this.disable();
	}

	@Override
	protected double returnPIDInput()
	{
		return navX.getPitch();
	}

	@Override
	protected void usePIDOutput(double v)
	{
		double pidOut;

		if (current == Stage.STRAIGHT_DRIVE)
		{
			pidOut = v;
		}
		else
		{
			pidOut = -v;
		}

		debugConsole(pidOut);

		Robot.driveTrain.falconArcadeDrive(pidOut, 0);

		if (current == Stage.STRAIGHT_DRIVE && (returnPIDInput() - horizontalPitch) > 5)
		{
			this.switchToBalanceDrive();
		}
	}

	@Override
	public boolean onTarget()
	{
		return false;
	}

	@Override
	protected void initDefaultCommand()
	{

	}
}