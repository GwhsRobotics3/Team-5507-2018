package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.subsystems.SmartGripper;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SmartGripperJankyMove extends Command {

	private int newState;
	private int currentState;

	public SmartGripperJankyMove() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.m_smartGripper);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		currentState = Robot.m_smartGripper.getCurrentState();
		newState = Robot.m_smartGripper.getToggledState();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(newState == SmartGripper.STATE_OPEN)
		{
			Robot.m_smartGripper.setDesiredAngleForward(20);
		}
		if(newState == SmartGripper.STATE_CLOSED) 
		{
			Robot.m_smartGripper.setDesiredAngleBackward(40);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(newState == SmartGripper.STATE_OPEN)
		{
			if(currentState == SmartGripper.STATE_START)
			{
				Robot.m_smartGripper.changeState(1);
				return Robot.m_smartGripper.getCurrentPosL() > SmartGripper.angleToTicks(20);
			}
			if(currentState == SmartGripper.STATE_CLOSED)
			{
				Robot.m_smartGripper.changeState(-1);
				return Robot.m_smartGripper.getCurrentPosL() < SmartGripper.angleToTicks(20);
			}
		}
		if(newState == SmartGripper.STATE_CLOSED)
		{
			Robot.m_smartGripper.changeState(1);
			return Robot.m_smartGripper.getCurrentPosL() > SmartGripper.angleToTicks(40);
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
