package org.usfirst.frc.team5190.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5190.robot.Robot;

public class RSSCommand extends Command
{
    public RSSCommand()
    {
        super("RSSCommand");

        requires(Robot.driveTrain);
        requires(Robot.teeterTotter);
    }

    @Override
    protected void initialize()
    {
        Robot.teeterTotter.reset();
        Robot.driveTrain.reset();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}
