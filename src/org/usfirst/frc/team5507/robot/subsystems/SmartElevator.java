package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Constants;
import org.usfirst.frc.team5507.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartElevator extends Subsystem {
	private static WPI_TalonSRX elevatorPulley = new WPI_TalonSRX(RobotMap.elevator);	
	private static int currentState;
	public static final int STATE_HIGH = 3;
	public static final int STATE_MEDIUM = 2;
	public static final int STATE_LOW = 1;
	private static DigitalInput limitSwitchTop = new DigitalInput(1);
	Counter counterTop = new Counter(limitSwitchTop);
	private static DigitalInput limitSwitchBottom = new DigitalInput(2);
	Counter counterBottom = new Counter(limitSwitchBottom);
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public SmartElevator()
	{
		currentState = STATE_LOW;
		DriveTrain.configTalon(elevatorPulley);
		resetEncoders();
		
	}
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		SmartDashboard.putNumber("Pulley Position", elevatorPulley.getSelectedSensorPosition(0));
	}
	
	public static void resetEncoders() {
		elevatorPulley.setSelectedSensorPosition(0, 0, 0);
	}

	public int getToggledState()
	{
		switch(currentState)
		{
			case(STATE_LOW):
				currentState = STATE_MEDIUM;
			
			break;
			case(STATE_MEDIUM):
				
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

	public static int getCurrentState()
	{
		return currentState;
	}

	public double getPositionForState(int currentState)
	{
		if(currentState == lowPos)
		{
			return Constants.EllowPosition;
		}
		else if(currentState == medPos)
		{
			return Constants.ElmedPosition;
		}
		else 
		{
			return Constants.ElhighPosition;
		}
	}

	public static void setDesiredPosition(double pos)
	{
		if(pos == Constants.ElhighPosition)
		{
			pos = Constants.ElhighPosition;
			while(pos < getCurrentPos())
			{
				goUp();
			}
		}	 
		else if(pos == Constants.ElmedPosition)
		{
			pos = Constants.ElmedPosition;
			if(getCurrentPos() < pos)
			{
				goUp();
			}
			else
			{
				goDown();
			}    		
		}
		else
		{
			pos = Constants.EllowPosition;
			if(getCurrentPos() < pos)
			{
				goDown();
			}
		}   	
	}

	public static void goUp()
	{
		elevatorPulley.set(.5);
	}

	public static void goDown()
	{
		elevatorPulley.set(-.5);
	}

	public static double getCurrentPos()
	{
		return elevatorPulley.getSelectedSensorPosition(0);
	}
	
	public boolean isSwitchSetTop() {
		return counterTop.get() > 0;
	}

	public boolean isSwitchSetBottom() {
		return counterBottom.get() > 0;
	}

	public void stop()
	{
		elevatorPulley.set(0);
	}
}

