package cooperativespace.components;

import cooperativespace.commands.Command;
import cooperativespace.sprites.GameActor;
import cooperativespace.sprites.PlayerSprite;
import cooperativespace.sprites.Sprite;

import java.util.HashSet;

public class ActionComponent {

    Sprite actor;

    double acceleration;
    double turningSpeed;

    public ActionComponent(Sprite actor) {
        this.actor = actor;
    }

    public void update(HashSet<Command> commands) {
        for (Command command : commands)
            command.execute(this);
    }

    public void accelerate() {
        actor.modifySpeed(acceleration);
    }

    public void reverse() {
        actor.modifySpeed(-1*acceleration);
    }

    public void rotateRight() {
        actor.modifyRotation(turningSpeed);
    }

    public void rotateLeft() {
        actor.modifyRotation(-1*turningSpeed);
    }

}
