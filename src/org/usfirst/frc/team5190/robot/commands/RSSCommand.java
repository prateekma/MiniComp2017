package org.usfirst.frc.team5190.robot.commands;

/*
  FRC Team 5190
  Team 3rd Pick
 */

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5190.robot.Robot;

public class RSSCommand extends Command
{
	public RSSCommand()
	{
		super("RSSCommand");

		requires(Robot.driveTrain);
		requires(Robot.balanceDrive);
		requires(Robot.straightDrive);
	}

	@Override
	protected void initialize()
	{
		Robot.driveTrain.reset();
		Robot.straightDrive.reset();
	}

	@Override
	protected boolean isFinished()
	{
		return true;
	}
}
