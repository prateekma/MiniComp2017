package org.usfirst.frc.team5190.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.omg.CORBA.PERSIST_STORE;
import org.usfirst.frc.team5190.robot.Robot;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

/**
 * Class for autonomous teeter totter balance.
 */
public class TeeterTotter extends PIDSubsystem
{
    // Variables to use for two stages of the PID loop.
    private double pidOut;
    private double setPoint;
    private double horizontalPitch;
    private double tolerance;

    // Stores the current stage of the PID loop.
    public enum Stage
    {
        STRAIGHT_DRIVE, BALANCE_DRIVE
    }
    private Stage current = Stage.STRAIGHT_DRIVE;

    // Constructor
    public TeeterTotter()
    {
        super("Teeter Totter", P_STRAIGHT, I_STRAIGHT, D_STRAIGHT);
    }

    // Initialize the PID loop depending on the stage
    public void initialize(Stage stage)
    {
        current = stage;

        // straight drive
        if (stage == Stage.STRAIGHT_DRIVE)
        {
            gyro.reset();

            this.horizontalPitch = gyro.getPitch();
            this.setPoint = MAX_PITCH - 1;
            this.tolerance = 0.5;

            this.getPIDController().setPID(P_STRAIGHT, I_STRAIGHT, D_STRAIGHT);
            this.getPIDController().setSetpoint(setPoint);

            this.setAbsoluteTolerance(tolerance);
            this.setOutputRange(-.7, .7);

            this.enable();
        }
        // balance drive
        else
        {
            this.setPoint = horizontalPitch;
            this.tolerance = 0.1;

            this.getPIDController().reset();
            this.getPIDController().setPID(P_BALANCE, I_BALANCE, D_BALANCE);
            this.getPIDController().setSetpoint(setPoint);

            this.setAbsoluteTolerance(tolerance);
            this.setOutputRange(-MAX_POWER, MAX_POWER);

            this.enable();
        }

    }

    // End the PID loop.
    public void end()
    {
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
        if (current == Stage.STRAIGHT_DRIVE)
            pidOut = -v;
        else
            pidOut = v;

        // debugging purposes
        System.out.println(returnPIDInput() + "------------->" + pidOut);
        Robot.driveTrain.drive(pidOut, 0);

        // switch to balance drive when necessary
        if (current == Stage.STRAIGHT_DRIVE && Math.abs(setPoint - gyro.getPitch()) < tolerance)
        {
            System.out.println("Entering Balance Drive");
            initialize(Stage.BALANCE_DRIVE);
        }
    }

    @Override
    public boolean onTarget()
    {
        return false;
    }

    @Override
    protected void initDefaultCommand()
    {
        // Nothing here.
    }
}