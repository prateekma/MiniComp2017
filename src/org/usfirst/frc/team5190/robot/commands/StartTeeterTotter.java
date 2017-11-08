package org.usfirst.frc.team5190.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5190.robot.Robot;
import org.usfirst.frc.team5190.robot.subsystems.TeeterTotter;

public class StartTeeterTotter extends Command
{
    public StartTeeterTotter()
    {
        super("StartTeeterTotter");
        requires(Robot.driveTrain);
        requires(Robot.teeterTotter);
    }

    @Override
    public void initialize()
    {
        System.out.println("Teeter Totter balance has begun.");
        Robot.teeterTotter.initialize(TeeterTotter.Stage.STRAIGHT_DRIVE);
    }

    @Override
    protected void end()
    {
        Robot.teeterTotter.end();
        System.out.println("Teeter Totter balance has ended.");
    }

    @Override
    protected void interrupted()
    {
        Robot.teeterTotter.end();
        System.out.println("Teeter Totter balance has ended.");
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}
