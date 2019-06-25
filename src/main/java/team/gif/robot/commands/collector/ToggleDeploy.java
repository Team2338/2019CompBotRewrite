package team.gif.robot.commands.collector;

import edu.wpi.first.wpilibj.command.Command;
import team.gif.robot.subsystems.Collector;

public class ToggleDeploy extends Command {

    private final Collector collector = Collector.getInstance();

    public ToggleDeploy() { requires(collector); }

    @Override
    protected void initialize() { collector.deployCollector(!collector.isDeployed()); }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() { return true; }

    @Override
    protected void end() {

    }

}


