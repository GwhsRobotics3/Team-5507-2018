package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class SmartElevatorToggle extends InstantCommand {

    public SmartElevatorToggle() {
        super();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.m_smartElevator);
    }

    // Called once when the command executes
    protected void initialize() {
    	Robot.m_smartElevator.setState(Robot.m_smartElevator.getToggledState());
    }

}
