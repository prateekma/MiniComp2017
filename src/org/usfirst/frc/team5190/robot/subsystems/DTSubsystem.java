package org.usfirst.frc.team5190.robot.subsystems;

/*
  FRC Team 5190
  Team 3rd Pick
 */

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5190.robot.Robot;

import java.util.ArrayList;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

@SuppressWarnings({"unused", "WeakerAccess"})
public class DTSubsystem extends Subsystem
{
    public ArrayList<CANTalon> masters = new ArrayList<>();

    public DTSubsystem()
    {
        masters.add(frontLeft);
        masters.add(frontRight);

        for (CANTalon master : masters)
        {
            master.changeControlMode(CANTalon.TalonControlMode.Speed);
            master.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

            master.configEncoderCodesPerRev(360);

            master.configNominalOutputVoltage(-0.0F, 0.0F);
            master.configPeakOutputVoltage(-12F, 12F);
        }

        frontLeft.reverseSensor(false);
        frontLeft.setPID(DT_P_ERROR, 0, 0, DT_F_GAIN, 0, 0, 0);

        rearLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
        rearLeft.set(frontLeft.getDeviceID());

        frontRight.reverseSensor(true);
        frontRight.setPID(DT_P_ERROR, 0, 0, DT_F_GAIN, 0, 0, 0);

        rearRight.changeControlMode(CANTalon.TalonControlMode.Follower);
        rearRight.set(frontRight.getDeviceID());

        navX = new AHRS(SPI.Port.kMXP);
    }

    @Override
    public void initDefaultCommand()
    {
//        this.setDefaultCommand(new JOYCommand());
    }

    public void drive()
    {
        falconArcadeDrive(-Robot.oi.getJoystick().getY(), -Robot.oi.getJoystick().getX());
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
        frontRight.set(rightMotorSpeed);
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
        falconDirectDrive();0, 0);
        System.out.println("DTSubsystem Reset.");
    }
}
