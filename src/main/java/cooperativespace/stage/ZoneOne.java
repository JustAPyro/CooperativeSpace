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

    // DELETED THIS
    int x; int y;

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

        byte playerCount = (byte) players.size();
        byte[] packagedState = new byte[playerCount*6+1];

        packagedState[0] = playerCount;
        int index = 1;

        int x =0; int y =0;
        for (PlayerSprite player : players.values()) {
            byte[] packedPlayer = player.pack();
            System.arraycopy(packedPlayer, 0, packagedState, index, packedPlayer.length);
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

        byte[] byteX = new byte[4];
        byte[] byteY = new byte[4];

        /*
        System.arraycopy(packedState, 4, byteX, 0, byteX.length);
        System.arraycopy(packedState, 0, byteY, 0, byteY.length);

        int x = byteArrayToInt(byteX);
        int y = byteArrayToInt(byteY);

        this.x = x;
        this.y = y;
    */

        System.out.println(packedState[0]);
    }


    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillRect(this.x, this.y, 10, 10);

    }



    private void packageInsert(byte[] b, int position, int toInsert) {
        for (int i = 0; i  < 4; i++) {
            b[i + position] = (byte) (toInsert >> 24-(i*8));
        }
    }


}
