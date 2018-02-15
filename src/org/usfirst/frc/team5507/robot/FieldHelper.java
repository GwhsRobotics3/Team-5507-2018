package org.usfirst.frc.team5507.robot;

import edu.wpi.first.wpilibj.DriverStation;

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
}

