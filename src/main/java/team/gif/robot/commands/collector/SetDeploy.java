package team.gif.robot.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import team.gif.robot.subsystems.Collector;

public class SetDeploy extends Command {

    private final Collector collector = Collector.getInstance();
    private final boolean out;

    public SetDeploy(boolean out) {
        this.out = out;
        requires(collector);
    }

    @Override
    protected void initialize() { collector.deployCollector(out); }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() { return isFinished(); }

    @Override
    protected void end() { collector.openClamp(false); }
}
