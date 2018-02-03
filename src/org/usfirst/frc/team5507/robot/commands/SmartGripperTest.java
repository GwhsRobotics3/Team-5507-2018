package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.subsystems.SmartGripper;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartGripperTest extends Command {

	private double x;
    public SmartGripperTest(double pos) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_smartGripper);
    	x = pos;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartGripper.setDesiredPosition(10);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartGripper.setDesiredPosition(x);
    	SmartDashboard.putNumber("Gripper position", SmartGripper.getCurrentPosL());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double y = SmartGripper.getCurrentPosL();
        return y > x;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
