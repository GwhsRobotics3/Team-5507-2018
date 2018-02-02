/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot;

import org.usfirst.frc.team5507.robot.commands.ClimberDown;
import org.usfirst.frc.team5507.robot.commands.ClimberUp;
import org.usfirst.frc.team5507.robot.commands.ElevatorDown;
import org.usfirst.frc.team5507.robot.commands.ElevatorUp;
import org.usfirst.frc.team5507.robot.commands.EncoderToAngle;
import org.usfirst.frc.team5507.robot.commands.GripperClose;
import org.usfirst.frc.team5507.robot.commands.GripperOpen;
import org.usfirst.frc.team5507.robot.commands.IntakeTakeIn;
import org.usfirst.frc.team5507.robot.commands.IntakeTakeOut;
import org.usfirst.frc.team5507.robot.commands.SmartElevatorUp;
import org.usfirst.frc.team5507.robot.commands.SmartGripperTest;
import org.usfirst.frc.team5507.robot.commands.TestPrint;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

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
	
	Joystick stick = new Joystick(0);
	
	public OI()
	{
		Button aButton = new JoystickButton(stick, 1); //arm in
		Button xButton = new JoystickButton(stick, 3); //arm out
		Button backLeft = new JoystickButton(stick,5); //gripper
		Button backRight = new JoystickButton(stick, 6); //gripper
		Button bButton = new JoystickButton(stick, 2);  //elevator up
		Button yButton = new JoystickButton(stick, 4); //elevator down
		//climbing right joystick

		aButton.whileHeld(new IntakeTakeIn());
		xButton.whileHeld(new IntakeTakeOut());		
		backLeft.whenPressed(new SmartElevatorUp()); //GripperOpen command
		backRight.whenPressed(new SmartGripperTest(30));	
		bButton.whenPressed(new ElevatorUp());
		yButton.whenPressed(new ElevatorUp());		
	}
	
	public Joystick getJoystick() {
		return stick;
	}
}
