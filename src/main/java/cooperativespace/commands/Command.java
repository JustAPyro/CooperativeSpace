package cooperativespace.commands;

import cooperativespace.components.ActionComponent;
import cooperativespace.sprites.GameActor;

public abstract class Command {
    public abstract void execute(ActionComponent actionComponent);
}
