package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.RobotMap;
import org.usfirst.frc.team5507.robot.commands.IntakeStop;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * Wheels on the hands of the gripper take in the box and spits it out.
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static WPI_VictorSPX m_leftWheel = new WPI_VictorSPX(RobotMap.intakeLeftMotor);
	private static WPI_VictorSPX m_rightWheel = new WPI_VictorSPX(RobotMap.intakeRightMotor);
	private static DigitalInput limitSwitch = new DigitalInput(RobotMap.intakeBoxLimitSwitch);
	
	public Intake() {
		super("Intake");
	    addChild("Limit Switch", limitSwitch);
	    addChild("left intake talon", m_leftWheel);
	    addChild("right intake talon", m_rightWheel);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new IntakeStop());
    }
    
    public void reelIn()
    {
    	m_leftWheel.set(.25);
    	m_rightWheel.set(.25);
    	SmartDashboard.putBoolean("Intake limit switch", isSwitchSet());
    }
    
    public void reelOut()
    {
    	m_leftWheel.set(-1);
    	m_rightWheel.set(-1); 
    }
    
    public void stop()
    {
    	m_leftWheel.set(0);
    	m_rightWheel.set(0);
    }
    
   public boolean isSwitchSet() {
       return limitSwitch.get();    
       }
   
	public void putExtraData() {
		SmartDashboard.putBoolean("limit switch", Robot.m_intake.isSwitchSet());
		SmartDashboard.putNumber("left wheel voltage", m_leftWheel.getMotorOutputVoltage());
		SmartDashboard.putNumber("right wheel voltage", m_rightWheel.getMotorOutputVoltage());
	}
}

