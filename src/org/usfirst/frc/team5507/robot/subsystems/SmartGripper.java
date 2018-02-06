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

	private static WPI_TalonSRX leftArm = new WPI_TalonSRX(RobotMap.leftArm);
	private static WPI_TalonSRX rightArm = new WPI_TalonSRX(RobotMap.rightArm);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public SmartGripper()
	{
		currentState = STATE_START;
		Constants.configTalon(leftArm);
		Constants.configTalon(rightArm);
		resetEncoders();
	}
	
	

	public static void resetEncoders() {
		leftArm.setSelectedSensorPosition(0, 0, 0);
		rightArm.setSelectedSensorPosition(0, 0, 0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		SmartDashboard.putNumber("Right arm position", rightArm.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Left arm position", leftArm.getSelectedSensorPosition(0));
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
				setTargetAngles(Constants.TICKS_START);
			
				break;
			case(STATE_OPEN):
				setTargetAngles(Constants.TICKS_OPEN);
			
				break;
			case(STATE_CLOSED):
				setTargetAngles(Constants.TICKS_CLOSED);
			
				break;
			default:
				setTargetAngles(Constants.TICKS_OPEN);
				
				break;

		}
	}

	private void setTargetAngles(double ticks) {	
		leftArm.set(ControlMode.MotionMagic, ticks);
		rightArm.set(ControlMode.MotionMagic, -ticks); // "negative" angle to drive opposite direction
	}

	public void stop()
	{
		leftArm.set(0);
		rightArm.set(0);
	}

}