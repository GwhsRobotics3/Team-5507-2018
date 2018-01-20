package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.commands.GripperStop;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gripper extends Subsystem {
	private WPI_TalonSRX leftArm = new WPI_TalonSRX(1);
	private WPI_TalonSRX rightArm = new WPI_TalonSRX(2);
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GripperStop());
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
}

