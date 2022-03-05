package cooperativespace.commands.movement;

import cooperativespace.commands.Command;
import cooperativespace.sprites.GameActor;

public class RotateRightCommand extends Command {

    @Override
    public void execute(GameActor actor) {
        actor.rotateRight();
    }

}
