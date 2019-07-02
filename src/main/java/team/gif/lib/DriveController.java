package team.gif.lib;

import team.gif.Constants;

public class DriveController {

    private double deadband = Constants.Drivetrain.INPUT_DEADBAND;
    private double quickStopThreshold = Constants.Drivetrain.QUICK_STOP_THRESHOLD;
    private double quickStopAlpha = Constants.Drivetrain.QUICK_STOP_ALPHA;
    private double quickStopAccumulator;

    public DriveController() {

    }

    // Tank drive
    public double[] tankDrive(double leftSpeed, double rightSpeed, boolean squareInputs) {
        leftSpeed = limit(leftSpeed);
        leftSpeed = applyDeadband(leftSpeed, deadband);

        rightSpeed = limit(rightSpeed);
        rightSpeed = applyDeadband(rightSpeed, deadband);

        // Square the inputs (while preserving the sign) to increase fine control
        // while permitting full power.
        if (squareInputs) {
            leftSpeed = Math.copySign(leftSpeed * leftSpeed, leftSpeed);
            rightSpeed = Math.copySign(rightSpeed * rightSpeed, rightSpeed);
        }

        return new double[] {leftSpeed, rightSpeed};
    }

    // Curvature drive
    public double[] curvatureDrive(double magnitude, double rotation, boolean quickTurn) {
        magnitude = limit(magnitude);
        magnitude = applyDeadband(magnitude, deadband);

        rotation = limit(rotation);
        rotation = applyDeadband(rotation, deadband);

        double angularPower;
        boolean overPower;

        if (quickTurn) {
            if (Math.abs(magnitude) < quickStopThreshold) {
                quickStopAccumulator = (1 - quickStopAlpha) * quickStopAccumulator
                        + quickStopAlpha * limit(rotation) * 2;
            }
            overPower = true;
            angularPower = rotation;
        } else {
            overPower = false;
            angularPower = Math.abs(magnitude) * rotation - quickStopAccumulator;

            if (quickStopAccumulator > 1) {
                quickStopAccumulator -= 1;
            } else if (quickStopAccumulator < -1) {
                quickStopAccumulator += 1;
            } else {
                quickStopAccumulator = 0.0;
            }
        }

        double leftMotorOutput = magnitude + angularPower;
        double rightMotorOutput = magnitude - angularPower;

        // If rotation is overpowered, reduce both outputs to within acceptable range
        if (overPower) {
            if (leftMotorOutput > 1.0) {
                rightMotorOutput -= leftMotorOutput - 1.0;
                leftMotorOutput = 1.0;
            } else if (rightMotorOutput > 1.0) {
                leftMotorOutput -= rightMotorOutput - 1.0;
                rightMotorOutput = 1.0;
            } else if (leftMotorOutput < -1.0) {
                rightMotorOutput -= leftMotorOutput + 1.0;
                leftMotorOutput = -1.0;
            } else if (rightMotorOutput < -1.0) {
                leftMotorOutput -= rightMotorOutput + 1.0;
                rightMotorOutput = -1.0;
            }
        }

        // Normalize the wheel speeds
        double maxMagnitude = Math.max(Math.abs(leftMotorOutput), Math.abs(rightMotorOutput));
        if (maxMagnitude > 1.0) {
            leftMotorOutput /= maxMagnitude;
            rightMotorOutput /= maxMagnitude;
        }

        return new double[] {limit(leftMotorOutput), limit(rightMotorOutput)};
    }

    private double limit(double value) {
        if (value > 1.0) {
            return 1.0;
        }
        if (value < -1.0) {
            return -1.0;
        }
        return value;
    }

    private double applyDeadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }
}
