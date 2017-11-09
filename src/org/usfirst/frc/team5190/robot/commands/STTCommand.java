package org.usfirst.frc.team5190.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5190.robot.Robot;
import org.usfirst.frc.team5190.robot.subsystems.ATTSubsystem;

public class STTCommand extends Command
{
    public STTCommand()
    {
        super("STTCommand");

        this.requires(Robot.driveTrain);
        this.requires(Robot.teeterTotter);
    }

    @Override
    public void initialize()
    {
        System.out.println("Teeter Totter balance has begun.");
        Robot.teeterTotter.initialize(ATTSubsystem.Stage.STRAIGHT_DRIVE);
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
