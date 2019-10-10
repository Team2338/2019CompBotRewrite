package team.gif.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import team.gif.Constants;
import team.gif.robot.subsystems.Elevator;

public class SetElevatorPosition extends Command {

    private final Elevator elevator = Elevator.getInstance();
    private double position;

    public SetElevatorPosition(double position) {
        if (position > Constants.Elevator.MAX_POS) {
            position = Constants.Elevator.MAX_POS;
        }
        if (position < Constants.Elevator.MIN_POS) {
            position = Constants.Elevator.MIN_POS;
        }
        this.position = position;
        requires(elevator);
    }

    @Override
    protected void initialize() {
        if (position > elevator.getPosition()) {
            elevator.setP(Constants.Elevator.P);
            elevator.setCruiseVelocity(Constants.Elevator.MAX_VELOCITY);
        }
    }

    @Override
    protected void execute() {
        if (!elevator.getFwdLimit() && elevator.getClosedLoopError() < 0) {
            elevator.setCruiseVelocity(400);
        } else {
            elevator.setCruiseVelocity(Constants.Elevator.MAX_VELOCITY);
        }

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {

    }

}
