/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot;

import org.usfirst.frc.team5507.robot.commands.Catapult;

import org.usfirst.frc.team5507.robot.commands.ClimberRewind;
import org.usfirst.frc.team5507.robot.commands.ClimberUp;
import org.usfirst.frc.team5507.robot.commands.IntakeTakeIn;
import org.usfirst.frc.team5507.robot.commands.IntakeTakeOut;
import org.usfirst.frc.team5507.robot.commands.RumbleInTheJungleJuliaWasRight;
import org.usfirst.frc.team5507.robot.commands.SmartElevatorGoToBottom;
import org.usfirst.frc.team5507.robot.commands.SmartElevatorGoToTop;
import org.usfirst.frc.team5507.robot.commands.SmartElevatorManualDrive;
import org.usfirst.frc.team5507.robot.commands.SmartElevatorMove;
import org.usfirst.frc.team5507.robot.commands.SmartGripperIntakeGrabCube;
import org.usfirst.frc.team5507.robot.commands.SmartGripperJankyMove;
import org.usfirst.frc.team5507.robot.commands.SmartGripperManualDrive;
//import org.usfirst.frc.team5507.robot.commands.SmartGripperJankyMove;
import org.usfirst.frc.team5507.robot.commands.SmartGripperSetState;
import org.usfirst.frc.team5507.robot.commands.SmartGripperToggle;
import org.usfirst.frc.team5507.robot.subsystems.SmartGripper;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick controller = new Joystick(port);
	// Button button = new JoystickButton(controller, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	public XboxController controller;
	// cool beans

	public static final int ELEVATOR_AXIS = 5;
	
	// Allow easily toggling between multiple control schemes.
	public static final int CONTROL_SCHEME_DEFAULT = 1;
	public static final int CONTROL_SCHEME_COMPETITION = 2;
	public static final int CONTROL_SCHEME_TESTING = 3;
	
	// Set the control scheme to be used
	public int control_scheme = CONTROL_SCHEME_COMPETITION;
	
	public OI()
	{
		controller = new XboxController(0);
		
		Button a = new JoystickButton(controller, 1); //take in
		Button b = new JoystickButton(controller, 2); // take out
		Button x = new JoystickButton(controller, 3); // toggle gripper position
		Button y = new JoystickButton(controller, 4); // retract climber chain
		Button backLeft = new JoystickButton(controller,5); //elevator up
		Button backRight = new JoystickButton(controller, 6);//elevator down
		Button select = new JoystickButton(controller, 7); 
		Button start = new JoystickButton(controller, 8);  
		Button leftJoy = new JoystickButton(controller, 9);
		Button rightJoy = new JoystickButton(controller, 10);
		Button dpadDown = new DpadButton(controller, DpadButton.DPAD_DOWN);
		Button dpadUp = new DpadButton(controller, DpadButton.DPAD_UP);

		switch(control_scheme) {			
			case CONTROL_SCHEME_COMPETITION:
				// Greg's proposed controller mappings
				a.whileHeld(new IntakeTakeIn());
				b.whileHeld(new IntakeTakeOut());
				x.whileHeld(new SmartGripperIntakeGrabCube());
				y.whenPressed(new SmartGripperSetState(SmartGripper.STATE_OPEN));
				backLeft.whileHeld(new SmartElevatorManualDrive(-1)); 
				backRight.whileHeld(new SmartElevatorManualDrive(1));
				//start.whenPressed(new Catapult());
				//select.whenPressed(new ClimberRewind());
				start.whileHeld(new ClimberUp());
				dpadDown.whenPressed(new SmartElevatorGoToBottom());
				dpadUp.whenPressed(new SmartElevatorGoToTop());
				
				break;
			
			case CONTROL_SCHEME_TESTING:
				x.whileHeld(new SmartGripperManualDrive(1, 1));
				y.whileHeld(new SmartGripperManualDrive(-1, 1));
				select.whileHeld(new SmartGripperManualDrive(1, -1));
				start.whileHeld(new SmartGripperManualDrive(-1, -1));
				backLeft.whileHeld(new SmartElevatorManualDrive(-1)); 
				backRight.whileHeld(new SmartElevatorManualDrive(1));
				break;
			
			case CONTROL_SCHEME_DEFAULT:
			default:
				a.whileHeld(new IntakeTakeIn());
				b.whileHeld(new IntakeTakeOut());		
				x.whenPressed(new SmartGripperToggle());
				y.whileHeld(new ClimberRewind());
				//backLeft.whenPressed(new SmartElevatorMove(-1)); 
				//backRight.whenPressed(new SmartElevatorMove(1));
				//start.whenPressed(new RumbleInTheJungleJuliaWasRight());
				//select.whenPressed(new Catapult());
				//leftJoy.whenPressed(new SmartGripperJankyMove());
				start.whileHeld(new ClimberUp());
				break;
		}
	}
	public void putPOV(){
		SmartDashboard.putNumber("D-Pad", controller.getPOV());
	}
}
