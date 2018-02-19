package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.RobotMap;
import org.usfirst.frc.team5507.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	private double pos;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.driveFrontLeft);
	private WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.driveFrontRight);
	private WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.driveBackLeft);
	private WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.driveBackRight);
	static final int CURRENT_LIMIT = 30;
//	private static DigitalInput limitSwitch = new DigitalInput(1);
//	Counter counter = new Counter(limitSwitch);
  

	private MecanumDrive m_drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

	public DriveTrain() {
		super("DriveTrain");
	    LiveWindow.addChild(this, m_drive);
		configTalon(frontLeft);
		configTalon(frontRight);
		configTalon(backRight);
		configTalon(backLeft);
		addChild("front left talon", frontLeft);
		addChild("front right talon", frontRight);
		addChild("back left talon", backLeft);
		addChild("back right talon", backRight);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoystick());
   	
    }
    
    public void drive(double ySpeed, double xSpeed, double zRotation)
    {   	
    	m_drive.driveCartesian(ySpeed, xSpeed, zRotation);
    }
    
    public void drive(XboxController controller)
    {
    	double y = controller.getRawAxis(1);
    	double x = controller.getRawAxis(0);
    	double z = controller.getRawAxis(3) - controller.getRawAxis(2);
    	if(Math.abs(x) > .2 || Math.abs(y) > .2 || Math.abs(z) > .2)
    	{
    		drive(x, -y , z);
    	}
    }
    
    public void driveForwardDistance(double targetPos, double angle)
    {
    	double angleError = (angle - Robot.m_ahrs.getYaw()) / 180;
    	angleError = angleError * 10;
    	angleError = Math.min(angleError, 1);
    	angleError = Math.max(angleError, -1);
    	double speed = 0;
    	if((targetPos < 0) && (frontLeft.getSelectedSensorPosition(0) > targetPos))
    	{
    		speed = -0.4;
    	}
    	if ((targetPos > 0) && (frontLeft.getSelectedSensorPosition(0) < targetPos))
    	{
    		speed = 0.4;
    	}
    	System.out.println(speed);
    	Robot.m_driveTrain.drive(0, speed, angleError);
    }
    
    public void driveSidewaysDistance(double targetPos, double angle)
    {
    	double angleError = (angle - Robot.m_ahrs.getYaw()) / 180;
    	angleError = angleError * 10;
    	angleError = Math.min(angleError, 1);
    	angleError = Math.max(angleError, -1);
    	double speed = 0;
      	if((targetPos < 0) && (frontLeft.getSelectedSensorPosition(0) > targetPos))
    	{
    		speed = -0.4;
    	}
    	if ((targetPos > 0) && (frontLeft.getSelectedSensorPosition(0) < targetPos))
    	{
    		speed = 0.4;
    	}
    	Robot.m_driveTrain.drive(speed, 0, angleError);
    }
    
    public void driveSideways(double speed, double targetAngle)
    {
    	double angleError = (targetAngle - Robot.m_ahrs.getYaw()) / 180;
    	angleError = angleError * 10;
    	angleError = Math.min(angleError, 1);
    	angleError = Math.max(angleError, -1);
    	Robot.m_driveTrain.drive(speed, 0, angleError);
    }
    
    public static double getCurrentPos()
    {
    	return Robot.m_driveTrain.frontLeft.getSelectedSensorPosition(0);
    }
    
    public static void resetPos()
    {
    	Robot.m_driveTrain.frontLeft.setSelectedSensorPosition(0,0,0);
   
    } 
    
    public void putExtraData()
    {
    	SmartDashboard.putNumber("frontleftpos", frontLeft.getSelectedSensorPosition(0));
    	SmartDashboard.putNumber("frontleftspd", frontLeft.getSelectedSensorVelocity(0));
    	SmartDashboard.putNumber("frontrightpos", frontRight.getSelectedSensorPosition(0));
    	SmartDashboard.putNumber("frontrightspd", frontRight.getSelectedSensorVelocity(0));
    	SmartDashboard.putNumber("backleftpos", backLeft.getSelectedSensorPosition(0));
    	SmartDashboard.putNumber("backleftspd", backLeft.getSelectedSensorVelocity(0));
    	SmartDashboard.putNumber("backrightpos", backRight.getSelectedSensorPosition(0));
    	SmartDashboard.putNumber("backrightspd", backRight.getSelectedSensorVelocity(0));  
    }
    public static void configTalon(WPI_TalonSRX talon) {
		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, ConfigTalon.kPIDLoopIdx, ConfigTalon.kTimeoutMs);
		talon.setSensorPhase(true);
		talon.setInverted(false);
		talon.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled, ConfigTalon.kTimeoutMs);
		talon.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.Disabled, ConfigTalon.kTimeoutMs);
		
		/* Set relevant frame periods to be at least as fast as periodic rate*/
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, ConfigTalon.kTimeoutMs);
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, ConfigTalon.kTimeoutMs);

		/* set the peak and nominal outputs */
		talon.configNominalOutputForward(0, ConfigTalon.kTimeoutMs);
		talon.configNominalOutputReverse(0, ConfigTalon.kTimeoutMs);
		talon.configPeakOutputForward(1, ConfigTalon.kTimeoutMs);
		talon.configPeakOutputReverse(-1, ConfigTalon.kTimeoutMs);
		
		/* set closed loop gains in slot0 - see documentation */
		talon.selectProfileSlot(ConfigTalon.kSlotIdx, ConfigTalon.kPIDLoopIdx);
		talon.config_kF(0, 0.2, ConfigTalon.kTimeoutMs);
		talon.config_kP(0, 1, ConfigTalon.kTimeoutMs);
		talon.config_kI(0, 0, ConfigTalon.kTimeoutMs);
		talon.config_kD(0, 10, ConfigTalon.kTimeoutMs);
		/* set acceleration and vcruise velocity - see documentation */
		talon.configMotionCruiseVelocity(15000, ConfigTalon.kTimeoutMs);
		talon.configMotionAcceleration(6000, ConfigTalon.kTimeoutMs);
		/* zero the sensor */
		talon.setSelectedSensorPosition(0, ConfigTalon.kPIDLoopIdx, ConfigTalon.kTimeoutMs);
	}
    
//    public boolean isSwitchSetDrive() {
//        return counter.get() < 0;
//    }



}

