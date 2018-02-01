package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Constants;
import org.usfirst.frc.team5507.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SmartGripper extends Subsystem {
	private static int currentState = 1;
	public static final int grabPos = 3;
	public static final int readyPos = 2;
	public static final int startPos = 1;
	private static WPI_TalonSRX leftArm = new WPI_TalonSRX(RobotMap.leftArm);
	private static WPI_TalonSRX rightArm = new WPI_TalonSRX(RobotMap.rightArm);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void ArmEncoders()
    {
    	DriveTrain.configTalon(leftArm);
    	DriveTrain.configTalon(rightArm);
    }
    	public int getNextStateBackwards()
    	   {
    		   if(currentState == startPos)
    		   {
    			   currentState = startPos;
    		   }
    		   else if(currentState == readyPos)
    		   {
    			   currentState = startPos;
    		   }
    		   else
    		   {
    			   currentState = readyPos;
    		   }
    		   return currentState;
    	   }
    	   
    	   public int getNextStateForwards()
    	   {
    		   if(currentState == startPos)
    		   {
    			    currentState = readyPos;
    		   }
    		   else if(currentState == readyPos)
    		   {
    			   currentState = grabPos;
    		   }
    		   else 
    		   {
    			   currentState = grabPos;
    		   }
    		   return currentState;
    	   }
    	    
    	    public int getCurrentState()
    	    {
    	    	return currentState;
    	    }
    	    
    	    public double getPositionForState()
    	    {
    	    	if(currentState == startPos)
    	    	{
    	    		return Constants.startPosition;
    	    	}
    	    	else if(currentState == readyPos)
    	    	{
    	    		return Constants.readyPosition;
    	    	}
    	    	else 
    	    	{
    	    		return Constants.grabPosition;
    	    	}
    	    }
    	    
    	    public void setDesiredPosition(double pos)
    	    {
    	    	if(pos == Constants.grabPosition)
    	    	{
    	    		pos = Constants.grabPosition;
    	    	}
    	    	
    	    }
    }


