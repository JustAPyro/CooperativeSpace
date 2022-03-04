package cooperativespace.stage;

import cooperativespace.core.Action;
import cooperativespace.sprites.PlayerSprite;
import cooperativespace.sprites.Sprite;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class ZoneOne implements WorldStage {

    HashMap<String, PlayerSprite> players = new HashMap<>();

    @Override
    public void loadAssets() {

    }

    @Override
    public void updatePlayers(ConcurrentHashMap<String, HashSet<Action>> actionMap) {
        for (String playerID : actionMap.keySet()) {
            PlayerSprite player = players.computeIfAbsent(playerID, k -> new PlayerSprite());
            player.update(actionMap.get(playerID));
        }
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public byte[] packageState() {
        int x =0; int y =0;
        for (PlayerSprite player : players.values()) {
            x = (int) player.x;
            y = (int) player.y;
        }

        byte[] packagedState = new byte[8];
        packageInsert(packagedState, 0, x);
        packageInsert(packagedState, 4, y);

        return packagedState;

    }

    public void unpackState(byte[] packedState) {
        int[] integers = new int[2];
        int x = (
                (int) (packedState[0] >> 24) +
                        (int) (packedState[1] >> 16) +
                        (int) (packedState[2] >> 8) +
                        (int) (packedState[3])
        );
        int y = (
                (int) (packedState[4] >> 24) +
                        (int) (packedState[5] >> 16) +
                        (int) (packedState[6] >> 8) +
                        (int) (packedState[7])
        );

        for (PlayerSprite player : players.values()) {
            player.x = x;
            player.y = y;
        }


        System.out.println(x + " | " + y);
    }


    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillRect(0, 0, 10, 10);
        for (PlayerSprite player : players.values()) {
            gc.fillRect(player.x,player.y,20, 20);
        }
    }

    private void packageInsert(byte[] b, int position, int toInsert) {
        for (int i = 0; i  < 4; i++) {
            b[i + position] = (byte) (toInsert >> 24-(i*8));
        }
    }


}
