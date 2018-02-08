package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Constants;
import org.usfirst.frc.team5507.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartGripper extends Subsystem {
	
	private static int currentState;
		
	public static final int STATE_START = 1;
	public static final int STATE_OPEN = 2;
	public static final int STATE_CLOSED = 3;
	
	public static final int DEGREES_START = 0;
	public static final int DEGREES_OPEN = 158;
	public static final int DEGREES_CLOSED = 175;
	
	static final int CURRENT_LIMIT = 7;
	
	private static WPI_TalonSRX leftArm = new WPI_TalonSRX(RobotMap.leftArm);
	private static WPI_TalonSRX rightArm = new WPI_TalonSRX(RobotMap.rightArm);
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public SmartGripper()
	{
		currentState = STATE_START;
		Constants.configTalon(leftArm);
		Constants.configTalon(rightArm);
		leftArm.configForwardSoftLimitThreshold(DEGREES_START, Constants.kTimeoutMs);
		leftArm.configReverseSoftLimitThreshold(DEGREES_OPEN, Constants.kTimeoutMs);
		rightArm.configForwardSoftLimitThreshold(-DEGREES_START, Constants.kTimeoutMs);
		rightArm.configReverseSoftLimitThreshold(-DEGREES_OPEN, Constants.kTimeoutMs);
		configGripperTalon(leftArm);
		configGripperTalon(rightArm);
		resetEncoders();
		
		addChild("left arm", leftArm);
		addChild("right arm", rightArm);
	}
	
	public static void resetEncoders() {
		leftArm.setSelectedSensorPosition(0, 0, 0);
		rightArm.setSelectedSensorPosition(0, 0, 0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public double getCurrentPosL() {
		return leftArm.getSelectedSensorPosition(0);
	}
		
	public double getCurrentPosR() {
		return rightArm.getSelectedSensorPosition(0);
	}
	
	public int getToggledState()
	{
		switch (currentState)
		{
			case(STATE_START):
				return STATE_OPEN;

			case(STATE_OPEN):
				return STATE_CLOSED;

			case(STATE_CLOSED):
				return STATE_OPEN;

			default:
				return STATE_OPEN;
		}
	}
	
	public void setState(int s)
	{
		if(s < STATE_START || s > STATE_CLOSED) {
			s = STATE_OPEN;
			}
			
		currentState = s;
		
		switch(currentState)
		{
			case(STATE_START):
				setTargetAngles(DEGREES_START);
				break;
				
			case(STATE_OPEN):
				setTargetAngles(DEGREES_OPEN);
				break;
				
			case(STATE_CLOSED):
				setTargetAngles(DEGREES_CLOSED);
				break;
				
			default:
				setTargetAngles(DEGREES_OPEN);
				break;
		}
	}
	
	private void setTargetAngles(int angle) {	
		leftArm.set(ControlMode.MotionMagic, angleToTicks(angle));
		rightArm.set(ControlMode.MotionMagic, -angleToTicks(angle)); // "negative" angle to drive opposite direction
	}
	
	public void stop()
	{
		leftArm.set(0);
		rightArm.set(0);
	}
	
	public void configGripperTalon(WPI_TalonSRX talon)
	{
		talon.configContinuousCurrentLimit(CURRENT_LIMIT, Constants.kTimeoutMs);
		talon.configPeakCurrentLimit(0, Constants.kTimeoutMs);
		talon.configForwardSoftLimitEnable(true, Constants.kTimeoutMs);
		talon.configReverseSoftLimitEnable(true, Constants.kTimeoutMs);
		talon.enableCurrentLimit(true);
	}
	
	public int angleToTicks(int degrees) {
		return (4096*degrees)/360;
	}
}