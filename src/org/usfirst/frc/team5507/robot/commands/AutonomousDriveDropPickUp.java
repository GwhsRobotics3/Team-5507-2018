package org.usfirst.frc.team5507.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * drives to switch, drops pre-loaded box, picks up new box, goes to exchange
 */
public class AutonomousDriveDropPickUp extends CommandGroup {

    public AutonomousDriveDropPickUp() {
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
    	addSequential(new DriveForward(3.0));
    	addSequential(new DriveTurnByAngle(-90));
    	addParallel(new DriveForward(3.0));
    	addSequential(new IntakeTakeOut());
    	addParallel(new SmartElevatorMove(1));
    	addSequential(new IntakeTakeOut());
    	addParallel(new SmartElevatorMove(-1));
    //	addParallel(new DriveSideways(3.0, 0.8));
    	addSequential(new DriveForward(1.0));
    	addSequential(new DriveTurnByAngle(-90));
    	addSequential(new DriveForward(1.0));
    	addSequential(new SmartGripperToggle());
    	addSequential(new IntakeTakeIn());
    	addSequential(new SmartGripperToggle());
   // 	addSequential(new DriveSideways(2.0, -0.8));
    	addSequential(new DriveForward(4.0));
    //	addSequential(new DriveSideways(4.0, 0.8));
    	addSequential(new DriveForward(1.0));
        addSequential(new IntakeTakeOut());
        addSequential(new DriveStop());
    }
}
