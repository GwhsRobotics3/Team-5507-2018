package org.usfirst.frc.team5507.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoMiddle extends CommandGroup {

    public AutoMiddle() {
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
    	if(gameData.charAt(0) == 'L')
    	{
    		addSequential(new DriveForwardDistance(-1));
    		addSequential(new DriveTurnByAngle(-45));
			addSequential(new DriveForwardDistance(-5.5));
			addSequential(new DriveTurnByAngle(45));
			addSequential(new DriveForward(2, -0.4));
			addSequential(new Catapult());
    	}
    	else 
    	{
    		addSequential(new DriveForwardDistance(-2));
			addSequential(new DriveTurnByAngle(45));
			addSequential(new DriveForwardDistance(-4.5));
			addSequential(new DriveTurnByAngle(-45));
			addSequential(new DriveForward(2, -0.4));
			addSequential(new Catapult());
    	}
    }
}
