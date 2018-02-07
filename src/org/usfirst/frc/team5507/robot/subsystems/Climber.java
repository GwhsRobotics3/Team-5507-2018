package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	private static WPI_TalonSRX climberPulley = new WPI_TalonSRX(0);
	private static WPI_TalonSRX climberArm = new WPI_TalonSRX(3);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void climb(XboxController controller)
    {
    	
    	double y = controller.getRawAxis(5);
    	System.out.println("adam is climb");
    	
//    	if (Math.abs(y) > .1 )
//    	{
//    		armUp();
//    	}
    }
    
    public void armUp()
    {
    	climberPulley.set(-.3);
    	climberArm.set(-.3);	
    }
    
    public void robotUp()
    {
    	climberPulley.set(.3);
    }
    
    public void robotDown()
    {
    	climberPulley.set(-.3);
    }
    
    public void armDown()
    {
    	climberPulley.set(.3);
    	climberArm.set(.3);
    }
    
    public void climberRewind()
    {
    	climberPulley.set(-.3);
    }
    
}
