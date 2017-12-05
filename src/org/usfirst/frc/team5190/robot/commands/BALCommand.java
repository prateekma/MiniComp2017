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
        Robot.balanceDrive.enable();
    }

    @Override
    protected void end()
    {
        Robot.balanceDrive.disable();
    }

    @Override
    protected void interrupted()
    {
        Robot.balanceDrive.disable();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}
