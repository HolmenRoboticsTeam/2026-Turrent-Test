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
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TurretSubsystem extends SubsystemBase {

  private SparkMax flyWheelMotorLeft = new SparkMax(1, MotorType.kBrushless);
  private SparkMax flyWheelMotorRight = new SparkMax(2, MotorType.kBrushless);

  private SparkMax angleMotor = new SparkMax(3, MotorType.kBrushless);

  /** Creates a new TurretSubsystem. */
  public TurretSubsystem() {

    // Set the fly wheel configs
    SparkMaxConfig leftFlyWheelConfig = new SparkMaxConfig();

    leftFlyWheelConfig.closedLoop.pid(0.1, 0.0, 0.0);
    leftFlyWheelConfig.inverted(false);
    flyWheelMotorLeft.configure(leftFlyWheelConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    SparkMaxConfig rightFlyWheelConfig = new SparkMaxConfig();

    rightFlyWheelConfig.closedLoop.pid(0.1, 0.0, 0.0);
    rightFlyWheelConfig.inverted(true);
    flyWheelMotorRight.configure(rightFlyWheelConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    SparkMaxConfig angleConfig = new SparkMaxConfig();

    angleConfig.encoder.positionConversionFactor(2.0 * Math.PI * (15.0 / 36.0));
    angleConfig.closedLoop.pid(0.01, 0.0, 0.0);
    angleConfig.inverted(false);
    angleMotor.configure(angleConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setTargetAngle(double rad) {
    angleMotor.getClosedLoopController().setSetpoint(rad, ControlType.kPosition);
  }

  public void setTargetSpeed(double RPM) {
    flyWheelMotorLeft.getClosedLoopController().setSetpoint(RPM, ControlType.kVelocity);
    flyWheelMotorRight.getClosedLoopController().setSetpoint(RPM, ControlType.kVelocity);
  }
}
