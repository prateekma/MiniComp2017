package org.usfirst.frc.team5190.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5190.robot.Robot;
import org.usfirst.frc.team5190.robot.subsystems.TeeterTotter;

/**
 * Green Hope Falcons
 * FRC Team 5190
 * Programming Team
 */
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
        Robot.teeterTotter.initialize(TeeterTotter.Stage.STRAIGHT_DRIVE);
    }

    @Override
    protected void end()
    {
        Robot.teeterTotter.end();
        System.out.println("Ending Teeter Totter Balance");
    }

    @Override
    protected void interrupted()
    {
        Robot.teeterTotter.end();
        System.out.println("Cancelling Teeter Totter Balance");
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}
