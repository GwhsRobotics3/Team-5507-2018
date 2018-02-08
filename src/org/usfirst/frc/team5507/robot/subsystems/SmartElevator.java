package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Constants;
import org.usfirst.frc.team5507.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartElevator extends Subsystem {
	private static WPI_TalonSRX elevatorPulley = new WPI_TalonSRX(RobotMap.elevatorPulley);	
	private static int currentState;
	public static final int STATE_HIGH = 3;
	public static final int STATE_MED = 2;
	public static final int STATE_LOW = 1;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public SmartElevator()
	{
		currentState = STATE_LOW;
		Constants.configTalonQuad(elevatorPulley);
		resetEncoders();
		LiveWindow.addChild(this, elevatorPulley);

	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand())
	}

	public static void resetEncoders() {
		elevatorPulley.setSelectedSensorPosition(0, 0, 0);
	}

	public static int getToggledState()
	{
		switch(currentState)
		{
		case(STATE_LOW):
			currentState = STATE_MED;

		break;
		case(STATE_MED):

			currentState = STATE_HIGH;

		break;
		case(STATE_HIGH):
			currentState = STATE_LOW;

		break;
		default:
			currentState = STATE_LOW;

			break;
		}
		return currentState;
	}

	public int getCurrentState()
	{
		return currentState;
	}

	public void setState(int state)
	{
		if(state > STATE_HIGH || state < STATE_LOW) {
			state = STATE_MED;
		}
		
		currentState = state;
		
		switch(currentState)
		{
			case(STATE_LOW):
				setDesiredPosition(Constants.ELEVATOR_LOW);
			
				break;
			case(STATE_MED):
				setDesiredPosition(Constants.ELEVATOR_MED);
			
				break;
			case(STATE_HIGH):
				setDesiredPosition(Constants.ELEVATOR_HIGH);
			
				break;
			default:
				setDesiredPosition(Constants.ELEVATOR_LOW);
				
				break;
		}
	}

	public static void setDesiredPosition(double inches)
	{
		double ticks = 12*64 / (Math.PI * 1.25) * inches; 
		elevatorPulley.set(ControlMode.MotionMagic, ticks);
		
	}

	public static int getNextStateUp()
	{

		switch(currentState) 
		{
			case(STATE_LOW):
				return STATE_MED;
			
			case(STATE_MED):
				return STATE_HIGH;
			
			case(STATE_HIGH):
				return STATE_HIGH;
			
			default:
				return STATE_MED;

		}
	}

	public static int getNextStateDown()
	{
		switch(currentState)
		{
			case(STATE_HIGH):
				return STATE_MED;
			
			case(STATE_MED):
				return STATE_LOW;
			
			case(STATE_LOW):
				return STATE_LOW;
			
			default:
				return STATE_MED;
		}
	}

	public double getCurrentPos()
	{
		return elevatorPulley.getSelectedSensorPosition(0);
	}

	public void stop()
	{
		elevatorPulley.set(0);
	}
	

}

