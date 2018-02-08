package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.RobotMap;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {
	int IMG_WIDTH = 320;
	int IMG_HEIGHT = 240;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Camera() {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

