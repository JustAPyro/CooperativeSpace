package cooperativespace.stage;

import cooperativespace.core.Action;
import cooperativespace.sprites.PlayerSprite;
import javafx.scene.canvas.Canvas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public abstract class WorldStage {

    HashMap<String, PlayerSprite> players = new HashMap<>();

    abstract public void loadAssets();

    public void updatePlayers(ConcurrentHashMap<String, HashSet<Action>> actionMap) {
        for (String playerID : actionMap.keySet()) {
            PlayerSprite player = players.computeIfAbsent(playerID, k -> new PlayerSprite(false));
            player.update(actionMap.get(playerID));
        }
    }

    abstract public void update();

    abstract public byte[] packageState();

    abstract public void unpackState(byte[] packedState);

    abstract public void draw(Canvas canvas);

}