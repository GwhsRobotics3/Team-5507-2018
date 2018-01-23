package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	private static WPI_TalonSRX elevatorPulley = new WPI_TalonSRX(RobotMap.elevator);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void goUp()
    {
    	elevatorPulley.set(1);
    }
    
    public void goDown()
    {
    	elevatorPulley.set(-1);
    }
}

