package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
// cool beans
/**
 *
 */
public class Climber extends Subsystem {


	private static VictorSPX climberPulley = new VictorSPX(RobotMap.climbPulley);
	private static VictorSPX climberArm = new VictorSPX(RobotMap.climbArm);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Climber()
    {
//    	addChild("Pulley", climberPulley);
//    	addChild("arm", climberArm);
    }

    public void armUp(double speed)
    {   	
    	climberArm.set(ControlMode.PercentOutput, speed);
    }

    public void robotUp()
    {
    	climberPulley.set(ControlMode.PercentOutput, .5);
    }

   public void robotDown()
    {
    	climberPulley.set(ControlMode.PercentOutput, -.5);
    }
    public void armDown()
    {
    	climberArm.set(ControlMode.PercentOutput, .5);
    }
    public void climberRewind()
    {
    	climberPulley.set(ControlMode.PercentOutput, 1);
    }
    
    public void pulleyStop()
    {
    	climberPulley.set(ControlMode.PercentOutput, 0);
    }
    
    public void testPrint()
    {
    	System.out.println("button left trigger");
    }
    
    public void catapult(double speed)
    {
    	climberArm.set(ControlMode.PercentOutput, speed);
    }
}
