package team.gif.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import team.gif.robot.subsystems.Elevator;
import team.gif.Constants;

public class MoveElevator extends Command {

    private final Elevator elevator = Elevator.getInstance();

    public MoveElevator() { requires(elevator); }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        elevator.setPercentOutput(0.2);
    }

    @Override
    protected boolean isFinished() { return false; }

    @Override
    protected void end() {

    }
}
