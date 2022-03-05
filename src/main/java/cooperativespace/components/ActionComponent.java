package cooperativespace.components;

import cooperativespace.commands.Command;
import cooperativespace.sprites.GameActor;
import cooperativespace.sprites.PlayerSprite;
import cooperativespace.sprites.Sprite;

import java.util.HashSet;

public class ActionComponent {

    Sprite actor;

    private final double ACCELERATION = 1;
    private final double TURNING_SPEED = 1;

    public ActionComponent(Sprite actor) {
        this.actor = actor;
    }

    public void update(HashSet<Command> commands) {
        for (Command command : commands)
            command.execute(this);
    }

    public void accelerate() {
        actor.modifySpeed(ACCELERATION);
    }

    public void reverse() {
        actor.modifySpeed(-1*ACCELERATION);
    }

    public void rotateRight() {
        actor.modifyRotation(TURNING_SPEED);
    }

    public void rotateLeft() {
        actor.modifyRotation(-1*TURNING_SPEED);
    }

}
