package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Constants;
import org.usfirst.frc.team5507.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SmartElevator extends Subsystem {
	private static WPI_TalonSRX elevatorPulley = new WPI_TalonSRX(RobotMap.elevator);	
	private static int currentState = 1;
	public static final int highPos = 3;
	public static final int medPos = 2;
	public static final int lowPos = 1;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
   public int getNextStateDown()
   {
	   if(currentState == lowPos)
	   {
		   currentState = lowPos;
	   }
	   else if(currentState == medPos)
	   {
		   currentState = lowPos;
	   }
	   else
	   {
		   currentState = medPos;
	   }
	   return currentState;
   }
   
   public int getNextStateUp()
   {
	   if(currentState == lowPos)
	   {
		    currentState = medPos;
	   }
	   else if(currentState == medPos)
	   {
		   currentState = highPos;
	   }
	   else 
	   {
		   currentState = highPos;
	   }
	   return currentState;
   }
    
    public int getCurrentState()
    {
    	return currentState;
    }
    
    public double getPositionForState()
    {
    	if(currentState == lowPos)
    	{
    		return Constants.lowPosition;
    	}
    	else if(currentState == medPos)
    	{
    		return Constants.medPosition;
    	}
    	else 
    	{
    		return Constants.highPosition;
    	}
    }
    
    public void setDesiredPosition(double pos)
    {
    	if(pos == Constants.highPosition)
    	{
    		pos = Constants.highPosition;
    	}
    		elevatorPulley.set(.5);
    	
    }
}

