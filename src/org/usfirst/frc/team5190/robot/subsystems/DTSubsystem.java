package org.usfirst.frc.team5190.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5190.robot.commands.JOYCommand;

public class DTSubsystem extends Subsystem
{
    RobotDrive robotDrive;

//    public DTSubsystem()
//    {
//        frontLeft.changeControlMode(TalonControlMode.PercentVbus);
//        rearLeft.changeControlMode(TalonControlMode.Follower);
//        rearLeft.set(frontLeft.getDeviceID());
//
//        frontRight.changeControlMode(TalonControlMode.PercentVbus);
//        rearRight.changeControlMode(TalonControlMode.Follower);
//        rearRight.set(frontRight.getDeviceID());
//
//        robotDrive = new RobotDrive(frontLeft, frontRight);
//
//        gyro = new AHRS(SPI.Port.kMXP);
//    }
//
//    public void drive()
//    {
//        robotDrive.arcadeDrive(Robot.oi.getJoystick());
//    }
//
    @Override
    public void initDefaultCommand()
    {
        this.setDefaultCommand(new JOYCommand());
    }
//
//    public void stop()
//    {
//        robotDrive.drive(0, 0);
//    }
//
//    public void reset()
//    {
//        robotDrive.drive(0, 0);
//        System.out.println("DTSubsystem Reset.");
//    }
}
