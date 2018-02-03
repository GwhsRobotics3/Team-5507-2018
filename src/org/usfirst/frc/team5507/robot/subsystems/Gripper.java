package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Constants;
import org.usfirst.frc.team5507.robot.RobotMap;
import org.usfirst.frc.team5507.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team5507.robot.commands.GripperStop;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gripper extends Subsystem {
	private WPI_TalonSRX leftArm = new WPI_TalonSRX(RobotMap.leftArm);
	private WPI_TalonSRX rightArm = new WPI_TalonSRX(RobotMap.rightArm);
	//ADD ENCODERS
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	

    public void initDefaultCommand() {
     //  Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	//setDefaultCommand(new DriveWithJoystick());
    }
    public void ArmEncoders()
    {
    	Constants.configTalon(leftArm);
    	Constants.configTalon(rightArm);
    }
    
//   public void control(Joystick stick) {
//    	leftArm.set(stick.getRawAxis(0)*.5);
//    	rightArm.set(stick.getRawAxis(4)*.5);
//    }
    public void driveToAngle(double degrees)
    {
    	resetPosition();
    	double targetPos = (degrees * 4096) / 360;   	
		leftArm.set(ControlMode.MotionMagic, targetPos); 
		rightArm.set(ControlMode.MotionMagic, targetPos * -1);

    }
    
    public void open()
    {
    	leftArm.set(-1);  
    	rightArm.set(-1);
    }
    
    public void close()
    {
    	leftArm.set(1);
    	rightArm.set(1);
    }
    
    public void stop()
    {
    	leftArm.set(0);
    	rightArm.set(0);
    }
    public void resetPosition() 
    {
    	leftArm.setSelectedSensorPosition(0, 0, 0);
    	rightArm.setSelectedSensorPosition(0, 0, 0);
    }
}

