package team.gif.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import team.gif.Constants;
import team.gif.robot.subsystems.Elevator;

public class SetElevatorPosition extends Command {

    private final Elevator elevator = Elevator.getInstance();
    private double position;

    public SetElevatorPosition(double setpoint) {
        requires(elevator);
        position = setpoint;
    }

    @Override
    protected void initialize() {
        elevator.setP(Constants.Elevator.P);
        elevator.setPosition(position);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {

    }

}
