/* (C) 2023 Pike RoboDevils, FRC Team 1018 */
package org.pikerobodevils.frc2017;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import org.pikerobodevils.frc2017.subsystems.Drivetrain;

public class RobotContainer {
  private final ControlBoard controlBoard = new ControlBoard();
  private final Drivetrain drivetrain = new Drivetrain();

  public RobotContainer() {
    drivetrain.setDefaultCommand(
        drivetrain.driveCartesianCommand(
            controlBoard::getX, controlBoard::getY, controlBoard::getOmega));

    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
