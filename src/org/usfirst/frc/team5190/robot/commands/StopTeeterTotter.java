package org.usfirst.frc.team5190.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5190.robot.Robot;

public class StopTeeterTotter extends Command
{
    public StopTeeterTotter()
    {
        super("StopTeeterTotter");
        requires(Robot.driveTrain);
        requires(Robot.teeterTotter);
    }

    @Override
    public void initialize()
    {
        System.out.println("Teeter Totter balance has ended.");
        Robot.teeterTotter.end();
    }

    @Override
    protected boolean isFinished()
    {
        return true;
    }
}
