package cooperativespace.components;

import cooperativespace.core.Action;
import cooperativespace.sprites.PlayerSprite;

import java.util.HashSet;

public class ActorComponent {
    public void update(PlayerSprite player, HashSet<Action> actions) {
        if (actions.contains(Action.ACCELERATE) ^ actions.contains(Action.REVERSE)) {
            if (actions.contains(Action.ACCELERATE))
                player.x = player.x - 1;
            else
                player.x = player.x + 1;
        }
        if (actions.contains(Action.ROTATE_LEFT) ^ actions.contains(Action.ROTATE_RIGHT)) {
            if (actions.contains(Action.ROTATE_RIGHT))
                player.y = player.y + 1;
            else
                player.y = player.y - 1;
        }
    }
}
