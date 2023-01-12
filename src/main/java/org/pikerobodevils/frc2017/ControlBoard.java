/* (C) 2023 Pike RoboDevils, FRC Team 1018 */
package org.pikerobodevils.frc2017;

import static org.pikerobodevils.frc2017.Constants.ControlBoardConstants.*;

import edu.wpi.first.wpilibj2.command.button.CommandJoystick;

public class ControlBoard {
  private final CommandJoystick leftStick = new CommandJoystick(LEFT_STICK);
  private final CommandJoystick rightStick = new CommandJoystick(RIGHT_STICK);

  public double getX() {
    return -leftStick.getY();
  }

  public double getY() {
    return leftStick.getX();
  }

  public double getOmega() {
    return rightStick.getX();
  }
}
