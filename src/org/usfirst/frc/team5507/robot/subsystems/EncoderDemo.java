package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Constants;
import org.usfirst.frc.team5507.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class EncoderDemo extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static WPI_TalonSRX m_talon = new WPI_TalonSRX(RobotMap.encoder);
	
	public EncoderDemo()
	{
		m_talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
		m_talon.setSensorPhase(true);
		m_talon.setInverted(false);
		
		/* Set relevant frame periods to be at least as fast as periodic rate*/
		m_talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		m_talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

		/* set the peak and nominal outputs */
		m_talon.configNominalOutputForward(0, Constants.kTimeoutMs);
		m_talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		m_talon.configPeakOutputForward(1, Constants.kTimeoutMs);
		m_talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		
		/* set closed loop gains in slot0 - see documentation */
		m_talon.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		m_talon.config_kF(0, 0.2, Constants.kTimeoutMs);
		m_talon.config_kP(0, 0.2, Constants.kTimeoutMs);
		m_talon.config_kI(0, 0, Constants.kTimeoutMs);
		m_talon.config_kD(0, 0, Constants.kTimeoutMs);
		/* set acceleration and vcruise velocity - see documentation */
		m_talon.configMotionCruiseVelocity(15000, Constants.kTimeoutMs);
		m_talon.configMotionAcceleration(6000, Constants.kTimeoutMs);
		/* zero the sensor */
		m_talon.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void driveToAngle(double degrees)
    {
    	double targetPos = (degrees * 4096) / 360;   	
		m_talon.set(ControlMode.MotionMagic, targetPos); 

    }
}

