package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
// cool beans
/**
 *
 */
public class Climber extends Subsystem {


	private static WPI_TalonSRX climberPulley = new WPI_TalonSRX(RobotMap.climbPulley);
	private static WPI_TalonSRX climberArm = new WPI_TalonSRX(RobotMap.climbArm);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
//    public void armUp()
//    {
//    	climberPulley.set(-.5);
//    	climberArm.set(-.5);
//    }
//    

    

    public void armUp(double speed)
    {   	
    	climberPulley.set(speed);
    	climberArm.set(speed);
    }

    public void robotUp()
    {
    	climberPulley.set(.5);
    }

   public void robotDown()
    {
    	climberPulley.set(-.5);
    }
    public void armDown()
    {
    	climberPulley.set(.5);
    	climberArm.set(.5);
    }
    public void climberRewind()
    {
    	climberPulley.set(-.5);
    }
    
    public void testPrint()
    {
    	System.out.println("button left trigger");
    }
}
