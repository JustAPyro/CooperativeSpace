package cooperativespace.sprites.commands.movement;

import cooperativespace.sprites.commands.Command;
import cooperativespace.sprites.components.ActionComponent;

public class AccelerateCommand extends Command {

    @Override
    public void execute(ActionComponent actionComponent) {
        actionComponent.accelerate();
    }

}
