package team.gif.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import team.gif.Constants;
import team.gif.RobotMap;

public class Elevator extends Subsystem {

    private static Elevator instance;

    private Elevator() {

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
