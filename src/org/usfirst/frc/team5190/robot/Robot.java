package org.usfirst.frc.team5190.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team5190.robot.commands.STTCommand;
import org.usfirst.frc.team5190.robot.commands.ETTCommand;
import org.usfirst.frc.team5190.robot.subsystems.DTSubsystem;
import org.usfirst.frc.team5190.robot.subsystems.ATTSubsystem;

public class Robot extends IterativeRobot
{
    public static DTSubsystem driveTrain = new DTSubsystem();
    public static ATTSubsystem teeterTotter = new ATTSubsystem();
    public static OI oi;

    private STTCommand sttCommand = new STTCommand();
    private ETTCommand ettCommand = new ETTCommand();

    @Override
    public void robotInit()
    {

    }

    @Override
    public void disabledInit()
    {
        ettCommand.start();
    }

    @Override
    public void disabledPeriodic()
    {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit()
    {
        System.out.println("Autonomous Mode Enabled.");
        sttCommand.start();
    }

    @Override
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit()
    {
        ettCommand.start();
        System.out.println("Manual Operated Mode Enabled.");
    }

    @Override
    public void teleopPeriodic()
    {
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic()
    {
        LiveWindow.run();
    }
}