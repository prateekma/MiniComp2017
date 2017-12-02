package org.usfirst.frc.team5190.robot;

/*
  FRC Team 5190
  Team 3rd Pick
 */

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5190.robot.commands.ETTCommand;
import org.usfirst.frc.team5190.robot.commands.RSSCommand;
import org.usfirst.frc.team5190.robot.commands.STTCommand;
import org.usfirst.frc.team5190.robot.subsystems.ATTSubsystem;
import org.usfirst.frc.team5190.robot.subsystems.DTSubsystem;

/* CHANGE OUTPUT PATH IN INTELLIJ IF PROGRAM DOESN'T COMPILE */

public class Robot extends IterativeRobot
{
    // Subsystem Declaration
    public static DTSubsystem driveTrain;
    public static ATTSubsystem teeterTotter;
    public static OI oi;

    // Command Declaration
    private STTCommand sttCommand;
    private ETTCommand ettCommand;

    @Override
    public void robotInit()
    {
        System.out.println("Robot Init.");

        // Subsystem Instantiation
        driveTrain = new DTSubsystem();
        teeterTotter = new ATTSubsystem();
        oi = new OI();

        // Command Instantiation
        sttCommand = new STTCommand();
        ettCommand = new ETTCommand();

        // Reset subsystems
        new RSSCommand().start();

        SmartDashboard.putData("ATT PID Controller", teeterTotter.getPIDController());
    }

    @Override
    public void disabledInit()
    {
        System.out.println("Disabled Init.");

        // End Autonomous PID
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

        // Start Autonomous PID
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
        System.out.println("Teleop Init.");

        // End Autonomous PID
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