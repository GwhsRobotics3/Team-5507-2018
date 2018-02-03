/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot;

public class Constants {
	
	/**
	 * Which PID slot to pull gains from.  Starting 2018, you can choose 
	 * from 0,1,2 or 3.  Only the first two (0,1) are visible in web-based configuration.
	 */
	public static final int grabPositionR = 0;
	public static final int readyPositionR = -1827;
	public static final int startPositionR = -1990;
	
	public static final int grabPositionL = 0;
	public static final int readyPositionL = 1750;
	public static final int startPositionL = 2000;
	
	public static final int ElhighPosition = 30;
	public static final int ElmedPosition = 20;
	public static final int EllowPosition = 10;
	
	public static final int kSlotIdx = 0;
	
	/* Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops.  
	 * For now we just want the primary one.
	 */
	public static final int kPIDLoopIdx = 0;

	/*
	 * set to zero to skip waiting for confirmation, set to nonzero to wait
	 * and report to DS if action fails.
	 */
	public static final int kTimeoutMs = 10;
	
}
