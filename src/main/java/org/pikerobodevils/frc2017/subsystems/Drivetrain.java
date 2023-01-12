/* (C) 2023 Pike RoboDevils, FRC Team 1018 */
package org.pikerobodevils.frc2017.subsystems;

import static org.pikerobodevils.frc2017.Constants.DrivetrainConstants.*;

import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;

public class Drivetrain extends SubsystemBase {

  WPI_TalonSRX frontLeft = new WPI_TalonSRX(FRONT_LEFT_ID);
  WPI_TalonSRX frontRight = new WPI_TalonSRX(FRONT_RIGHT_ID);
  WPI_TalonSRX rearLeft = new WPI_TalonSRX(REAR_LEFT_ID);
  WPI_TalonSRX rearRight = new WPI_TalonSRX(REAR_RIGHT_ID);

  AHRS navx = new AHRS();

  public Drivetrain() {
    var config = new TalonSRXConfiguration();
    config.voltageCompSaturation = 10;

    frontLeft.configAllSettings(config);
    frontLeft.enableVoltageCompensation(true);
    frontRight.configAllSettings(config);
    frontRight.enableVoltageCompensation(true);
    frontRight.setInverted(true);
    rearLeft.configAllSettings(config);
    rearLeft.enableVoltageCompensation(true);
    rearRight.configAllSettings(config);
    rearRight.enableVoltageCompensation(true);
    rearRight.setInverted(true);
  }

  public void setWheelSpeeds(MecanumDrive.WheelSpeeds speeds) {
    frontLeft.set(speeds.frontLeft);
    frontRight.set(speeds.frontRight);
    rearLeft.set(speeds.rearLeft);
    rearRight.set(speeds.rearRight);
  }

  public void driveCartesian(double x, double y, double omega) {
    var speeds =
        MecanumDrive.driveCartesianIK(x, y, omega, Rotation2d.fromDegrees(navx.getAngle()));
    setWheelSpeeds(speeds);
  }

  public CommandBase driveCartesianCommand(
      DoubleSupplier x, DoubleSupplier y, DoubleSupplier omega) {
    return run(
        () -> {
          driveCartesian(x.getAsDouble(), y.getAsDouble(), omega.getAsDouble());
        });
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Gyro angle", navx.getRotation2d().getDegrees());
  }
}
