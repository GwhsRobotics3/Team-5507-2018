/*----------------------------------------------------------------------------*/
/* Copyright ( zzc) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5507.robot.commands.AutonomousDriveStraight;
import org.usfirst.frc.team5507.robot.commands.AutonomousDriveStraightTurnLeft;
import org.usfirst.frc.team5507.robot.commands.ClimberUp;
import org.usfirst.frc.team5507.robot.commands.DriveTurnByAngle;
import org.usfirst.frc.team5507.robot.commands.ExampleCommand;
import org.usfirst.frc.team5507.robot.subsystems.Climber;
import org.usfirst.frc.team5507.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5507.robot.subsystems.Elevator;
import org.usfirst.frc.team5507.robot.subsystems.EncoderDemo;
import org.usfirst.frc.team5507.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team5507.robot.subsystems.SmartGripper;
import org.usfirst.frc.team5507.robot.subsystems.Intake;
import org.usfirst.frc.team5507.robot.subsystems.SmartElevator;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.AHRS.SerialDataType;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 * 
 * @author Julia Ma, Jennessa Ma, Daphne Nong, Thomas Lee, Riley Blair, Nelson Truong, GREG MARRA
 * SHOUTOUT TO GREG OUR ONE AND ONLY MENTOR, THE MASTERMIND OF THIS CODE 
 */
public class Robot extends TimedRobot {
	public static final ExampleSubsystem kExampleSubsystem
			= new ExampleSubsystem();
	public static OI m_oi;

	public static Climber m_climber = new Climber();
	public static DriveTrain m_driveTrain = new DriveTrain();
	public static EncoderDemo m_encoder = new EncoderDemo();
	//public static Elevator m_elevator = new Elevator();
	public static SmartElevator m_smartElevator = new SmartElevator();
	public static SmartGripper m_smartGripper = new SmartGripper();
	public static Intake m_intake = new Intake();
	public static AHRS m_ahrs;
	public static Timer m_timer = new Timer();

	//public static SmartGripper m_smartGripper = new SmartGripper();
	
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>(); 
	
	public Robot() {
	   try {
		    m_ahrs = new AHRS(I2C.Port.kMXP);
       	    m_ahrs.enableLogging(true);
        } catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
	}
	
	public void putSmartDashboardData() {
		SmartDashboard.putData(m_climber);
		SmartDashboard.putData(m_driveTrain);
		SmartDashboard.putData(m_smartElevator);
		SmartDashboard.putData(m_encoder);
		SmartDashboard.putData(m_smartGripper);
		SmartDashboard.putData(m_intake);
		SmartDashboard.putNumber("IMU_Yaw", m_ahrs.getYaw());
		SmartDashboard.putNumber("Pulley Position", Robot.m_smartElevator.getCurrentPos());
		SmartDashboard.putNumber("Elevator State", Robot.m_smartElevator.getCurrentState());
		SmartDashboard.putBoolean("limit switch", Robot.m_intake.isSwitchSet());
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
		m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoComman
		//+d());
		
		
		SmartDashboard.putData("Auto mode", m_chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_ahrs.reset();
		m_autonomousCommand = new AutonomousDriveStraightTurnLeft(); //m_chooser.getSelected();
		DriveTrain.resetPos();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		putSmartDashboardData();
		//SmartDashboard.putNumber("Position", Robot.leftEncoder);
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if(m_oi.controller.getRawAxis(5) < -0.1)
		{
			Scheduler.getInstance().add(new ClimberUp());
		}
		
		putSmartDashboardData();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
