
package org.usfirst.frc.team5190.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5190.robot.subsystems.BalanceDrive;
import org.usfirst.frc.team5190.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5190.robot.subsystems.StraightDrive;

public class Robot extends IterativeRobot
{
    public static DriveTrain driveTrain = new DriveTrain();
    public static OI oi;
    public static StraightDrive straightDrive;
    public static BalanceDrive balanceDrive;

    private Command autonomousCommand;
    private SendableChooser<Command> chooser = new SendableChooser<>();

    @Override
    public void robotInit()
    {
        oi = new OI();
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
        autonomousCommand = chooser.getSelected();

        if (autonomousCommand != null)
            autonomousCommand.start();

        try
        {
            straightDrive = new StraightDrive(0.01, 0.01, 0.05);
            balanceDrive = new BalanceDrive(0.01, 0.01, 0.05);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        straightDrive.enable();
    }

    @Override
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit()
    {
        if (autonomousCommand != null)
            autonomousCommand.cancel();
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
