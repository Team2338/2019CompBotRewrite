package team.gif;

import static java.lang.Math.*;

public abstract class Constants {

    public static class Collector {
        public static final double LEFT_BRAKE_POS = Robot.isCompBot ? 0.01: 0.22;
        public static final double LEFT_NEUTRAL_POS = Robot.isCompBot ? 0.50: 0.7;
        public static final double RIGHT_BRAKE_POS = Robot.isCompBot ? 0.615: 0.52;
        public static final double RIGHT_NEUTRAL_POS = Robot.isCompBot ? 0.125: 0.0;
    }

    public static class Drivetrain {
        public static final double INPUT_DEADBAND = 0.05;
        public static final double QUICK_STOP_THRESHOLD = 0.2;
        public static final double QUICK_STOP_ALPHA = 0.1;
    }

    public static class Elevator {
        public static final int ALLOWABLE_ERROR = 100;
        public static final int MAX_VELOCITY = 1700;
        public static final int REV_MAX_VELOCITY = 2800;
        public static final int MAX_ACCELERATION = 8000;

        public static final int MIN_POS = Robot.isCompBot ? 200 : 900;
        public static final int MAX_POS = Robot.isCompBot ? 33700 : 34400;
    }
}
