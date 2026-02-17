// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {

  private TurretSubsystem turret = new TurretSubsystem();

  private CommandXboxController controller = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {

    turret.setDefaultCommand(Commands.run(() -> {
      turret.setTargetAngle((Math.PI / 4.0) * MathUtil.applyDeadband(controller.getRightTriggerAxis(), 0.1));
      turret.setTargetSpeed(4000.0 * MathUtil.applyDeadband(controller.getLeftTriggerAxis(), 0.1));
    }, turret));

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
