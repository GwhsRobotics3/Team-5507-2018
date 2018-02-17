package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.RobotMap;
import org.usfirst.frc.team5507.robot.commands.ElevatorWithJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DumbElevator extends Subsystem {

	private WPI_TalonSRX elevatorPulley = new WPI_TalonSRX(RobotMap.elevatorPulley);
	private static DigitalInput limitSwitchT = new DigitalInput(RobotMap.elevatorLimitT);
	private static DigitalInput limitSwitchB = new DigitalInput(RobotMap.elevatorLimitB);
	private static double GRAVITY_WITHOUT_BOX = 0;
	private static double GRAVITY_WITH_BOX = 0;
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ElevatorWithJoystick());
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

	public boolean isSwitchSetT() {
		return limitSwitchT.get();    
	}

	public boolean isSwitchSetB() {
		return limitSwitchB.get();    
	}
	
	public double getVoltage() {
		return elevatorPulley.getMotorOutputVoltage(); 
	}
}

