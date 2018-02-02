package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.subsystems.SmartElevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SmartElevatorUp extends Command {

    public SmartElevatorUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_smartElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.m_smartElevator.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_smartElevator.getPositionForState(Robot.m_smartElevator.setNextStateUp());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.m_smartElevator.isSwitchSetTop();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_smartElevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
