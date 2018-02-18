package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.subsystems.SmartGripper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SmartGripperIntakeGrabCube extends CommandGroup {

    public SmartGripperIntakeGrabCube() {    	
    	addParallel(new SmartGripperSetState(SmartGripper.STATE_CLOSED));
    	addSequential(new IntakeTakeIn());
    }
}
