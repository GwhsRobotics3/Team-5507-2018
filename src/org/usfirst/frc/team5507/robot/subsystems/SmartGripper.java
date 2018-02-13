package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SmartGripper extends Subsystem {
	
	private static int currentState;
		
	public static final int STATE_START = 1;
	public static final int STATE_OPEN = 2;
	public static final int STATE_CLOSED = 3;
	
	public static final int DEGREES_START = 0;
	public static final int DEGREES_OPEN = 90;
	public static final int DEGREES_CLOSED = 180;
	
	static final int CURRENT_LIMIT = 30;
	
	public static WPI_TalonSRX leftArm = new WPI_TalonSRX(RobotMap.leftArm);
	public static WPI_TalonSRX rightArm = new WPI_TalonSRX(RobotMap.rightArm);
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public SmartGripper()
	{
		currentState = STATE_START;
		ConfigTalon.configTalon(leftArm);
		ConfigTalon.configTalon(rightArm);
		/*leftArm.configForwardSoftLimitThreshold(DEGREES_START, ConfigTalon.kTimeoutMs);
		leftArm.configReverseSoftLimitThreshold(DEGREES_OPEN, ConfigTalon.kTimeoutMs);
		rightArm.configForwardSoftLimitThreshold(-DEGREES_START, ConfigTalon.kTimeoutMs);
		rightArm.configReverseSoftLimitThreshold(-DEGREES_OPEN, ConfigTalon.kTimeoutMs);*/
		configGripperTalon(leftArm);
		configGripperTalon(rightArm);
		resetEncoders();
		
		addChild("left arm", leftArm);
		addChild("right arm", rightArm);
		
		//leftArm.getMotorOutputVoltage();
		//rightArm.getMotorOutputVoltage();
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
			System.out.println("Start" + " Degrees " + angleToTicks(DEGREES_START));
				break;
				
			case(STATE_OPEN):
				setTargetAngles(DEGREES_OPEN);
			System.out.println("open" + " Degrees " + angleToTicks(DEGREES_OPEN));
				break;
				
			case(STATE_CLOSED):
				setTargetAngles(DEGREES_CLOSED);
			System.out.println("Closed" + " Degrees " + angleToTicks(DEGREES_CLOSED));
				break;
				
			default:
				setTargetAngles(DEGREES_OPEN);
				System.out.println("Open default" + " Degrees " + angleToTicks(DEGREES_OPEN));
				break;
		}
	}
	
	private void setTargetAngles(int angle) {	
		double ticks = (4096 * angle)/ 360;
		leftArm.set(ControlMode.MotionMagic, ticks);
		rightArm.set(ControlMode.MotionMagic, -1 * ticks); // "negative" angle to drive opposite direction
	}
	
	public void stop()
	{
		leftArm.set(0);
		rightArm.set(0);
	}
	
	public int getCurrentState()
	{
		return currentState;
	}
	
	public void configGripperTalon(WPI_TalonSRX talon)
	{
		/*talon.configContinuousCurrentLimit(CURRENT_LIMIT, ConfigTalon.kTimeoutMs);
		talon.configPeakCurrentLimit(0, ConfigTalon.kTimeoutMs);
		talon.configForwardSoftLimitEnable(true, ConfigTalon.kTimeoutMs);
		talon.configReverseSoftLimitEnable(true, ConfigTalon.kTimeoutMs);
		talon.enableCurrentLimit(true);*/
	}
	
	public int angleToTicks(int degrees) {
		return (4096 * degrees) / 360;
	}
	
	public void gripperUseJoystick() {
		leftArm.set(Robot.m_oi.controller.getRawAxis(1));
		rightArm.set(Robot.m_oi.controller.getRawAxis(1) * -1);
	}
	
	public void stopAllJoy() {
		leftArm.set(0);
		rightArm.set(0);
	}
	
	public void setDesiredAngleForward(int angle) {
		double targetPos = (4096 * angle) / 360;
		if (getCurrentPosL() < targetPos) {
			leftArm.set(0.4);
		}
		else {
			leftArm.set(0);
		}
		
		if (getCurrentPosR() > targetPos * -1) {
			rightArm.set(-0.4);
		}
		else {
			rightArm.set(0);
		}
		currentState++;
		//Jennessa was right twice
	}
	
	public void setDesiredAngleBackward(int angle) {
		double targetPos = (4096 * angle) / 360;
		if (getCurrentPosL() > targetPos) {
			leftArm.set(-0.4);
		}
		else {
			leftArm.set(0);
		}
		
		if (getCurrentPosR() < targetPos * -1) {
			rightArm.set(0.4);
		}
		else {
			rightArm.set(0);
		}
		currentState--;
		//Jennessa was right btw
	}

}