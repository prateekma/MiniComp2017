
package org.usfirst.frc.team5190.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team5190.robot.commands.StartTeeterTotter;
import org.usfirst.frc.team5190.robot.commands.StopTeeterTotter;
import org.usfirst.frc.team5190.robot.subsystems.JaguarDriveTrain;
import org.usfirst.frc.team5190.robot.subsystems.TeeterTotter;

public class Robot extends IterativeRobot
{
    // Declare subsystems
    public static JaguarDriveTrain driveTrain;
    public static OI oi;
    public static TeeterTotter teeterTotter;

    // Declare commands
    private StartTeeterTotter start;
    private StopTeeterTotter stop;

    @Override
    public void robotInit()
    {
        // Initialize subsystems
        oi = new OI();
        driveTrain = new JaguarDriveTrain();
        teeterTotter = new TeeterTotter();

        // Initialize commands
        start = new StartTeeterTotter();
        stop = new StopTeeterTotter();
    }

    @Override
    public void disabledInit()
    {

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
        start.start();
    }

    @Override
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit()
    {
        stop.start();
        System.out.println("Teleoperated Mode Enabled.");
    }

    @Override
    public void teleopPeriodic()
    {
        Scheduler.getInstance().run();
        driveTrain.drive();
    }

    @Override
    public void testPeriodic()
    {
        LiveWindow.run();
    }
}
