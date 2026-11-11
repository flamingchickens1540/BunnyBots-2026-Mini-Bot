package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

public class DriveTrain extends SubsystemBase {
    private final TalonSRX leftMotor = new TalonSRX(1);
    private final TalonSRX rightMotor = new TalonSRX(2);

    public Command drive(DoubleSupplier righty, DoubleSupplier rightx) {
        return Commands.run(() -> {
            double speedLeft = 0.0;
            double speedRight = 0.0;
            if ((rightx.getAsDouble() > .2 || rightx.getAsDouble() < .2)) {
                speedLeft -= rightx.getAsDouble();
                speedRight -= rightx.getAsDouble();
            }
            if ((righty.getAsDouble() > .2 || righty.getAsDouble() < .2)) {
                speedLeft += righty.getAsDouble();
                speedRight -= righty.getAsDouble();
            }
            leftMotor.set(TalonSRXControlMode.PercentOutput, speedLeft);
            rightMotor.set(TalonSRXControlMode.PercentOutput, speedRight);
        }, this);
    }
}