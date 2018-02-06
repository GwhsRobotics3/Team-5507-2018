/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Constants {

	/**
	 * Which PID slot to pull gains from.  Starting 2018, you can choose 
	 * from 0,1,2 or 3.  Only the first two (0,1) are visible in web-based configuration.
	 */
	public static final double TICKS_START = 0.0;
	public static final double TICKS_OPEN = 1800;
	public static final double TICKS_CLOSED = 2000;

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

	// config a talon motor controller with an Encoder
	public static void configTalon(WPI_TalonSRX talon) {
		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, kPIDLoopIdx, kTimeoutMs);
		talon.setSensorPhase(true);
		talon.setInverted(false);

		/* Set relevant frame periods to be at least as fast as periodic rate*/
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, kTimeoutMs);
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, kTimeoutMs);

		/* set the peak and nominal outputs */
		talon.configNominalOutputForward(0, kTimeoutMs);
		talon.configNominalOutputReverse(0, kTimeoutMs);
		talon.configPeakOutputForward(1, kTimeoutMs);
		talon.configPeakOutputReverse(-1, kTimeoutMs);

		/* set closed loop gains in slot0 - see documentation */
		talon.selectProfileSlot(kSlotIdx, kPIDLoopIdx);
		talon.config_kF(0, 0.2, kTimeoutMs);
		talon.config_kP(0, 0.2, kTimeoutMs);
		talon.config_kI(0, 0, kTimeoutMs);
		talon.config_kD(0, 0, kTimeoutMs);
		/* set acceleration and vcruise velocity - see documentation */
		talon.configMotionCruiseVelocity(15000, kTimeoutMs);
		talon.configMotionAcceleration(6000, kTimeoutMs);
		/* zero the sensor */
		talon.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
	}
}
