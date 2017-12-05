package org.usfirst.frc.team5190.robot;

/*
  FRC Team 5190
  Team 3rd Pick
 */

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5190.robot.commands.BALCommand;
import org.usfirst.frc.team5190.robot.commands.RSSCommand;
import org.usfirst.frc.team5190.robot.subsystems.BALSubsystem;
import org.usfirst.frc.team5190.robot.subsystems.DTSubsystem;
import org.usfirst.frc.team5190.robot.subsystems.STRSubsystem;

/* CHANGE OUTPUT PATH IN INTELLIJ IF PROGRAM DOESN'T COMPILE */

public class Robot extends IterativeRobot
{
    // Subsystem Declaration
    public static DTSubsystem driveTrain;
    public static BALSubsystem balanceDrive;
    public static STRSubsystem straightDrive;
    public static OI oi;

    // Command Declaration
    private BALCommand balCommand;

    @Override
    public void robotInit()
    {
        System.out.println("Robot Init.");

        // Subsystem Instantiation
        driveTrain = new DTSubsystem();
        balanceDrive = new BALSubsystem();
        straightDrive = new STRSubsystem();
        oi = new OI();

        // Command Instantiation
        balCommand = new BALCommand();

        // Reset subsystems
        new RSSCommand().start();

        SmartDashboard.putData("Straight Drive PID Controller", straightDrive.getPIDController());
        SmartDashboard.putData("Balance Drive PID Controller", balanceDrive.getPIDController());
    }

    @Override
    public void disabledInit()
    {
        System.out.println("Disabled Init.");

        // End Autonomous PID
        balCommand.cancel();
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
        balCommand.start();
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
        balCommand.cancel();
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