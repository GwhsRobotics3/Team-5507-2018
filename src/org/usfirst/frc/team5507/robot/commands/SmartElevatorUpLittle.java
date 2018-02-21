package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SmartElevatorUpLittle extends Command {
	
	private Timer x = new Timer();
	private double time;
    public SmartElevatorUpLittle(double t) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_smartElevator);
    	time = t;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	x.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.m_smartElevator.goUp();
    	System.out.println("kguasd");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return x.get() > time;
    }

    // Called once after isFinished returns true
    protected void end() {
    	x.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
