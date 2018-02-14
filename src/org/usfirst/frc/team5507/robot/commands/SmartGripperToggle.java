package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SmartGripperToggle extends InstantCommand {

    public SmartGripperToggle() {
    	super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_smartGripper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	int newState = Robot.m_smartGripper.getToggledState();
    	System.out.println(Robot.m_smartGripper.getCurrentState());
    	Robot.m_smartGripper.setState(newState);
    }
    
}
