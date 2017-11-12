package org.usfirst.frc.team5190.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5190.robot.Robot;
import org.usfirst.frc.team5190.robot.commands.JOYCommand;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

public class JDTSubsystem extends Subsystem
{
    @SuppressWarnings("WeakerAccess")
    RobotDrive robotDrive;

    public JDTSubsystem()
    {
        robotDrive = new RobotDrive(jFrontLeft, jRearLeft, jFrontRight, jRearRight);
        gyro = new AHRS(SPI.Port.kMXP);
    }

    public void drive()
    {
        robotDrive.arcadeDrive(Robot.oi.getJoystick());
    }

    @Override
    public void initDefaultCommand()
    {
        this.setDefaultCommand(new JOYCommand());
    }

    public void stop()
    {
        robotDrive.drive(0, 0);
    }

    public void reset()
    {
        robotDrive.drive(0, 0);
        System.out.println("DTSubsystem Reset.");
    }
}
