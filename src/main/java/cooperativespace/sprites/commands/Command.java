package cooperativespace.sprites.commands;

import cooperativespace.sprites.components.ActionComponent;

public abstract class Command {
    public abstract void execute(ActionComponent actionComponent);
}
