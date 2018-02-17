package org.usfirst.frc.team5507.robot.subsystems;


import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.RobotMap;
import org.usfirst.frc.team5507.robot.commands.ElevatorWithJoystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartElevator extends Subsystem {
	private static WPI_TalonSRX elevatorPulley = new WPI_TalonSRX(RobotMap.elevatorPulley);
	private static int currentState;
	
	private static final double GRAVITY_WITHOUT_BOX = 0;
	private static final double GRAVITY_WITH_BOX = 0;
	public static final int STATE_HIGH = 3;
	public static final int STATE_MED = 2;
	public static final int STATE_LOW = 1;

	public static final double ELEVATOR_HIGH = 24;
	public static final double ELEVATOR_MED = 12;
	public static final double ELEVATOR_LOW = 3;

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
		setDefaultCommand(new ElevatorWithJoystick());
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
			setDesiredPosInches(ELEVATOR_LOW);					
		break;

		case(STATE_MED):
			setDesiredPosInches(ELEVATOR_MED);
		break;

		case(STATE_HIGH):
			setDesiredPosInches(ELEVATOR_HIGH);
		break;

		default:
			setDesiredPosInches(ELEVATOR_LOW);
			break;
		}
	}

	public static void setDesiredPosInches(double inches)
	{
		double ticks = ((768 / (Math.PI * 1.5)) * inches); 
		elevatorPulley.set(ControlMode.MotionMagic, ticks);
		System.out.println(ticks);
	}

	//public static void setDesiredPosTicks(double ticks)
	//{
	//	elevatorPulley.set(ControlMode.MotionMagic, ticks);
	//}

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

	public static void stateReset()
	{
		currentState = STATE_LOW;
	}

	public double getCurrentPos()
	{
		return elevatorPulley.getSelectedSensorPosition(0);
	}
	
	public void goUp()
	{
		elevatorPulley.set(0.5);
	}
	
	public void goDown()
	{
		elevatorPulley.set(-0.5);
	}

	public void stop()
	{
		elevatorPulley.set(0);
	}
	
	public double ticksToAngle(double ticks)
	{
		 return (ticks * 360) / 768;
	}
	
	public boolean isSwitchSetBot() {
		return elevatorPulley.getSensorCollection().isFwdLimitSwitchClosed();
	}
	
	public boolean isSwitchSetTop() {
		return elevatorPulley.getSensorCollection().isRevLimitSwitchClosed();   
	}

	public void putExtraData()
	{
		SmartDashboard.putNumber("Elevator pos", ticksToAngle(getCurrentPos()));
		SmartDashboard.putNumber("Elevator State", getCurrentState());
		SmartDashboard.putBoolean("Elevator top limit switch", isSwitchSetTop());
		SmartDashboard.putBoolean("Elevator bottom limit switch", isSwitchSetBot());
		SmartDashboard.putNumber("Elevator Voltage", elevatorPulley.getMotorOutputVoltage());
		SmartDashboard.putNumber("Elevator Current", elevatorPulley.getOutputCurrent());
	}

	public void drive(double desiredSpeed) {
		double outputSpeed = 0;
		if(Robot.m_smartGripper.getCurrentState() == SmartGripper.STATE_OPEN) {
			outputSpeed = GRAVITY_WITHOUT_BOX + desiredSpeed;
		}
		if(Robot.m_smartGripper.getCurrentState() == SmartGripper.STATE_CLOSED) {
			outputSpeed = GRAVITY_WITH_BOX + desiredSpeed;
		}
		elevatorPulley.set(outputSpeed);
	}
}

