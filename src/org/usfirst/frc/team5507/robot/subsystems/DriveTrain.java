package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Constants;
import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.RobotMap;
import org.usfirst.frc.team5507.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
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
	private static WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.driveFrontLeft);
	private WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.driveFrontRight);
	private WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.driveBackLeft);
	private WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.driveBackRight);
//	private static DigitalInput limitSwitch = new DigitalInput(1);
//	Counter counter = new Counter(limitSwitch);
  

	private MecanumDrive m_drive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

	public DriveTrain() {
		super("DriveTrain");
	    LiveWindow.addChild(this, m_drive);
		ConfigTalon.configTalon(frontLeft);
		ConfigTalon.configTalon(frontRight);
		ConfigTalon.configTalon(backRight);
		ConfigTalon.configTalon(backLeft);
		addChild("front left talon",frontLeft);
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
    	SmartDashboard.putNumber("frontleftpos", frontLeft.getSelectedSensorPosition(0));
    	SmartDashboard.putNumber("frontleftspd", frontLeft.getSelectedSensorVelocity(0));
    	SmartDashboard.putNumber("frontrightpos", frontRight.getSelectedSensorPosition(0));
    	SmartDashboard.putNumber("frontrightspd", frontRight.getSelectedSensorVelocity(0));
    	SmartDashboard.putNumber("backleftpos", backLeft.getSelectedSensorPosition(0));
    	SmartDashboard.putNumber("backleftspd", backLeft.getSelectedSensorVelocity(0));
    	SmartDashboard.putNumber("backrightpos", backRight.getSelectedSensorPosition(0));
    	SmartDashboard.putNumber("backrightspd", backRight.getSelectedSensorVelocity(0));  	
    }
    
    public void drive(XboxController controller)
    {
    	double y = controller.getRawAxis(1);
    	double x = controller.getRawAxis(0);
    	double z = controller.getRawAxis(3) - controller.getRawAxis(2);
    	if(Math.abs(x) > .1 || Math.abs(y) > .1 || Math.abs(z) > .1)
    	{
    		drive(x, y, z);
    	}
    }
    
    public void driveForward(double targetPos)
    {
    	pos = targetPos;
    	if(frontLeft.getSelectedSensorPosition(0) < pos)
    	{
    		Robot.m_driveTrain.drive(0, 0.4, 0);
    	}
    }
    
    public void driveSideways(double targetPos)
    {
    	pos = targetPos;
    	if(frontLeft.getSelectedSensorPosition(0) < pos)
    	{
    		Robot.m_driveTrain.drive(0.4, 0, 0);
    	}
    }
    
    public static double getCurrentPos()
    {
    	return frontLeft.getSelectedSensorPosition(0);
    }
    
    public static void resetPos()
    {
    	frontLeft.setSelectedSensorPosition(0,0,0);
   
    }
    
//    public boolean isSwitchSetDrive() {
//        return counter.get() < 0;
//    }



}

