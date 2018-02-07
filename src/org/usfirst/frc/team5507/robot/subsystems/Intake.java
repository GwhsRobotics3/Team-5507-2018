package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.Robot;
import org.usfirst.frc.team5507.robot.RobotMap;
import org.usfirst.frc.team5507.robot.commands.IntakeStop;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Wheels on the hands of the gripper take in the box and spits it out.
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static WPI_VictorSPX m_leftWheel = new WPI_VictorSPX(RobotMap.intakeLeftMotor);
	private static WPI_VictorSPX m_rightWheel = new WPI_VictorSPX(RobotMap.intakeRightMotor);
	private static DigitalInput limitSwitch = new DigitalInput(3);
	Counter counter = new Counter(limitSwitch);
	
	public Intake() {
		super("Intake");
	    LiveWindow.addChild(this, limitSwitch);
	    LiveWindow.addChild(this, counter);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new IntakeStop());
    }
    
    public void reelIn()
    {
    	m_leftWheel.set(.75);
    	m_rightWheel.set(.75);
//    	SmartDashboard.putBoolean("Intake limit switch", isSwitchSet());
    }
    
    public void reelOut()
    {
    	m_leftWheel.set(-.75);
    	m_rightWheel.set(-.75); 
    }
    
    public static void stop()
    {
    	m_leftWheel.set(0);
    	m_rightWheel.set(0);
    }
    
   public boolean isSwitchSet() {
       return counter.get() > 0;    
       }
}

