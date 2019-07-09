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

    public static Elevator getInstance() {
        if (instance == null) {
            instance = new Elevator();
        }
        return instance;
    }

    public void setPercentOutput(double percent) {
        lift.set(ControlMode.PercentOutput, percent);
    }

    public void setCruiseVelocity(int ticksper100ms) {
        lift.configMotionCruiseVelocity(ticksper100ms);
    }

    public boolean isFinished() {
        return Math.abs(lift.getClosedLoopError()) < Constants.Elevator.ALLOWABLE_ERROR;
    }

    private void configLift(TalonSRX talon) {
        talon.configFactoryDefault();
        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        talon.enableVoltageCompensation(true);
        talon.setSensorPhase(true);
        talon.setInverted(false);
        talon.setNeutralMode(NeutralMode.Brake);

        talon.configMotionCruiseVelocity(Constants.Elevator.MAX_VELOCITY);
        talon.configMotionAcceleration(Constants.Elevator.MAX_ACCELERATION);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
