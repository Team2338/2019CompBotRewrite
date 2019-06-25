package team.gif.robot.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import team.gif.robot.subsystems.Collector;

public class ToggleMode extends Command {

    private final Collector collector = Collector.getInstance();
    private boolean isFinished = false;

    public ToggleMode() { requires(collector); }

    @Override
    protected void initialize() {
        isFinished = false;
        collector.setHatchMode(!collector.isHatchMode());
        collector.openClamp(true);
        collector.deployHooks(false);
        System.out.println("Is Hatch Mode ? " + collector.isHatchMode());
    }

    @Override
    protected void execute() {
        if (timeSinceInitialized() > 0.4 ) {
            isFinished = true;
        } else if (timeSinceInitialized() > 0.1 ) {
            collector.engageServoBrakes(!collector.isHatchMode());
        }
    }

    @Override
    protected boolean isFinished() { return isFinished; }

    @Override
    protected void end() { collector.openClamp(false); }

}
