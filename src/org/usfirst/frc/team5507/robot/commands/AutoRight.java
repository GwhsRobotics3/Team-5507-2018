package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.subsystems.SmartGripper;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRight extends CommandGroup {

    public AutoRight() {
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
    	
    	// If Sacle is ours we go for Scale
    	//if Scale isnt ours && Switch is ours we go for Switch
    	String gameData = DriverStation.getInstance().getGameSpecificMessage();
    	if(gameData.charAt(1) == 'R') {
    		addSequential(new DriveForwardDistance(-24));
    		addSequential(new DriveTurnByAngle(-90));
    		addParallel(new DriveForwardDistance(-0.5));
    		addParallel(new Catapult());
    		//new code
    		//picking up another cube
    		addSequential(new DriveTurnByAngle(90));
    		addSequential(new SmartGripperSetState(SmartGripper.STATE_OPEN));
    		addSequential(new DriveForwardDistance(3.5)); //was 5
    		addSequential(new DriveSidewaysDistance(3));
    		addSequential(new DriveForwardDistance(3.5));
    		addSequential(new SmartGripperIntakeGrabCube());
    	}
    	else if ( gameData.charAt(0) == 'R') {
    		addSequential(new DriveForwardDistance(-11.0));
			addSequential(new DriveTurnByAngle(-90.0));
			addSequential(new DriveForward(1.0, -1.0));
			addSequential(new Catapult());
			//new code
			//picking up another cube
			addSequential(new DriveTurnByAngle(90));
			addSequential(new DriveForwardDistance(-5));
			addSequential(new DriveSidewaysDistance(4));
			addSequential(new SmartGripperSetState(SmartGripper.STATE_OPEN));
			addSequential(new DriveForwardDistance(2));
    		addSequential(new SmartGripperIntakeGrabCube());
    	}
    	else {
    		addSequential(new DriveForwardDistance(-12.0));
        	addSequential(new DriveStop());
    	}
    }
}
