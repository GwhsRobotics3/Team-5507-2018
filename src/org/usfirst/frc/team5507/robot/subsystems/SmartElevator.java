package org.usfirst.frc.team5507.robot.subsystems;


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
	
	public static final double ELEVATOR_HIGH = 30;
	public static final double ELEVATOR_MED = 15;
	public static final double ELEVATOR_LOW = 0;
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public SmartElevator()
	{
		currentState = STATE_LOW;
		ConfigTalon.configTalonQuad(elevatorPulley);
		resetEncoders();
		addChild("elevator pulley talon", elevatorPulley);

	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand())
	}

	public static void resetEncoders() {
		elevatorPulley.setSelectedSensorPosition(0, 0, 0);
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
				setDesiredPosition(ELEVATOR_LOW);					
				break;
				
			case(STATE_MED):
				setDesiredPosition(ELEVATOR_MED);
				break;
				
			case(STATE_HIGH):
				setDesiredPosition(ELEVATOR_HIGH);
				break;
				
			default:
				setDesiredPosition(ELEVATOR_LOW);
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

