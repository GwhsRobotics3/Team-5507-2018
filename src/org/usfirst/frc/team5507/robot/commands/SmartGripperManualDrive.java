package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SmartGripperManualDrive extends Command {
	
	private int side;
	private int direction;
	
	public static final int RIGHT_ARM = 1;
	public static final int ARM_OUT = 1;
	
    public SmartGripperManualDrive(int s, int d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_smartGripper);
    	side = s;
    	direction = d;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(side == RIGHT_ARM)
    	{
    		if(direction == ARM_OUT)
    		{
    			Robot.m_smartGripper.moveRight(-0.5);
    		}
    		else
    		{
    			Robot.m_smartGripper.moveRight(0.5);
    		}
    	}
    	else 
    	{
    		if(direction == ARM_OUT)
    		{
    			Robot.m_smartGripper.moveLeft(0.5);
    		}
    		else
    		{
    			Robot.m_smartGripper.moveLeft(-0.5);
    		}
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
    }
}
