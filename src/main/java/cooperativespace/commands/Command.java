package cooperativespace.commands;

import cooperativespace.sprites.GameActor;

public abstract class Command {
    public abstract void execute(GameActor actor);
}
