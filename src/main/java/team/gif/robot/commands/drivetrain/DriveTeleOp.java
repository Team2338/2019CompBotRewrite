package team.gif.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import team.gif.Constants;
import team.gif.OI;
import team.gif.robot.subsystems.Drivetrain;
import team.gif.robot.subsystems.Elevator;

public class DriveTeleOp extends Command {

    private final Drivetrain drivetrain = Drivetrain.getInstance();
   // private final Elevator elevator = Elevator.getInstance();

    public DriveTeleOp() { requires(drivetrain); }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        // Actual command and drive type goes here
    }

    @Override
    protected boolean isFinished() { return false; }

    @Override
    protected void end() { drivetrain.setOutputs(0.0, 0.0); }
}
