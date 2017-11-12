package org.usfirst.frc.team5190.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team5190.robot.Robot;

import static org.usfirst.frc.team5190.robot.RobotMap.*;

public class ATTSubsystem extends PIDSubsystem
{
    private double setPoint;
    private double horizontalPitch;
    private double tolerance;

    private boolean printedPID = false;

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
        System.out.println("ATT Subsystem Reset.");
        System.out.println("P: " + P_STRAIGHT + "  |  " + "I: " + I_STRAIGHT + "  |  " + "D: " + D_STRAIGHT);
        debugConsole(0);
        infoToSmartDashboard(0);

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
        this.setOutputRange(-.3, .3);
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
        this.setOutputRange(-0.035 * MIN_PITCH, 0.035 * MIN_PITCH);

        this.enable();
    }

    private void debugConsole(double pidOut)
    {
        String stage  = current.toString();
        String input  = String.valueOf(returnPIDInput()).substring(0, 7);
        String set    = String.valueOf(setPoint).substring(0, 7);
        String output = String.valueOf(pidOut).substring(0, 7);

        if (!printedPID)
        {
            System.out.println("P: " + P_BALANCE + "  |  " + "I: " + I_BALANCE + "  |  " + "D: " + D_BALANCE);
            printedPID = true;
        }

        System.out.println("ST: " + stage + "  |  " + "IN: " + input + "  |  " + "SP: " + set + "  |  " + "OUT: " + output);
    }

    private void infoToSmartDashboard(double pidOut)
    {
        SmartDashboard.putString("ATTSubsystem Stage",    current.toString());
        SmartDashboard.putString("ATTSubsystem Input",    String.valueOf(returnPIDInput()).substring(0, 7));
        SmartDashboard.putString("ATTSubsystem Setpoint", String.valueOf(setPoint).substring(0, 7));
        SmartDashboard.putString("ATTSubsystem Output",   String.valueOf(pidOut).substring(0, 7));
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
        return gyro.getPitch();
    }

    @Override
    protected void usePIDOutput(double v)
    {
        double pidOut;

        if (current == Stage.STRAIGHT_DRIVE)
            pidOut = v;
        else
            pidOut = -v;

        debugConsole(pidOut);
        infoToSmartDashboard(pidOut);

        Robot.driveTrain.robotDrive.drive(pidOut, 0);

        if (current == Stage.STRAIGHT_DRIVE && Math.abs(setPoint - gyro.getPitch()) < tolerance)
            this.switchToBalanceDrive();
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