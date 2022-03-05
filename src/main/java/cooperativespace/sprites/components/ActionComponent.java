package cooperativespace.sprites.components;

import cooperativespace.sprites.commands.Command;
import cooperativespace.sprites.Sprite;

import java.util.HashSet;

import static cooperativespace.utilities.UtilMath.sin;
import static cooperativespace.utilities.UtilMath.cos;

public class ActionComponent {

    Sprite actor;

    private final double ACCELERATION = 0.01;
    private final double TURNING_SPEED = 2;

    public ActionComponent(Sprite actor) {
        this.actor = actor;
    }

    public void update(HashSet<Command> commands) {
        for (Command command : commands)
            command.execute(this);
    }

    public void accelerate() {
        actor.modifyXVelocity(ACCELERATION * sin(actor.getRotation()));
        actor.modifyYVelocity(-1 * ACCELERATION * cos(actor.getRotation()));
    }

    public void reverse() {
        actor.modifyXVelocity(-1 * ACCELERATION * sin(actor.getRotation()));
        actor.modifyYVelocity(ACCELERATION * cos(actor.getRotation()));
    }

    public void rotateRight() {
        actor.modifyRotation(TURNING_SPEED);
    }

    public void rotateLeft() {
        actor.modifyRotation(-1*TURNING_SPEED);
    }

}
