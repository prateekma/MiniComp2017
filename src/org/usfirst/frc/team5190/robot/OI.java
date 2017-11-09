package org.usfirst.frc.team5190.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team5190.robot.commands.ETTCommand;
import org.usfirst.frc.team5190.robot.commands.RSSCommand;
import org.usfirst.frc.team5190.robot.commands.STTCommand;

public class OI
{
    private Joystick joy = new Joystick(0);

    OI()
    {
        JoystickButton b2 = new JoystickButton(joy, 2);
        JoystickButton b6 = new JoystickButton(joy, 6);
        JoystickButton b7 = new JoystickButton(joy, 7);

        b2.whenPressed(new RSSCommand());
        b6.whenPressed(new STTCommand());
        b7.whenPressed(new ETTCommand());
    }

    public Joystick getJoystick()
    {
        return joy;
    }
}
