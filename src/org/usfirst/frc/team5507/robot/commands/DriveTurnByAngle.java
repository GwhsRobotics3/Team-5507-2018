package org.usfirst.frc.team5507.robot.commands;

import org.usfirst.frc.team5507.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTurnByAngle extends Command {

	private double angle;
	AHRS ahrs;
	
    public DriveTurnByAngle(double a) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_driveTrain);
    	angle = a;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	 try {
             ahrs = new AHRS(I2C.Port.kMXP);
         	ahrs.enableLogging(true);
         } catch (RuntimeException ex ) {
             DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
         }
    	 ahrs.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (angle == -90)
    	{
    		if(ahrs.getYaw() > -88)
        	{
        		Robot.m_driveTrain.drive(0, 0, 0.5);
        	}
        	else if((ahrs.getYaw() > -92) && (ahrs.getYaw() < -88))
        	{
        		Robot.m_driveTrain.drive(0, 0, 0);
        	}
        	else if(ahrs.getYaw() < -92)
        	{
        		Robot.m_driveTrain.drive(0, 0,-0.5);
        	}
    	}
    	else 
    	{
    		if(ahrs.getYaw() < 88)
        	{
    			Robot.m_driveTrain.drive(0, 0,-0.5);
        	}
        	else if((ahrs.getYaw() < 92) && (ahrs.getYaw() > 88))
        	{
        		Robot.m_driveTrain.drive(0, 0, 0);
        	}
        	else if(ahrs.getYaw() > 92)
        	{
        		Robot.m_driveTrain.drive(0, 0, 0.5);
        	}
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (((ahrs.getYaw() > -92) && (ahrs.getYaw() < -88)) || ((ahrs.getYaw() < 92) && (ahrs.getYaw() > 88)));    	  	
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
