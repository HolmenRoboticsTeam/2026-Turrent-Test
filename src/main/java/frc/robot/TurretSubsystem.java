// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {

  private SparkMax rotationMotor = new SparkMax(0, MotorType.kBrushless);
  private SparkMax flyWheelMotor = new SparkMax(1, MotorType.kBrushless);

  /** Creates a new TurretSubsystem. */
  public TurretSubsystem() {

    // Set the rotation config
    SparkMaxConfig rotationConfig = new SparkMaxConfig();
    rotationConfig.closedLoop.pid(1.0, 0.0, 0.0);
    rotationMotor.configure(rotationConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // Get the AbsoluteEncoder Position
    rotationMotor.getEncoder().setPosition(rotationMotor.getAbsoluteEncoder().getPosition());

    // Set the fly wheel config
    SparkMaxConfig flyWheelConfig = new SparkMaxConfig();

    flyWheelConfig.closedLoop.pid(1.0, 0.0, 0.0);
    flyWheelMotor.configure(rotationConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setTargetRotation(Rotation2d rot) {
    rotationMotor.getClosedLoopController().setSetpoint(rot.getRadians(), ControlType.kPosition);
  }

  public void setTargetSpeed(double volts) {
    flyWheelMotor.getClosedLoopController().setSetpoint(volts, ControlType.kVoltage);
  }
}
