package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStrafe extends Command {

	private double startAngle;
	private double speed;
	
    public DriveStrafe(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_driveTrain);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startAngle = Robot.m_ahrs.getYaw();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_driveTrain.driveSideways(speed, startAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_driveTrain.drive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.m_driveTrain.drive(0, 0, 0);
    }
}
