package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.subsystems.SmartElevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SmartElevatorManualDrive extends Command {
	
	private int direction;
	
    public SmartElevatorManualDrive(int d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(Robot.m_smartElevator);
    	direction = d;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (direction > 0)
    	{
    		Robot.m_smartElevator.goUp();
    	}
    	else
    	{
    		Robot.m_smartElevator.goDown();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.m_smartElevator.stop();
    }
}
