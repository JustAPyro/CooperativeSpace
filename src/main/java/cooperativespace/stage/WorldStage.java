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

    abstract public void draw(Canvas canvas);

    public byte[] packageState() {

        byte playerCount = (byte) players.values().size();
        byte[] packagedState = new byte[(playerCount*6)+1+5];

        packagedState[0] = playerCount;
        int index = 0;

        int x =0; int y =0;
        for (PlayerSprite player : players.values()) {
            byte[] packedPlayer = player.pack();
            System.arraycopy(packedPlayer, 0, packagedState, 1+index*6, packedPlayer.length);
            index++;
        }

        /*
        byte[] byteX = intToByteArray(x);
        byte[] byteY = intToByteArray(y);

        System.arraycopy(byteX, 0, packagedState, 0, byteX.length);
        System.arraycopy(byteY, 0, packagedState, 4, byteY.length);
*/

        return packagedState;

    }

    public void unpackState(byte[] packedState) {

        int playerCount = packedState[0];


        for (int i = 0; i < playerCount; i++) {

            byte[] playerPacket = new byte[6];
            System.arraycopy(packedState, 1+i*6, playerPacket, 0, 6);

            PlayerSprite player = players.computeIfAbsent(String.valueOf(i), k -> new PlayerSprite(true));
            player.unpack(playerPacket);
        }

    }

}