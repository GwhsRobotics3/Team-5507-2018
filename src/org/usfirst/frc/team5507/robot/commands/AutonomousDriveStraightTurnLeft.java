package org.usfirst.frc.team5507.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * drives straight, turns left, drop
 */
public class AutonomousDriveStraightTurnLeft extends CommandGroup {

    public AutonomousDriveStraightTurnLeft() {
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
    	addSequential(new DriveForward(1.0));
    	addSequential(new DriveTurnByAngle(-90));
    	addParallel(new DriveForward(1.0));
    	addParallel(new ElevatorUp());
    	addSequential(new IntakeTakeOut());
    	addSequential(new ElevatorDown());
    	addSequential(new DriveStop());
    }
}
