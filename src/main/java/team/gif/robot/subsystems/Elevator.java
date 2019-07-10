package team.gif.robot.subsystems;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXPIDSetConfiguration;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team.gif.Constants;
import team.gif.RobotMap;
import team.gif.robot.commands.elevator.ManualMovement;

public class Elevator extends Subsystem {

    private static Elevator instance;

    private final TalonSRX lift;

    private Elevator() {
        lift = new TalonSRX(RobotMap.ELEVATOR_LIFT_ID);
        configLift(lift);

        int absPos = lift.getSensorCollection().getPulseWidthPosition();
        absPos &= 0xFFF;
        lift.setSelectedSensorPosition(absPos);
    }

    public static Elevator getInstance() {
        if (instance == null) {
            instance = new Elevator();
        }
        return instance;
    }

//    public void setPercentOutput(double percent) {
//        lift.set(ControlMode.PercentOutput, percent);
//    }

    public void setPosition(double position) {
        lift.set(ControlMode.Position, position, DemandType.ArbitraryFeedForward, 0.15);
    }

    public double getPosition() {
        return lift.getSensorCollection().getQuadraturePosition();
    }

    public void displayMetrics() {
        SmartDashboard.putNumber("Elevator Position: ", getPosition());
        SmartDashboard.putNumber("Error: ", getError());
        System.out.println(Constants.Elevator.P);
    }

    public double getError() {
        return lift.getClosedLoopError();
    }

    public void setP(double P) {
        lift.config_kP(0, P);
    }

    public void manualMove(double percent) {
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

        talon.config_kP(0, Constants.Elevator.P);
        talon.config_kI(0, Constants.Elevator.I);
        talon.config_kD(0, Constants.Elevator.D);
        talon.config_kF(0, Constants.Elevator.F);
        talon.configNominalOutputForward(0);
        talon.configNominalOutputReverse(0);
        talon.configPeakOutputForward(1);
        talon.configPeakOutputReverse(-1);
        talon.selectProfileSlot(0, 0);
        talon.setSensorPhase(false);
        talon.setInverted(true);

        talon.configForwardSoftLimitThreshold(Constants.Elevator.MAX_POS);
        talon.configReverseSoftLimitThreshold(Constants.Elevator.MIN_POS);
        // talon.configForwardSoftLimitEnable(true);
        // talon.configReverseSoftLimitEnable(true);

//      talon.configMotionCruiseVelocity(Constants.Elevator.MAX_VELOCITY);
//      talon.configMotionAcceleration(Constants.Elevator.MAX_ACCELERATION);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ManualMovement(0.5));

    }
}
