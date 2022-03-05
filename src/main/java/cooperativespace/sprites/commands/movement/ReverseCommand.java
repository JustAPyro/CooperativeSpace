package cooperativespace.sprites.commands.movement;

import cooperativespace.sprites.commands.Command;
import cooperativespace.sprites.components.ActionComponent;

public class ReverseCommand extends Command {

    @Override
    public void execute(ActionComponent actionComponent) {
        actionComponent.reverse();
    }

}
