package org.usfirst.frc.team5190.robot.subsystems;

/*
  FRC Team 5190
  Team 3rd Pick
 */

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5190.robot.Robot;

import java.util.ArrayList;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

public class DTSubsystem extends Subsystem
{
    RobotDrive robotDrive;
    public ArrayList<CANTalon> masters = new ArrayList<>();

    private double fGain = calculateFGain(calculateVelocity(0.0, 1440.0));  // fix zeros from actual data
    private double pDiff = calculatePGain(.1, 0); // fix zeroes from actual data

    public DTSubsystem()
    {
        masters.add(frontLeft);
        masters.add(frontRight);

        for (CANTalon master : masters)
        {
            master.changeControlMode(CANTalon.TalonControlMode.Speed);
            master.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
            master.configEncoderCodesPerRev(360);
            master.configNominalOutputVoltage(0.0F, -0.0F);
            master.configPeakOutputVoltage(-12F, 12F);
        }

        // Left drive train motors instantiation and config.
        frontLeft.reverseSensor(false);
//        frontLeft.setPID(0.1, 0, 0, calculateFGain(1F, 116, 1440), 0, 0, 0);

        rearLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
        rearLeft.set(frontLeft.getDeviceID());
        rearLeft.enableBrakeMode(true);


        // Right drive train motors instantiation and config.
        frontRight.reverseSensor(true);
//        frontRight.setPID(0.1, 0, 0, calculateFGain(1F, 116, 1440), 0, 0, 0);


        rearRight.changeControlMode(CANTalon.TalonControlMode.Follower);
        rearRight.set(frontRight.getDeviceID());
        rearRight.enableBrakeMode(true);

        // RobotDrive object instantiation
        robotDrive = new RobotDrive(frontLeft, frontRight);

        // NavX instantiation
        gyro = new AHRS(SPI.Port.kMXP);

        robotDrive.setMaxOutput(116);
    }

    @Override
    public void initDefaultCommand()
    {
//        this.setDefaultCommand(new JOYCommand());
    }

    public void drive()
    {
        robotDrive.arcadeDrive(-Robot.oi.getJoystick().getY(), -Robot.oi.getJoystick().getX());
    }

    public void encoderDrive(double moveValue, double rotateValue)
    {
        double leftMotorSpeed, rightMotorSpeed;

        if (moveValue >= 0.0)
        {
            moveValue = moveValue * moveValue;
        }
        else
        {
            moveValue = -(moveValue * moveValue);
        }
        if (rotateValue >= 0.0)
        {
            rotateValue = rotateValue * rotateValue;
        }
        else
        {
            rotateValue = -(rotateValue * rotateValue);
        }
        if (moveValue > 0.0)
        {
            if (rotateValue > 0.0)
            {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            }
            else
            {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        }
        else
        {
            if (rotateValue > 0.0)
            {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
            else
            {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }

        frontLeft.set(leftMotorSpeed);
        frontRight.set(rightMotorSpeed);
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
