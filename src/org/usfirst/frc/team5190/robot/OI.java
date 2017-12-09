package org.usfirst.frc.team5190.robot;

/*
  FRC Team 5190
  Team 3rd Pick
 */

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc.team5190.robot.commands.ETTCommand;
import org.usfirst.frc.team5190.robot.commands.RSSCommand;
import org.usfirst.frc.team5190.robot.commands.STTCommand;

public class OI
{
    // Joystick Declaration
    private Joystick joy = new Joystick(0);
    private XboxController xbox = new XboxController(0);

    OI()
    {
        // Joystick button declaration and instantiation
        JoystickButton b2 = new JoystickButton(joy, 2);
        JoystickButton b6 = new JoystickButton(joy, 6);
        JoystickButton b7 = new JoystickButton(joy, 7);

        // Ex. commands when buttons are pressed
        b2.whenPressed(new RSSCommand());
        b6.whenPressed(new STTCommand());
        b7.whenPressed(new ETTCommand());
    }

    public Joystick getJoystick()
    {
        return joy;
    }

    public XboxController getXbox()
    {
        return xbox;
    }

    public void setXbox(XboxController xbox)
    {
        this.xbox = xbox;
    }
}
