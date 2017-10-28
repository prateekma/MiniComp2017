package org.usfirst.frc.team5190.robot.subsystems;


import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.usfirst.frc.team5190.robot.Robot;

import static org.usfirst.frc.team5190.robot.RobotMap.gyro;

public class BalanceDrive extends PIDSubsystem
{
    public BalanceDrive(double p, double i, double d)
    {
        super("Balance Drive", p, i, d);

        this.setSetpoint(0);
        this.setAbsoluteTolerance(0.05);
        this.setInputRange(-90, 90);
        this.setOutputRange(-1, 1);
    }

    @Override
    protected double returnPIDInput()
    {
        double pitch = gyro.getPitch();

        if (pitch < -90)
            return -90;

        else if (pitch > 90)
            return 90;

        return pitch;
    }

    @Override
    protected void usePIDOutput(double v)
    {
        Robot.driveTrain.drive(v, v);
    }

    @Override
    protected void initDefaultCommand()
    {

    }
}
