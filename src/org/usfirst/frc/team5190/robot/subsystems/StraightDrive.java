package org.usfirst.frc.team5190.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.usfirst.frc.team5190.robot.Robot;

import static org.usfirst.frc.team5190.robot.RobotMap.gyro;

public class StraightDrive extends PIDSubsystem
{
    public StraightDrive(double p, double i, double d)
    {
        super("Straight Drive", p, i, d);

        gyro.reset();

        this.setSetpoint(5.0);
        this.setInputRange(0, 7);
        this.setPercentTolerance(10);
        this.setOutputRange(-1, 1);
    }

    @Override
    public void disable()
    {
        Robot.balanceDrive.enable();
        super.disable();
    }

    @Override
    protected double returnPIDInput()
    {
        double pitch = gyro.getPitch();

        if (pitch > 4.5 && pitch < 5.5)
            this.disable();

        if (pitch < 0)
            return 0;

        return pitch;
    }

    public boolean isEnabled()
    {
        return this.isEnabled();
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
