package org.usfirst.frc.team5190.robot.commands;

/*
  FRC Team 5190
  Team 3rd Pick
 */

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5190.robot.Robot;

public class ETTCommand extends Command
{
    public ETTCommand()
    {
        super("ETTCommand");

        requires(Robot.driveTrain);
        requires(Robot.teeterTotter);
    }

    @Override
    public void initialize()
    {
        Robot.teeterTotter.stop();
    }

    @Override
    protected boolean isFinished()
    {
        return true;
    }
}
