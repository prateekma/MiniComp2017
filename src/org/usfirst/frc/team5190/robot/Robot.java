
package org.usfirst.frc.team5190.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5190.robot.commands.StartTeeterTotter;
import org.usfirst.frc.team5190.robot.commands.StopTeeterTotter;
import org.usfirst.frc.team5190.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5190.robot.subsystems.JaguarDriveTrain;
import org.usfirst.frc.team5190.robot.subsystems.TeeterTotter;

public class Robot extends IterativeRobot
{
    public static JaguarDriveTrain driveTrain;
    public static OI oi;
    public static TeeterTotter teeterTotter;

    private StartTeeterTotter start;
    private StopTeeterTotter stop;


    private SendableChooser<Command> chooser = new SendableChooser<>();

    @Override
    public void robotInit()
    {
        oi = new OI();
        driveTrain = new JaguarDriveTrain();
        teeterTotter = new TeeterTotter();

        start = new StartTeeterTotter();
        stop = new StopTeeterTotter();

        SmartDashboard.putData("Auto mode", chooser);
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
        System.out.println("Autonomous Mode Enabled. Entering Straight Drive.");
        start.initialize();
    }

    @Override
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit()
    {
        stop.initialize();
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
