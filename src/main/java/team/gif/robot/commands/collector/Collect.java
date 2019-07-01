package team.gif.robot.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import team.gif.robot.subsystems.Collector;

public class Collect extends Command {

    private final Collector collector = Collector.getInstance();

    public Collect() { requires(collector); }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        collector.setIntake(0.5);
    }

    @Override
    protected boolean isFinished() { return false; }

    @Override
    protected void end() { collector.setIntake(0.0);}
}
