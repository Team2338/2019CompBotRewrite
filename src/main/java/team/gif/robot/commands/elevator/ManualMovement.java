package team.gif.robot.commands.elevator;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import team.gif.Constants;
import team.gif.OI;
import team.gif.robot.subsystems.Elevator;

public class ManualMovement extends Command {

    private final Elevator elevator = Elevator.getInstance();
    private double position;

    public ManualMovement(double position) {
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

    }

    @Override
    protected void execute() {
        elevator.manualMove(-OI.getInstance().aux.getY(GenericHID.Hand.kLeft));
        elevator.setPosition(position);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {

    }
}
