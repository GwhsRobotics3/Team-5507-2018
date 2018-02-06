package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.OI;
import org.usfirst.frc.team5507.robot.Robot;
//import org.usfirst.frc.team5507.robot.XboxController;
import org.usfirst.frc.team5507.robot.subsystems.SmartGripper;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestPrint extends Command {

    public TestPrint() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_smartGripper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    
    //	Robot.m_oi.controller.setRumble(1, 1);
    	//Robot.m_climber.testPrint();
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
