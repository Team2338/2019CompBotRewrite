package team.gif.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import team.gif.Constants;
import team.gif.OI;
import team.gif.lib.DriveController;
import team.gif.robot.subsystems.Drivetrain;
import team.gif.robot.subsystems.Elevator;

public class DriveTeleOp extends Command {

    private final Drivetrain drivetrain = Drivetrain.getInstance();
    private final DriveController controller = new DriveController();

    public DriveTeleOp() { requires(drivetrain); }

    @Override
    protected void initialize() {

    }

    @Override
    protected void execute() {
        // Curvature
        double magnitude = -OI.getInstance().driver.getY(GenericHID.Hand.kLeft);
        double rotation = OI.getInstance().driver.getX(GenericHID.Hand.kRight);
        boolean quickTurn = false;

        double[] outputs = controller.curvatureDrive(magnitude, rotation, quickTurn);
        drivetrain.setOutputs(outputs[0], outputs[1]);

        // Tank
        /*
        double leftSpeed = -OI.getInstance().driver.getY(GenericHID.Hand.kLeft);
        double rightSpeed = OI.getInstance().driver.getX(GenericHID.Hand.kRight);

        double[] outputs = controller.tankDrive(leftSpeed, rightSpeed, true);
        drivetrain.setOutputs(outputs[0], outputs[1]);
        */

    }

    @Override
    protected boolean isFinished() { return false; }

    @Override
    protected void end() { drivetrain.setOutputs(0.0, 0.0); }
}
