// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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

    turret.setDefaultCommand(Commands.runOnce(() -> {
      turret.setTargetAngle(45.0 * controller.getRightTriggerAxis());
      turret.setTargetAngle(4000.0 * controller.getLeftTriggerAxis());
    }, turret));

  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
