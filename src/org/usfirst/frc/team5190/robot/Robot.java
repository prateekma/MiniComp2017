package org.usfirst.frc.team5190.robot;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5190.robot.commands.ETTCommand;
import org.usfirst.frc.team5190.robot.commands.STTCommand;
import org.usfirst.frc.team5190.robot.subsystems.ATTSubsystem;
import org.usfirst.frc.team5190.robot.subsystems.JDTSubsystem;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

/* CHANGE OUTPUT PATH IN INTELLIJ IF PROGRAM DOESN'T COMPILE */

public class Robot extends IterativeRobot
{
    public static JDTSubsystem driveTrain;
    public static ATTSubsystem teeterTotter;
    public static OI oi;

    private STTCommand sttCommand = new STTCommand();
    private ETTCommand ettCommand = new ETTCommand();

    @Override
    public void robotInit()
    {
        System.out.println("Robot Init.");

        if (RobotMap.IS_MENTORS_BOT)
        {
            jFrontLeft = new Jaguar(FRONT_LEFT);
            jFrontRight = new Jaguar(FRONT_RIGHT);
            jRearLeft = new Jaguar(REAR_LEFT);
            jRearRight = new Jaguar(REAR_RIGHT);
        }
        else
        {
            frontLeft = new CANTalon(FRONT_LEFT);
            frontRight = new CANTalon(FRONT_RIGHT);
            rearLeft = new CANTalon(REAR_LEFT);
            rearRight = new CANTalon(REAR_RIGHT);
        }

        driveTrain = new JDTSubsystem();
        teeterTotter = new ATTSubsystem();
        oi = new OI();

        SmartDashboard.putData("ATT PID Controller", teeterTotter.getPIDController());
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