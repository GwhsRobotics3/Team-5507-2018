package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Catapult extends Command {
	
	public static Timer throwTimer = new Timer();
	private boolean done = false;
	
    public Catapult() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	throwTimer.reset();
    	throwTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(throwTimer.get() < 0.75)
    	{
	    	Robot.m_climber.armUp(0.8);
    	}
    	else if(throwTimer.get() < 1)
    	{
    		Robot.m_climber.armUp(-0.2);
    	}
    	else
    	{
    		done = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
