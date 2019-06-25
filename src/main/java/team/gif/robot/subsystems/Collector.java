package team.gif.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import team.gif.Constants;
import team.gif.RobotMap;

public class Collector extends Subsystem {

    private static Collector instance;

    private final TalonSRX intake;
    private final Solenoid deploy, clamp;
    private final DoubleSolenoid hooks;
    private final Servo left, right;

    public final AnalogInput ballSensor;

    private boolean hatchMode = true;

    private Collector() {
        intake = new TalonSRX(RobotMap.COLLECTOR_INTAKE_ID);

        deploy = new Solenoid(RobotMap.COLLECTOR_DEPLOY_ID);
        clamp = new Solenoid(RobotMap.COLLECTOR_CLAMP_ID);
        hooks = new DoubleSolenoid(RobotMap.COLLECTOR_HOOKS_FWD_ID, RobotMap.COLLECTOR_HOOKS_REV_ID);

        left = new Servo(RobotMap.COLLECTOR_LEFT_SERVO_ID);
        right = new Servo(RobotMap.COLLECTOR_RIGHT_SERVO_ID);

        ballSensor = new AnalogInput(RobotMap.COLLECTOR_BALL_SENSOR_ID);

        intake.configFactoryDefault();
        intake.setInverted(true);

    }

    public static Collector getInstance() {
        if (instance == null) {
            instance = new Collector();
        }
        return instance;
    }

    public void setIntake(double percent) { intake.set(ControlMode.PercentOutput, percent); }

    public void deployCollector(boolean out) { deploy.set(out); }

    public void openClamp(boolean open) { clamp.set(open); }

    public void deployHooks(boolean out) {
        hooks.set(out ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }

    public void engageServoBrakes(boolean engaged) {
        left.set(engaged ? Constants.Collector.LEFT_BRAKE_POS : Constants.Collector.LEFT_NEUTRAL_POS);
        right.set(engaged ? Constants.Collector.RIGHT_BRAKE_POS : Constants.Collector.RIGHT_NEUTRAL_POS);
    }

    public void setHatchMode(boolean hatchMode) { this.hatchMode = hatchMode; }

    public boolean isHatchMode() { return hatchMode; }

    public boolean isDeployed() { return deploy.get(); }

    public boolean hasBall() { return ballSensor.getAverageVoltage() < 1.0; }

    TalonSRX getDriveEncoderTalon() { return intake; }

    @Override
    protected void initDefaultCommand() {

    }
}

