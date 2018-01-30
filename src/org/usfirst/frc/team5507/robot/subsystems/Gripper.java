package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.RobotMap;
import org.usfirst.frc.team5507.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team5507.robot.commands.GripperStop;

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
    
//   public void control(Joystick stick) {
//    	leftArm.set(stick.getRawAxis(0)*.5);
//    	rightArm.set(stick.getRawAxis(4)*.5);
//    }
    
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
}

