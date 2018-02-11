package org.usfirst.frc.team5507.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LLLDroppingInScale extends CommandGroup {

	private String side;
	
    public LLLDroppingInScale(String s) {
    	
    	side = s;
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
    	
    	addSequential(new DriveForwardDistance(-1));
    	if (side == "left")
    	{
    		addSequential(new DriveForwardDistance(-25));
    		addSequential(new DriveTurnByAngle(90));
    		addSequential(new Catapult());
    	}
    	else if(side == "right")
    	{
    		addSequential(new DriveTurnByAngle(-90));
    		addSequential(new DriveForwardDistance(-14));
    		addSequential(new DriveTurnByAngle(90));
    		addSequential(new DriveForwardDistance(24));
    		addSequential(new DriveSidewaysDistance(2));
    		addSequential(new DriveTurnByAngle(-90));
    		addSequential(new Catapult());
    	}
    	addSequential(new DriveStop());	
    }
}
