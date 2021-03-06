package org.usfirst.frc.team5507.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ConfigTalon extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
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
	

    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
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
		talon.config_kP(0, 1, kTimeoutMs);
		talon.config_kI(0, 0, kTimeoutMs);
		talon.config_kD(0, 10, kTimeoutMs);
		/* set acceleration and vcruise velocity - see documentation */
		talon.configMotionCruiseVelocity(15000, kTimeoutMs);
		talon.configMotionAcceleration(6000, kTimeoutMs);
		/* zero the sensor */
		talon.setSelectedSensorPosition(0, kPIDLoopIdx, kTimeoutMs);
	}
	//public ErrorCode configForwardLimitSwitchSource(RemoteLimitSwitchSource type, LimitSwitchNormal normalOpenOrClose,
		//	int deviceID, int timeoutMs) {
	//	return configForwardLimitSwitchSource(type.value, normalOpenOrClose.value, deviceID, timeoutMs);
	//https://github.com/CrossTheRoadElec/Phoenix-frc-lib/blob/master/java/src/com/ctre/phoenix/motorcontrol/can/BaseMotorController.java#L731
}



