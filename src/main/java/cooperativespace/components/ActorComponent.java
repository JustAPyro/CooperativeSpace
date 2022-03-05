package cooperativespace.components;

import cooperativespace.commands.Command;
import cooperativespace.commands.movement.AccelerateCommand;
import cooperativespace.commands.movement.ReverseCommand;
import cooperativespace.commands.movement.RotateLeftCommand;
import cooperativespace.commands.movement.RotateRightCommand;
import cooperativespace.core.Action;
import cooperativespace.sprites.PlayerSprite;

import java.util.HashSet;

public class ActorComponent {

    HashSet<Command> commands = new HashSet<>();
    AccelerateCommand accelerate = new AccelerateCommand();
    ReverseCommand reverse = new ReverseCommand();
    RotateRightCommand right = new RotateRightCommand();
    RotateLeftCommand left = new RotateLeftCommand();

    public void update(PlayerSprite player, HashSet<Action> actions) {

        // Start by clearing the command set
        commands.clear();

        // Accelerating
        if (actions.contains(Action.ACCELERATE) && !actions.contains(Action.REVERSE))
            commands.add(accelerate);

        // Decelerating/reversing
        if (actions.contains(Action.REVERSE) && !actions.contains(Action.ACCELERATE))
            commands.add(reverse);

        // Turning right
        if (actions.contains(Action.ROTATE_RIGHT) && !actions.contains(Action.ROTATE_LEFT))
            commands.add(right);

        // Turning left
        if (actions.contains(Action.ROTATE_LEFT) && !actions.contains(Action.ROTATE_RIGHT))
            commands.add(left);

        // Execute all commands added
        for (Command command : commands)
            command.execute(player);
    }
}
