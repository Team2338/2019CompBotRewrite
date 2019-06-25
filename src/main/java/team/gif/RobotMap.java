package team.gif;

public abstract class RobotMap {

    // OI - Controllers (USB)
    public static final int DRIVER_CONTROLLER_ID = 0;
    public static final int AUX_CONTROLLER_ID = 1;

    // Spark MAXs (CAN)
    public static final int DRIVE_LEFT_MASTER_ID = 1;
    public static final int DRIVE_LEFT_SLAVE_ID = 2;
    public static final int DRIVE_RIGHT_MASTER_ID = 3;
    public static final int DRIVER_RIGHT_SLAVE_ID = 4;

    // Talon SRXs (CAN)
    public static final int COLLECTOR_INTAKE_ID = 0;
    public static final int ELEVATOR_LIFT_ID = 1;

    // Servos (PWM)
    public static final int COLLECTOR_LEFT_SERVO_ID = 0;
    public static final int COLLECTOR_RIGHT_SERVO_ID = 1;

    // Solenoids (PCM)
    public static final int COLLECTOR_DEPLOY_ID = 1;
    public static final int COLLECTOR_CLAMP_ID = 2;
    public static final int COLLECTOR_HOOKS_FWD_ID = 0;
    public static final int COLLECTOR_HOOKS_REV_ID = 3;

    // Sensors
    public static final int PIGEON_ID = 0; // (CAN)
    public static final int COLLECTOR_BALL_SENSOR_ID = 0; // (Analog Input)
}
