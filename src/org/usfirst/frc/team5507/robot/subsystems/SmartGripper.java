package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Robot;
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
	public static final int DEGREES_OPEN = 60;
	public static final int DEGREES_CLOSED = 100;
	
	static final int CURRENT_LIMIT = 5;
	
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
		configGripperTalonL(leftArm);
		configGripperTalonR(rightArm);
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
		double ticks = angleToTicks(angle);
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
	
	public void configGripperTalonL(WPI_TalonSRX talon)
	{
		talon.configContinuousCurrentLimit(CURRENT_LIMIT, ConfigTalon.kTimeoutMs);
		talon.configPeakCurrentLimit(0, ConfigTalon.kTimeoutMs);
		talon.enableCurrentLimit(true);
		
		talon.configForwardSoftLimitThreshold(angleToTicks(DEGREES_CLOSED), ConfigTalon.kTimeoutMs);
		talon.configForwardSoftLimitEnable(true, ConfigTalon.kTimeoutMs);
		talon.configReverseSoftLimitThreshold(0, ConfigTalon.kTimeoutMs);
		talon.configReverseSoftLimitEnable(true, ConfigTalon.kTimeoutMs); // someone was right (no name)

	}
	
	public void configGripperTalonR(WPI_TalonSRX talon)
	{
		talon.configContinuousCurrentLimit(CURRENT_LIMIT, ConfigTalon.kTimeoutMs);
		talon.configPeakCurrentLimit(0, ConfigTalon.kTimeoutMs);
		talon.enableCurrentLimit(true);
		
		talon.configForwardSoftLimitThreshold(0, ConfigTalon.kTimeoutMs);
		talon.configForwardSoftLimitEnable(true, ConfigTalon.kTimeoutMs);
		talon.configReverseSoftLimitThreshold(-(angleToTicks((DEGREES_CLOSED))), ConfigTalon.kTimeoutMs);
		talon.configReverseSoftLimitEnable(true, ConfigTalon.kTimeoutMs); // someone was right (no name)

	}
	
	public static int angleToTicks(int degrees) {
		return (4096 * degrees) / 360;
	}
	
	public double ticksToAngle(double pos)
	{
		return (pos * 360) / 4096;
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
			leftArm.set(0.35);
		}
		else {
			leftArm.set(0);
		}
		
		if (getCurrentPosR() > targetPos * -1) {
			rightArm.set(-0.35);
		}
		else {
			rightArm.set(0);
		}
		//Jennessa was right twice
	}
	
	public void setDesiredAngleBackward(int angle) {
		double targetPos = (4096 * angle) / 360;
		if (getCurrentPosL() > targetPos) {
			leftArm.set(-0.35);
		}
		else {
			leftArm.set(0);
		}
		
		if (getCurrentPosR() < targetPos * -1) {
			rightArm.set(0.35);
		}
		else {
			rightArm.set(0);
		}
		//Jennessa was right btw
	}
	
	public void changeState(int x)
	{
		currentState += x;
	}
	
	public void moveLeft(double speed)
	{
		leftArm.set(speed);
	}
	
	public void moveRight(double speed)
	{
		rightArm.set(speed);
	}
	
	public void putExtraData() {
		SmartDashboard.putNumber("Right Arm Pos", ticksToAngle(this.getCurrentPosR()));
		SmartDashboard.putNumber("Left Arm Pos", ticksToAngle(this.getCurrentPosL()));
		SmartDashboard.putNumber("Gripper State", currentState);
		SmartDashboard.putNumber("Left Arm Output", leftArm.getOutputCurrent());
		SmartDashboard.putNumber("Right Arm Output", rightArm.getOutputCurrent());
		SmartDashboard.putNumber("Left Arm Voltage", leftArm.getMotorOutputVoltage());
		SmartDashboard.putNumber("Right Arm Voltage", rightArm.getMotorOutputVoltage());
	}
	
}