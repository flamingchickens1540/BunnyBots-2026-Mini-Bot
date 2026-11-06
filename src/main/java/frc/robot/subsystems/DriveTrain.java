package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

public class DriveTrain extends SubsystemBase {
    private final TalonSRX leftMotor = new TalonSRX(1);
    private final TalonSRX rightMotor = new TalonSRX(2);
    private final XboxController controller = new XboxController(0);


    public Command drive(DoubleSupplier leftTrigger, DoubleSupplier rightTrigger) {
        return Commands.run(() -> {
            double speedLeft = 0.0;
            double speedRight = 0.0;
            if (leftTrigger.getAsDouble() > .2) {
                speedLeft += leftTrigger.getAsDouble()/1.3;
                speedRight -= leftTrigger.getAsDouble()/1.3;
            }
            if (rightTrigger.getAsDouble() > .2) {
                speedLeft -= rightTrigger.getAsDouble()/2;
                speedRight += rightTrigger.getAsDouble()/2;
            }
            if (controller.getLeftBumperButton()) {
                if (speedLeft>=-.7){
                    speedLeft -= .3;
                }
                if (speedRight <= .7){
                    speedRight +=.3;
                }
            }
            if (controller.getRightBumperButton()) {
                if (speedLeft<=.7){
                    speedLeft += .3;
                }
                if (speedRight >=-.7){
                    speedRight -=.3;
                }
            }
            leftMotor.set(TalonSRXControlMode.PercentOutput, speedLeft);
            rightMotor.set(TalonSRXControlMode.PercentOutput, speedRight);
        }, this);
    }
}