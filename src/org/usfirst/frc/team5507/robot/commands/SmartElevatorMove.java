package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.subsystems.SmartElevator;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SmartElevatorMove extends InstantCommand {
	
	int d;
	
	public SmartElevatorMove(int direction) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.m_smartElevator);
		int d = direction;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		int newState = 0;
		if(d > 0) 
		{
			newState = SmartElevator.getNextStateUp();			
		}
		else
		{
			newState = SmartElevator.getNextStateDown();
		}
		Robot.m_smartElevator.setState(newState);
	}
}
