package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeTakeIn extends Command {

	public IntakeTakeIn() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.m_intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.m_timer.reset();
		Robot.m_timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.m_intake.reelIn();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.m_intake.isSwitchSet()) 
		{
			return true;
		} 
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.m_intake.stop();
		if (Robot.m_timer.get() < 1)
		{
			Robot.m_oi.controller.setRumble(RumbleType.kLeftRumble, 1);
			Robot.m_oi.controller.setRumble(RumbleType.kRightRumble, 1);
		}
		Robot.m_oi.controller.setRumble(RumbleType.kLeftRumble, 0);
		Robot.m_oi.controller.setRumble(RumbleType.kRightRumble, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
