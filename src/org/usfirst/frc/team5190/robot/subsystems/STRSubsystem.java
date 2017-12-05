package org.usfirst.frc.team5190.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5190.robot.Robot;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

public class STRSubsystem extends PIDSubsystem
{
    private double initYaw;
    private double initPitch;

    public STRSubsystem()
    {
        super("STRSubsystem", 0.03, 0, 0);

        this.init();

        this.setInputRange(-360, 360);
        this.setOutputRange(-1, 1);
        this.setSetpoint(initYaw);
        this.setAbsoluteTolerance(STR_TOLERANCE);
    }

    private void init()
    {
        gyro.reset();
        initYaw = gyro.getAngle();
        initPitch = gyro.getPitch();

        SmartDashboard.putNumber("ZERO PITCH: ", initPitch);
    }

    public void reset()
    {
        this.init();
    }

    @Override
    protected double returnPIDInput()
    {
        return gyro.getAngle();
    }

    @Override
    protected void usePIDOutput(double output)
    {
        if (Math.abs(gyro.getPitch() - this.initPitch) > 5)
        {
            initialPitch = initPitch;
            this.disable();
            Robot.balanceDrive.enable();
            return;
        }

        Robot.driveTrain.robotDrive.drive(0.35, -output);
    }

    @Override
    protected void initDefaultCommand()
    {

    }
}
