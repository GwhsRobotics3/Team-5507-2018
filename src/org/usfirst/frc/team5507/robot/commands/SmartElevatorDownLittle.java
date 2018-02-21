package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SmartElevatorDownLittle extends Command {
	private Timer timer = new Timer();
	private double time;
    public SmartElevatorDownLittle(double t) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_smartElevator);
    	time = t;
    }
 
    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.m_smartElevator.goDown();
    	System.out.println("ajgdjas");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return timer.get() > time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
