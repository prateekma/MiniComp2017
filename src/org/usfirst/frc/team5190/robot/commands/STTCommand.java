package org.usfirst.frc.team5190.robot.commands;

/*
  FRC Team 5190
  Team 3rd Pick
 */

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5190.robot.Robot;

public class STTCommand extends Command
{
    public STTCommand()
    {
        super("STTCommand");

        requires(Robot.driveTrain);
        requires(Robot.teeterTotter);
    }

    @Override
    public void initialize()
    {
        new RSSCommand().start();
        Robot.teeterTotter.start();
    }

    @Override
    protected void end()
    {
        Robot.teeterTotter.stop();
    }

    @Override
    protected void interrupted()
    {
        Robot.teeterTotter.stop();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}
