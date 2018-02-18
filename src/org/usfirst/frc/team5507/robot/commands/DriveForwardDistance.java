package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveForwardDistance extends Command {
	
	private double d1;
	private double startAngle;
	public static final double WHEEL_CIRCUMFERENCE = 18.84956;
	public static final double TICKS_PER_REVOLUTION = 4096;
	public static final double DISTANCE = TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE; //1 inch moved
	
    public DriveForwardDistance(double d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	d1 = DISTANCE * d * 12 ;
    	requires(Robot.m_driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetPos();
    	startAngle = Robot.m_ahrs.getYaw();
    }
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_driveTrain.driveForward(d1, startAngle);
    	System.out.println("Adam rolling out!");
    	SmartDashboard.putNumber("Auto Target position Forward", d1);
    	SmartDashboard.putNumber("Auto Starting Angle", startAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double errorPos = d1 - DriveTrain.getCurrentPos();
    	if(Math.abs(errorPos) < DISTANCE)
    	{
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.resetPos();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
