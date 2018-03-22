package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.subsystems.SmartGripper;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeft extends CommandGroup {

    public AutoLeft() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	if(gameData.charAt(1) == 'L') {
    		addSequential(new DriveForwardDistance(-1));
    		addSequential(new DriveForwardDistance(-25));
    		addSequential(new DriveTurnByAngle(90));
    		addSequential(new DriveForwardDistance(2));
    		addSequential(new Catapult());
    		//new code
    		//picking up another cube
    		addSequential(new DriveTurnByAngle(-90));
    		addSequential(new SmartGripperSetState(SmartGripper.STATE_OPEN));
    		addSequential(new DriveForwardDistance(5)); 
    		addSequential(new DriveSidewaysDistance(-1.7));
    		addSequential(new DriveForwardDistance(3.5));
    		addSequential(new SmartGripperIntakeGrabCube());
    	}
    	else if(gameData.charAt(0) == 'L') {
    		addSequential(new DriveForwardDistance(-14.0));
			addSequential(new DriveTurnByAngle(90.0));
			addSequential(new DriveForwardDistance(-1.5));
			addSequential(new Catapult());
			//new code
			//picking up another cube
			addSequential(new DriveTurnByAngle(-90));
			addSequential(new DriveForwardDistance(5));
			addSequential(new DriveSidewaysDistance(-2.5));
			addSequential(new SmartGripperSetState(SmartGripper.STATE_OPEN));
			addSequential(new DriveForwardDistance(2.5));
    		addSequential(new SmartGripperIntakeGrabCube());
    	}
    	else {
    		addSequential(new DriveForwardDistance(-12.0));
        	addSequential(new DriveStop());
    	}
    }
}
