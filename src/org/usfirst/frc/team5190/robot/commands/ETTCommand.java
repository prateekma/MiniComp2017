package org.usfirst.frc.team5190.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5190.robot.Robot;

public class ETTCommand extends Command
{
    public ETTCommand()
    {
        super("ETTCommand");

        this.requires(Robot.driveTrain);
        this.requires(Robot.teeterTotter);
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
