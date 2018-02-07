package org.usfirst.frc.team5507.robot.subsystems;

import org.usfirst.frc.team5507.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {
	private static WPI_TalonSRX elevatorPulley = new WPI_TalonSRX(RobotMap.elevatorPulley);
//	private static DigitalInput limitSwitchTop = new DigitalInput(1);
//	Counter counterTop = new Counter(limitSwitchTop);
//	private static DigitalInput limitSwitchBottom = new DigitalInput(2);
//	Counter counterBottom = new Counter(limitSwitchBottom);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void goUp()
    {

    	elevatorPulley.set(1);
//    	if (forwardLimitSwitch.get())
//        {
//    		 elevatorPulley.set(0);
//        }    	
//    	SmartDashboard.putBoolean("Top elevator limit switch", isSwitchSetTop());
//    	SmartDashboard.putBoolean("Botton elevator limit switch", isSwitchSetBottom());

    	elevatorPulley.set(.5);

    }
    
    public void goDown()
    {

    	elevatorPulley.set(-1);
//    	if (downLimitSwitch.get())
//        {
//    		 elevatorPulley.set(0);
//        }    	

    	elevatorPulley.set(-.5);

    }
    
    public void stop()
    {
    	elevatorPulley.set(0);
    }
//    
//    public boolean isSwitchSetTop() {
//        return counterTop.get() > 0;
//    }
//    
//    public boolean isSwitchSetBottom() {
//        return counterBottom.get() > 0;
//    }
}

