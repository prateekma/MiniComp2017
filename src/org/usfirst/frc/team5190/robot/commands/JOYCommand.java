package org.usfirst.frc.team5190.robot.commands;

/*
  FRC Team 5190
  Team 3rd Pick
 */

import org.usfirst.frc.team5190.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class JOYCommand extends Command
{
    public JOYCommand()
    {
        super("JOYCommand");
        requires(Robot.driveTrain);
    }

    @Override
    protected void execute()
    {
        Robot.driveTrain.drive();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }

    @Override
    protected void end()
    {
        Robot.driveTrain.stop();
    }

    @Override
    protected void interrupted()
    {
        Robot.driveTrain.stop();
    }
}
