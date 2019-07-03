package team.gif.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import team.gif.Constants;
import team.gif.RobotMap;

public class Elevator extends Subsystem {

    private static Elevator instance;

    private final TalonSRX lift;

    private Elevator() {
        lift = new TalonSRX(RobotMap.ELEVATOR_LIFT_ID);
        configLift(lift);
    }

    private void configLift(TalonSRX talon) {
        talon.configFactoryDefault();
        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        talon.enableVoltageCompensation(true);
        talon.setSensorPhase(true);
        talon.setInverted(false);
        talon.setNeutralMode(NeutralMode.Brake);
    }

    public void setPercentOutput(double percent) {
        lift.set(ControlMode.PercentOutput, percent);
    }

    public static Elevator getInstance() {
        if (instance == null) {
            instance = new Elevator();
        }
        return instance;
    }

    @Override
    protected void initDefaultCommand() {

    }
}
