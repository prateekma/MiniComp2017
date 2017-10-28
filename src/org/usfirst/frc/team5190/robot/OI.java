package org.usfirst.frc.team5190.robot;

import edu.wpi.first.wpilibj.XboxController;

public class OI
{
    private XboxController controller = new XboxController(0);

    public XboxController getController()
    {
        return controller;
    }
}
