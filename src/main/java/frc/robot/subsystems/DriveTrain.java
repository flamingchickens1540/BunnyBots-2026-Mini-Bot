package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import java.util.function.DoubleSupplier;

public class DriveTrain {
    private final TalonSRX leftMotor  = new TalonSRX(1);
    private final TalonSRX rightMotor = new TalonSRX(2);
    private final CommandXboxController controller = new CommandXboxController(0);

    public Command drive(DoubleSupplier righty, DoubleSupplier rightx){
        if ((rightx.getAsDouble()>.2||rightx.getAsDouble()<.2)){
            rightMotor.set(TalonSRXControlMode.PercentOutput, rightx.getAsDouble());
            leftMotor.set(TalonSRXControlMode.PercentOutput, -rightx.getAsDouble());
        }
    }
}
