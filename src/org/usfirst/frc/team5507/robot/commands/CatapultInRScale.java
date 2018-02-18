package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.FieldHelper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CatapultInRScale extends CommandGroup {

	private int side;
	
    public CatapultInRScale(int s) {
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
    	if (side == FieldHelper.ROBOT_START_RIGHT)
    	{
    		addSequential(new DriveForwardDistance(-25));
    		addSequential(new DriveTurnByAngle(-90));
    		addSequential(new Catapult());
    	}
    	if (side == FieldHelper.ROBOT_START_LEFT)
    	{
    		addSequential(new DriveTurnByAngle(90));
    		addSequential(new DriveForwardDistance(-14));
    		addSequential(new DriveTurnByAngle(-90));
    		addSequential(new DriveForwardDistance(24));
    		addSequential(new DriveSidewaysDistance(3));
    		addSequential(new DriveTurnByAngle(-90));
    		addSequential(new Catapult());
    	}
    	if (side == FieldHelper.ROBOT_START_MIDDLE)
    	{
    		addSequential(new DriveSidewaysDistance(8));
    		addSequential(new DriveForwardDistance(-25));
    		addSequential(new DriveTurnByAngle(-90.0));
    		addSequential(new Catapult());
    	}
    	addSequential(new DriveStop());	
    }
}
