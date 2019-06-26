package team.gif.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
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

        leftEncoderTalon = Collector.getInstance().getDriveEncoderTalon();

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

    @Override
    protected void initDefaultCommand() { setDefaultCommand(new DriveTeleOp()); }
}
