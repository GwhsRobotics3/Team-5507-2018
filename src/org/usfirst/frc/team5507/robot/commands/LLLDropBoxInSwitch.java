package org.usfirst.frc.team5507.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LLLDropBoxInSwitch extends CommandGroup {
	
	private String side; // input the position the robot is placed mauaully agaisnt the wall
	
    public LLLDropBoxInSwitch(String s) {
    	
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
    	
    	addSequential(new DriveForwardDistance(-5.0));
    	if (side == "left")
    	{
    		addSequential(new DriveForwardDistance(-9.0));
    		addSequential(new DriveTurnByAngle(90.0));
    		addSequential(new Catapult());
    	}
    	if(side == "right")
    	{
    		addSequential(new DriveTurnByAngle(-90.0));
    		addSequential(new DriveForwardDistance(13.0));
    		addSequential(new DriveTurnByAngle(90.0));
    		addSequential(new DriveForwardDistance(-9.0));
    		addSequential(new DriveTurnByAngle(90.0));
    		addSequential(new Catapult());
    	}
    	addSequential(new DriveStop());
    }
}
