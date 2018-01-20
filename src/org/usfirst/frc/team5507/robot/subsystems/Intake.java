package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.RobotMap;
import org.usfirst.frc.team5507.robot.commands.IntakeStop;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Wheels on the hands of the gripper take in the box and spits it out.
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Spark m_leftWheel = new Spark(RobotMap.intakeLeftMotor);
	private Spark m_rightWheel = new Spark(RobotMap.intakeRightMotor);
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new IntakeStop());
    }
    
    public void reelIn()
    {
    	m_leftWheel.set(1);
    	m_rightWheel.set(-1);
    }
    
    public void reelOut()
    {
    	m_leftWheel.set(-1);
    	m_rightWheel.set(1);
    }
    
    public void stop()
    {
    	m_leftWheel.set(0);
    	m_rightWheel.set(0);
    }
}

