package org.usfirst.frc.team5507.robot;

import org.usfirst.frc.team5507.robot.commands.AutonomousCrossLine;
import org.usfirst.frc.team5507.robot.commands.AutoCatapultInLScale;
import org.usfirst.frc.team5507.robot.commands.AutoCatapultInLSwitch;
import org.usfirst.frc.team5507.robot.commands.AutoCatapultInRScale;
import org.usfirst.frc.team5507.robot.commands.AutoCatapultInRSwitch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class FieldHelper {

	public static final int ROBOT_START_LEFT = 1;
	public static final int ROBOT_START_MIDDLE = 2;
	public static final int ROBOT_START_RIGHT = 3;

	public static boolean isSwitchLeft()
	{
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.length() > 0)
		{
			if(gameData.charAt(0) == 'L')
			{
				return true;
			}
		}
		return false;
	}

	public static boolean isScaleLeft()
	{
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.length() > 0)
		{
			if(gameData.charAt(1) == 'L')
			{
				return true;
			}
		}
		return false;
	}

	public static CommandGroup getAuto(int position, String goal)
	{
		CommandGroup x = new AutonomousCrossLine();
		if(goal == "switch") {
			if(isSwitchLeft()) {
				if(position == ROBOT_START_LEFT) {
					x = new AutoCatapultInLSwitch(ROBOT_START_LEFT);
				}
				else if(position == ROBOT_START_RIGHT) {
					x = new AutoCatapultInLSwitch(ROBOT_START_RIGHT);
				}
				else if(position == ROBOT_START_MIDDLE) {
					x = new AutoCatapultInLSwitch(ROBOT_START_MIDDLE);
				}
			}
			else {
				if(position == ROBOT_START_LEFT) {
					x = new AutoCatapultInRSwitch(ROBOT_START_LEFT);
				}
				else if(position == ROBOT_START_RIGHT) {
					x = new AutoCatapultInRSwitch(ROBOT_START_RIGHT);
				}
				else if(position == ROBOT_START_MIDDLE) {
					x = new AutoCatapultInRSwitch(ROBOT_START_MIDDLE);
				}
			}
		}
		else if(goal == "scale") {
			if(isScaleLeft()) {
				if(position == ROBOT_START_LEFT) {
					x = new AutoCatapultInLScale(ROBOT_START_LEFT);
				}
				else if(position == ROBOT_START_RIGHT) {
					x = new AutoCatapultInLScale(ROBOT_START_RIGHT);
				}
				else if(position == ROBOT_START_MIDDLE) {
					x = new AutoCatapultInLScale(ROBOT_START_MIDDLE);
				}
			}
			else {
				if(position == ROBOT_START_LEFT) {
					x = new AutoCatapultInRScale(ROBOT_START_LEFT);
				}
				else if(position == ROBOT_START_RIGHT) {
					x = new AutoCatapultInRScale(ROBOT_START_RIGHT);
				}
				else if(position == ROBOT_START_MIDDLE) {
					x = new AutoCatapultInRScale(ROBOT_START_MIDDLE);
				}
			}
		}
		else {
			x = new AutonomousCrossLine();
		}
		return x;
	}
}



