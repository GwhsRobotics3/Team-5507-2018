package org.usfirst.frc.team5507.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class DpadButton extends Button {
	
	private GenericHID joystick;
	private int angle;
	
	public static final int DPAD_UP = 0;
	public static final int DPAD_RIGHT = 90;
	public static final int DPAD_DOWN = 180; 
	public static final int DPAD_LEFT = 270;
	
	public DpadButton(GenericHID joystick, int angle) {
		this.joystick = joystick;
		this.angle = angle;	
	}
	
	public boolean get() {
		return joystick.getPOV() == angle;
	}
}
