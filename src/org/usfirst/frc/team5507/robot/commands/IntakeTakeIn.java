package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

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
		Robot.m_intake.counterReset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.m_intake.reelIn();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.m_intake.isSwitchSet(); 
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.m_intake.stop();
		Scheduler.getInstance().add(new RumbleInTheJungleJuliaWasRight());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.m_intake.stop();
	}
}
