package team.gif.robot.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import team.gif.robot.subsystems.Collector;

public class Eject extends Command {

    private final Collector collector = Collector.getInstance();

    public Eject() { requires(collector); }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        if (collector.isHatchMode()) {
            collector.deployHooks(false);
        } else {
            collector.setIntake(0.0);
        }
    }

    @Override
    protected boolean isFinished() { return false; }

    @Override
    protected void end() { collector.setIntake(0.0); }
}

