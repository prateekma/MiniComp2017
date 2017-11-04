package org.usfirst.frc.team5190.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team5190.robot.commands.StartTeeterTotter;
import org.usfirst.frc.team5190.robot.commands.StopTeeterTotter;

public class OI
{
    private Joystick joy = new Joystick(0);

    // Constructor
    OI()
    {
        JoystickButton b5 = new JoystickButton(joy, 6);
        JoystickButton b6 = new JoystickButton(joy, 7);

        b5.whenPressed(new StartTeeterTotter());
        b6.whenPressed(new StopTeeterTotter());
    }

    // Getter Method
    public Joystick getJoystick()
    {
        return joy;
    }
}
