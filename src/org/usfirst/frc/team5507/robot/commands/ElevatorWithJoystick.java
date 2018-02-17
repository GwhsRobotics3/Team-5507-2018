package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.OI;
import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevatorWithJoystick extends Command {

    public ElevatorWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_dumbElevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_dumbElevator.drive(Robot.m_oi.controller.getRawAxis(OI.ELEVATOR_AXIS));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (false);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
