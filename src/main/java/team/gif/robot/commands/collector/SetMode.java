package team.gif.robot.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import team.gif.robot.subsystems.Collector;

public class SetMode extends Command {

    private final Collector collector = Collector.getInstance();
    private boolean isFinished = false;
    private boolean hatchMode;

    public SetMode(boolean hatchMode) {
        this.hatchMode = hatchMode;
        requires(collector);
    }

    @Override
    protected void initialize() {
        isFinished = false;
        collector.setHatchMode(hatchMode);
        collector.openClamp(true);
        collector.deployHooks(false);
    }

    @Override
    protected void execute() {
        if (timeSinceInitialized() > 0.4 ) {
            isFinished = true;
        } else if (timeSinceInitialized() > 0.1 ) {
            collector.engageServoBrakes(!hatchMode);
        }
    }

    @Override
    protected boolean isFinished() { return isFinished; }

    @Override
    protected void end() {collector.openClamp(false); }
}
