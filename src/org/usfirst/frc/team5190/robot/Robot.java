package org.usfirst.frc.team5190.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5190.robot.commands.ETTCommand;
import org.usfirst.frc.team5190.robot.commands.STTCommand;
import org.usfirst.frc.team5190.robot.subsystems.ATTSubsystem;
import org.usfirst.frc.team5190.robot.subsystems.JDTSubsystem;

public class Robot extends IterativeRobot
{
    public static JDTSubsystem driveTrain = new JDTSubsystem();
    public static ATTSubsystem teeterTotter = new ATTSubsystem();
    public static OI oi = new OI();

    private STTCommand sttCommand = new STTCommand();
    private ETTCommand ettCommand = new ETTCommand();

    private Preferences prefs;

    @Override
    public void robotInit()
    {
        System.out.println("Robot Init.");

        prefs = Preferences.getInstance();

//        P_STRAIGHT = prefs.getDouble("P Straight", 0.2);
//        I_STRAIGHT = prefs.getDouble("I Straight", 0);
//        D_STRAIGHT = prefs.getDouble("D Straight", 0);
//
//        P_BALANCE = prefs.getDouble("P Balance", 0.035);
//        I_BALANCE = prefs.getDouble("I Balance", 0);
//        D_BALANCE = prefs.getDouble("D Balance", 0.3);

        SmartDashboard.putData("PID Controller", teeterTotter.getPIDController());
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
        System.out.println("Teleop Init.");
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