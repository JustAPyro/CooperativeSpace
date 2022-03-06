package cooperativespace.sprites.components;

import cooperativespace.sprites.commands.Command;
import cooperativespace.sprites.commands.movement.AccelerateCommand;
import cooperativespace.sprites.commands.movement.ReverseCommand;
import cooperativespace.sprites.commands.movement.RotateLeftCommand;
import cooperativespace.sprites.commands.movement.RotateRightCommand;
import cooperativespace.core.Action;
import cooperativespace.sprites.PlayerSprite;

import java.util.HashSet;

public class InputComponent {

    HashSet<Command> commands = new HashSet<>();
    AccelerateCommand accelerate = new AccelerateCommand();
    ReverseCommand reverse = new ReverseCommand();
    RotateRightCommand right = new RotateRightCommand();
    RotateLeftCommand left = new RotateLeftCommand();

    public HashSet<Command> update(PlayerSprite player, HashSet<Action> actions) {

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

        if (actions.contains(Action.ABILITY_ONE))
            System.out.println("Fire");

        if (actions.contains(Action.ABILITY_TWO))
            System.out.println("Shields!");

        // Return the commands
        return commands;

    }
}
