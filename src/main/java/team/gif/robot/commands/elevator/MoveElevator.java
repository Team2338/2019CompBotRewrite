package team.gif.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import team.gif.robot.subsystems.Elevator;
import team.gif.Constants;

public class MoveElevator extends Command {

    private final Elevator elevator = Elevator.getInstance();

    @Override
    protected void initialize() {
        elevator.setCruiseVelocity(400);
    }

    @Override
    protected void execute() {
        elevator.setPercentOutput(0.3);
    }

    @Override
    protected boolean isFinished() {
        return elevator.isFinished();
    }

    @Override
    protected void end() {

    }
}
