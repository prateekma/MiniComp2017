package org.usfirst.frc.team5190.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team5190.robot.commands.ETTCommand;
import org.usfirst.frc.team5190.robot.commands.STTCommand;
import org.usfirst.frc.team5190.robot.subsystems.ATTSubsystem;
import org.usfirst.frc.team5190.robot.subsystems.JDTSubsystem;

public class Robot extends IterativeRobot
{
    public static JDTSubsystem driveTrain = new JDTSubsystem();
    public static ATTSubsystem teeterTotter = new ATTSubsystem();
    public static OI oi;

    private STTCommand sttCommand = new STTCommand();
    private ETTCommand ettCommand = new ETTCommand();

    @Override
    public void robotInit()
    {
        System.out.println("Robot Init.");
    }

    @Override
    public void disabledInit()
    {
        System.out.println("Disabled Init.");
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
        System.out.println("Autonomous Init.");
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
        System.out.println("Manual Init.");
        ettCommand.start();
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