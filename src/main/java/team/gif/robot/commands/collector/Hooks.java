package team.gif.robot.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import team.gif.robot.subsystems.Collector;

public class Hooks extends Command {

    private final Collector collector = Collector.getInstance();
    private boolean isFinished = false;

    public Hooks() { requires(collector);}

    @Override
    protected void initialize() {
        if (collector.isHatchMode()) {
            collector.deployHooks(true);
        }
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() { return isFinished; }

    @Override
    protected void end() { collector.isHatchMode(); }
}
