package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RumbleInTheJungleJuliaWasRight extends Command {
	Timer rumbleTime;
	
    public RumbleInTheJungleJuliaWasRight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	rumbleTime = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	rumbleTime.reset();
    	rumbleTime.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if (rumbleTime.get() < 1)
		{
			Robot.m_oi.controller.setRumble(RumbleType.kLeftRumble, 1);
			Robot.m_oi.controller.setRumble(RumbleType.kRightRumble, 1);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (rumbleTime.get() > 1);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_oi.controller.setRumble(RumbleType.kLeftRumble, 0);
    	Robot.m_oi.controller.setRumble(RumbleType.kRightRumble, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.m_oi.controller.setRumble(RumbleType.kLeftRumble, 0);
    	Robot.m_oi.controller.setRumble(RumbleType.kRightRumble, 0);
    }
}
