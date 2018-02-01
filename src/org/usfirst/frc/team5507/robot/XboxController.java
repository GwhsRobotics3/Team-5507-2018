package org.usfirst.frc.team5507.robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.hal.HAL;

public class XboxController extends Joystick {
	
	public XboxController(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	private int m_outputs;
	private short m_leftRumble;
	private short m_rightRumble;
	
    public void setRumble(double leftValue, double rightValue){
		setRumble(RumbleType.kLeftRumble, leftValue);
		setRumble(RumbleType.kRightRumble, rightValue);
	}

	public void setRumble(RumbleType type, double value) {
		if (value < 0) {
			value = 0;
		} else if (value > 1) {
			value = 1;
		}
		if (type == RumbleType.kLeftRumble) {
			m_leftRumble = (short) (value * 65535);
		} else {
			m_rightRumble = (short) (value * 65535);
		}
		HAL.setJoystickOutputs((byte) getPort(), m_outputs, m_leftRumble, m_rightRumble);
	}

}
