package team.gif.robot.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import team.gif.robot.subsystems.Collector;

public class SetOpen extends Command {

    private final Collector collector = Collector.getInstance();
    private boolean open;

    public SetOpen(boolean open) {
        this.open = open;
        requires(collector);
    }

    @Override
    protected void initialize() {
        if (collector.isHatchMode()) {
            collector.openClamp(false);
        } else {
            collector.openClamp(open);
        }
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() { return true; }

    @Override
    protected void end() {

    }
}
