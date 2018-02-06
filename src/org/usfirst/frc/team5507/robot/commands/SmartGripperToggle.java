package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SmartGripperToggle extends InstantCommand {

	public SmartGripperToggle() {
		super();
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.m_smartGripper);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.m_smartGripper.setState(Robot.m_smartGripper.getToggledState());
	}
}
