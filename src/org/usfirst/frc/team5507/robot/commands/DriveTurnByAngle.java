package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurnByAngle extends Command {

	private double angle;
	
    public DriveTurnByAngle(double a) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_driveTrain);
    	angle = a;
    }

    // Called just before this Command runs the first time
    protected void initialize() { 	
    	Robot.m_ahrs.zeroYaw();
    	System.out.println("zero");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angleError = (angle - Robot.m_ahrs.getYaw());
    	if (angleError > 0)
    	{
    		Robot.m_driveTrain.drive(0, 0, 0.3);
    	}
    	else if(angleError < 0)
    	{
    		Robot.m_driveTrain.drive(0, 0,-0.3);
    		System.out.println("turning left");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double angleError = (angle - Robot.m_ahrs.getYaw());
    	if(Math.abs(angleError) < 2)
    	{
    		System.out.println("adam");
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
