package org.usfirst.frc.team5190.robot.commands;

/*
  FRC Team 5190
  Team 3rd Pick
 */

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5190.robot.Robot;

public class BALCommand extends Command
{
    public BALCommand()
    {
        super("BALCommand");

        requires(Robot.driveTrain);
        requires(Robot.balanceDrive);
        requires(Robot.straightDrive);
    }

    @Override
    public void initialize()
    {
        Robot.straightDrive.enable();
    }

    @Override
    protected void end()
    {
        try
        {
            Robot.balanceDrive.disable();
        }
        catch (Exception ignored) {}

        try
        {
            Robot.straightDrive.disable();
        }
        catch (Exception ignored) {}
    }

    @Override
    protected void interrupted()
    {
	    try
	    {
		    Robot.balanceDrive.disable();
	    }
	    catch (Exception ignored) {}

	    try
	    {
		    Robot.straightDrive.disable();
	    }
	    catch (Exception ignored) {}
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}
