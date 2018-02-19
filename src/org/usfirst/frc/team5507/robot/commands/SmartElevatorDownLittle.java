package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SmartElevatorDownLittle extends Command {
 
	private Timer x;
    public SmartElevatorDownLittle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_smartElevator);   	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	x.reset();
    	x.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(x.get() < .1)
    	{
    		Robot.m_smartElevator.goDown();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return x.get() > .1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_smartElevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
