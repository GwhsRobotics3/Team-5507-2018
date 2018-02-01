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
    public static void resetPos() 
    {
    	leftArm.setSelectedSensorPosition(0, 0, 0);
    	rightArm.setSelectedSensorPosition(0, 0, 0);
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
    	    
    	    public static int getCurrentState()
    	    {
    	    	return currentState;
    	    }
    	    
    	    public double getPositionForStateR()
    	    {
    	    	if(currentState == startPos)
    	    	{
    	    		return Constants.startPositionR;
    	    	}
    	    	else if(currentState == readyPos)
    	    	{
    	    		return Constants.readyPositionR;
    	    	}
    	    	else 
    	    	{
    	    		return Constants.grabPositionR;
    	    	}
    	    }
    	    
    	    public double getPositionForStateL()
    	    {
    	    	if(currentState == startPos)
    	    	{
    	    		return Constants.startPositionL;
    	    	}
    	    	else if(currentState == readyPos)
    	    	{
    	    		return Constants.readyPositionL;
    	    	}
    	    	else 
    	    	{
    	    		return Constants.grabPositionL;
    	    	}
    	    }
    	    
    	    public static void setDesiredPosition(double pos) {
    	    	if(getCurrentState() == startPos && pos == Constants.readyPositionR)
    	    	{
    	    		while(leftArm.getSelectedSensorPosition(0) < pos)
    	    		{
    	    			goForwardL();
    	    			
    	    		}
    	    		while(rightArm.getSelectedSensorPosition(0) < pos)
    	    		{
    	    			goForwardR();
    	    		}
    	    	}
    	    	
    	    	if(getCurrentState() == startPos && pos == Constants.grabPositionR)
    	    	{
    	    		while(leftArm.getSelectedSensorPosition(0) < pos)
    	    		{
    	    			goForwardL();
    	    			
    	    		}
    	    		while(rightArm.getSelectedSensorPosition(0) < pos)
    	    		{
    	    			goForwardR();
    	    		}
    	    	}
    	    	
    	    	if(getCurrentState() == readyPos && pos == Constants.grabPositionR)
    	    	{
    	    		while(leftArm.getSelectedSensorPosition(0) < pos)
    	    		{
    	    			goForwardL();
    	    			
    	    		}
    	    		while(rightArm.getSelectedSensorPosition(0) < pos)
    	    		{
    	    			goForwardR();
    	    		}
    	    	}
    	    	
    	    	if(getCurrentState() == readyPos && pos == Constants.startPositionR)
    	    	{
    	    		while(leftArm.getSelectedSensorPosition(0) > pos)
    	    		{
    	    			goBackL();
    	    			
    	    		}
    	    		while(rightArm.getSelectedSensorPosition(0) > pos)
    	    		{
    	    			goBackR();
    	    		}
    	    	}
    	    	
    	    	if(getCurrentState() == grabPos && pos == Constants.readyPositionR)
    	    	{
    	    		while(leftArm.getSelectedSensorPosition(0) > pos)
    	    		{
    	    			goBackL();
    	    			
    	    		}
    	    		while(rightArm.getSelectedSensorPosition(0) > pos)
    	    		{
    	    			goBackR();
    	    		}
    	    	}
    	    	
    	    	if(getCurrentState() == grabPos && pos == Constants.startPositionR)
    	    	{
    	    		while(leftArm.getSelectedSensorPosition(0) > pos)
    	    		{
    	    			goBackL();
    	    			
    	    		}
    	    		while(rightArm.getSelectedSensorPosition(0) > pos)
    	    		{
    	    			goBackR();
    	    		}
    	    	}
    	    	
    	    }
    	    public static double getCurrentPosL()
    	    {
    	    	return leftArm.getSelectedSensorPosition(0);
    	    }
    	    public static double getCurrentPosR()
    	    {
    	    	return rightArm.getSelectedSensorPosition(0);
    	    }
    	    public static void goForwardR()
    	    {
    	    	rightArm.set(.5);
    	    }
    	    
    	    public static void goBackR()
    	    {
    	    	rightArm.set(-.5);
    	    }
    	    public static void goForwardL()
    	    {
    	    	leftArm.set(.5);
    	    }
    	    
    	    public static void goBackL()
    	    {
    	    	leftArm.set(-.5);
    	    }
    }


