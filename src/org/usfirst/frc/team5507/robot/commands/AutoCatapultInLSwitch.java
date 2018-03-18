package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.FieldHelper;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCatapultInLSwitch extends CommandGroup {
	
	private int side;

    public AutoCatapultInLSwitch(int s) {
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

    	//addSequential(new AutonomousWait(3));
    	if (side == FieldHelper.ROBOT_START_LEFT)
    	{
    		addSequential(new DriveForwardDistance(-14.0));
    		addSequential(new DriveTurnByAngle(90.0)); //+90 
    		addSequential(new Catapult());
    	}
    	if(side == FieldHelper.ROBOT_START_RIGHT)
    	{
    		addSequential(new DriveForwardDistance(-5.0));
    		addSequential(new DriveTurnByAngle(-90.0));
    		addSequential(new DriveForwardDistance(14.0));
    		addSequential(new DriveTurnByAngle(90.0));
    		addSequential(new DriveForwardDistance(-7.0));
    		addSequential(new DriveTurnByAngle(90.0));
    		addSequential(new DriveForwardDistance(2));
    		addSequential(new Catapult());
    	}
    	if (side == FieldHelper.ROBOT_START_MIDDLE)
		{
    		addSequential(new DriveForwardDistance(-1));
    		addSequential(new DriveTurnByAngle(-45));
			addSequential(new DriveForwardDistance(-5.5));
			addSequential(new DriveTurnByAngle(45));
			addSequential(new DriveForward(2, -0.4));
			addSequential(new Catapult());
		}
    	addSequential(new DriveStop());
    }
}

