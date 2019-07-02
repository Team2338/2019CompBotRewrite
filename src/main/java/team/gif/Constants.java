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
}
