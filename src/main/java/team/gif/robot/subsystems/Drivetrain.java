package team.gif.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import team.gif.Constants;
import team.gif.Robot;
import team.gif.RobotMap;
import team.gif.robot.commands.drivetrain.DriveTeleOp;

public class Drivetrain extends Subsystem {

    private static Drivetrain instance;

    private final CANSparkMax leftMaster, leftSlave, rightMaster, rightSlave;
    private final TalonSRX leftEncoderTalon;

    private Drivetrain() {
        leftMaster = new CANSparkMax(RobotMap.DRIVE_LEFT_MASTER_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        leftSlave = new CANSparkMax(RobotMap.DRIVE_LEFT_SLAVE_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightMaster = new CANSparkMax(RobotMap.DRIVE_RIGHT_MASTER_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        rightSlave = new CANSparkMax(RobotMap.DRIVER_RIGHT_SLAVE_ID, CANSparkMaxLowLevel.MotorType.kBrushless);
        configNeo(leftMaster);
        configNeo(leftSlave);
        configNeo(rightMaster);
        configNeo(rightSlave);

        leftSlave.setIdleMode(CANSparkMax.IdleMode.kCoast);
        rightSlave.setIdleMode(CANSparkMax.IdleMode.kCoast);

        leftEncoderTalon = Collector.getInstance().getDriveEncoderTalon();
        configDriveEncoder(leftEncoderTalon);
        leftEncoderTalon.setSensorPhase(!Robot.isCompBot);

        leftMaster.setInverted(false);
        rightMaster.setInverted(true);

        leftSlave.follow(leftMaster, false);
        rightSlave.follow(rightMaster, false);

    }

    public static Drivetrain getInstance() {
        if (instance == null) {
            instance = new Drivetrain();
        }
        return instance;
    }

    public void setOutputs(double left, double right) {
        leftMaster.set(left);
        rightMaster.set(right);
    }

    public void setBrakeMode(boolean on) {
        leftMaster.setIdleMode(on ? CANSparkMax.IdleMode.kBrake : CANSparkMax.IdleMode.kCoast);
        rightMaster.setIdleMode(on ? CANSparkMax.IdleMode.kBrake : CANSparkMax.IdleMode.kCoast);
    }

    /*
        public void setRampRate(double seconds) {
        leftMaster.setOpenLoopRampRate(seconds);
        rightMaster.setOpenLoopRampRate(seconds);
        leftSlave.setOpenLoopRampRate(seconds);
        rightSlave.setOpenLoopRampRate(seconds);
    }

    */

    private void configNeo(CANSparkMax spark) {
        spark.setIdleMode(CANSparkMax.IdleMode.kBrake);
        spark.enableVoltageCompensation(12.0);
        spark.setSmartCurrentLimit(80);
        spark.setOpenLoopRampRate(0.15);
    }

    private void configDriveEncoder(TalonSRX talon) {
        talon.configFactoryDefault();
        talon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 5);
        talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    @Override
    protected void initDefaultCommand() { setDefaultCommand(new DriveTeleOp()); }
}
