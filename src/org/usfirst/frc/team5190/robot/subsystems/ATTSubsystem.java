package org.usfirst.frc.team5190.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.usfirst.frc.team5190.robot.Robot;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

public class ATTSubsystem extends PIDSubsystem
{
    private double setPoint;
    private double horizontalPitch;
    private double tolerance;

    public enum Stage
    {
        STRAIGHT_DRIVE, BALANCE_DRIVE
    }

    private Stage current = Stage.STRAIGHT_DRIVE;

    public ATTSubsystem()
    {
        super("Teeter Totter", P_STRAIGHT, I_STRAIGHT, D_STRAIGHT);
        this.reset();
    }

    public void reset()
    {
        gyro.reset();
        horizontalPitch = gyro.getPitch();

        current = Stage.STRAIGHT_DRIVE;
        setPoint = MIN_PITCH + horizontalPitch;
        tolerance = 0.5;

        if (this.getPIDController().isEnabled())
            disable();

        this.getPIDController().setPID(P_STRAIGHT, I_STRAIGHT, D_STRAIGHT);
        this.getPIDController().setSetpoint(setPoint);

        this.setAbsoluteTolerance(tolerance);
        this.setOutputRange(-.5, .5);
    }

    public void start()
    {
        System.out.println("ATT Subsystem Enabled.");
        this.enable();
    }

    public void stop()
    {
        System.out.println("ATT Subsystem Disabled.");
        this.disable();
    }

    @Override
    protected double returnPIDInput()
    {
        double pitch = gyro.getPitch();

        if (current == Stage.STRAIGHT_DRIVE && pitch < 0)
            return 0;

        return pitch;
    }

    @Override
    protected void usePIDOutput(double v)
    {
        double pidOut;

        if (current == Stage.STRAIGHT_DRIVE)
            pidOut = -v;
        else
            pidOut = v;

        System.out.println(returnPIDInput() + "------------->" + pidOut);
        Robot.driveTrain.robotDrive.drive(pidOut, 0);

        if (current == Stage.STRAIGHT_DRIVE && Math.abs(setPoint - gyro.getPitch()) < tolerance)
        {
            System.out.println("Entering Balance Drive");
            this.switchToBalanceDrive();
        }
    }

    private void switchToBalanceDrive()
    {
        current = Stage.BALANCE_DRIVE;
        setPoint = horizontalPitch;
        tolerance = 0.1;

        this.getPIDController().reset();

        this.getPIDController().setPID(P_BALANCE, I_BALANCE, D_BALANCE);
        this.getPIDController().setSetpoint(setPoint);

        this.setAbsoluteTolerance(tolerance);
        this.setOutputRange(-0.03 * MIN_PITCH, 0.03 * MIN_PITCH);

        this.enable();
    }

    @Override
    public boolean onTarget()
    {
        return false;
    }

    @Override
    protected void initDefaultCommand()
    {

    }
}