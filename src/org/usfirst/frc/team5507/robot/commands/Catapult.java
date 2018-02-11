package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Catapult extends Command {
	
	public static Timer throwTimer = new Timer();
	
    public Catapult() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	throwTimer.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(throwTimer.get() < 0.5)
    	{
	    	Robot.m_climber.catapult(0.3);
    	}
    	else if(throwTimer.get() > 0.5 && throwTimer.get() < .8)
    	{
    		Robot.m_climber.catapult(0.5);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(throwTimer.get() > 0.9)
    	{
    		return true;
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
