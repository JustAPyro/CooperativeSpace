package cooperativespace.commands.movement;

import cooperativespace.commands.Command;
import cooperativespace.components.ActionComponent;
import cooperativespace.sprites.GameActor;

public class RotateRightCommand extends Command {

    @Override
    public void execute(ActionComponent actionComponent) {
        actionComponent.rotateRight();
    }

}
