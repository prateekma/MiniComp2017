package org.usfirst.frc.team5190.robot.subsystems;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5190.robot.Robot;
import org.usfirst.frc.team5190.robot.commands.JOYCommand;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

public class DTSubsystem extends Subsystem
{
    RobotDrive robotDrive;

    public DTSubsystem()
    {
        frontLeft   .changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        rearLeft    .changeControlMode(CANTalon.TalonControlMode.Follower);
        rearLeft    .set(frontLeft.getDeviceID());

        frontRight  .changeControlMode(CANTalon.TalonControlMode.PercentVbus);
        rearRight   .changeControlMode(CANTalon.TalonControlMode.Follower);
        rearRight   .set(frontRight.getDeviceID());

        robotDrive  = new RobotDrive(frontLeft, frontRight);

        gyro        = new AHRS(SPI.Port.kMXP);
    }

    @Override
    public void initDefaultCommand()
    {
        this.setDefaultCommand(new JOYCommand());
    }

    public void drive()
    {
        robotDrive.arcadeDrive(Robot.oi.getJoystick());
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
