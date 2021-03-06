package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {

	private Timer m_timer = new Timer(); //subjected to change with encoders
	private double time;
	private double speed;
	
    public DriveForward(double t, double s) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_driveTrain);   
    	time = t;
    	speed = s;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	m_timer.reset();
    	m_timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_driveTrain.drive(0, speed, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (m_timer.get() > time)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
      }
    

    // Called once after isFinished returns true
    protected void end() {
    //	Robot.m_oi.controller.setRumble(0.4,0.4);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
