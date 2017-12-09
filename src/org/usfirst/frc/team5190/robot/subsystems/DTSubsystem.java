package org.usfirst.frc.team5190.robot.subsystems;

/*
  FRC Team 5190
  Team 3rd Pick
 */

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5190.robot.Robot;
import org.usfirst.frc.team5190.robot.commands.JOYCommand;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

@SuppressWarnings({"unused", "WeakerAccess"})
public class DTSubsystem extends Subsystem
{

    public DTSubsystem()
    {

        frontLeft.changeControlMode(CANTalon.TalonControlMode.Speed);
        frontLeft.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

        frontLeft.configEncoderCodesPerRev(360);

        frontLeft.reverseSensor(false);
        frontLeft.setPID(DT_P_ERROR, 0, 0, DT_F_GAIN, 0, 0, 0);

        frontRight.changeControlMode(CANTalon.TalonControlMode.Speed);
        frontRight.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

        frontRight.configEncoderCodesPerRev(360);

        rearLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
        rearLeft.set(frontLeft.getDeviceID());

        frontRight.reverseSensor(false);
        frontRight.setPID(DT_P_ERROR, 0, 0, DT_F_GAIN, 0, 0, 0);

        rearRight.changeControlMode(CANTalon.TalonControlMode.Follower);
        rearRight.set(frontRight.getDeviceID());

        navX = new AHRS(SPI.Port.kMXP);
    }

    @Override
    public void initDefaultCommand()
    {
        this.setDefaultCommand(new JOYCommand());
    }

    public void drive()
    {
        falconArcadeDrive(-Robot.oi.getJoystick().getY(), -Robot.oi.getJoystick().getX());
    }

    public void tankDrive()
    {
        falconTankDrive(-Robot.oi.getXbox().getY(GenericHID.Hand.kLeft), Robot.oi.getXbox().getY(GenericHID.Hand.kRight));
    }

    public void falconTankDrive(double leftValue, double rightValue)
    {
        if (leftValue >= 0.0)
        {
            leftValue = leftValue * leftValue;
        }
        else
        {
            leftValue = -(leftValue * leftValue);
        }
        if (rightValue >= 0.0)
        {
            rightValue = rightValue * rightValue;
        }
        else
        {
            rightValue = -(rightValue * rightValue);
        }

        leftValue *= MAX_RPM;
        rightValue *= MAX_RPM;

        frontLeft.set(leftValue);
        frontRight.set(rightValue);

    }

    public void falconArcadeDrive(double moveValue, double rotateValue)
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

        leftMotorSpeed *= MAX_RPM;
        rightMotorSpeed *= MAX_RPM;

        frontLeft.set(leftMotorSpeed);
        frontRight.set(-rightMotorSpeed);

        System.out.println(leftMotorSpeed);

    }

    public void falconDirectDrive(double rawLeft, double rawRight)
    {
        double left, right;

        if (rawLeft > MAX_RPM)
        {
            left = MAX_RPM;
        }
        else if (rawLeft < -MAX_RPM)
        {
            left = -MAX_RPM;
        }
        else
        {
            left = rawLeft;
        }

        if (rawRight > MAX_RPM)
        {
            right = MAX_RPM;
        }
        else if (rawRight < -MAX_RPM)
        {
            right = -MAX_RPM;
        }
        else
        {
            right = rawRight;
        }

        frontLeft.set(left);
        frontRight.set(right);

    }

    public void stop()
    {
        falconDirectDrive(0, 0);
    }

    public void reset()
    {
        falconDirectDrive(0, 0);
        System.out.println("DTSubsystem Reset.");
    }
}
