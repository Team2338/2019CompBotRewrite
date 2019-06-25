package team.gif.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import team.gif.Constants;
import team.gif.Robot;
import team.gif.RobotMap;

public class Collector {

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
}

