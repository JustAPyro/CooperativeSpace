package cooperativespace.stage;

import cooperativespace.UI.HUD;
import cooperativespace.core.Action;
import cooperativespace.sprites.PlayerSprite;
import cooperativespace.sprites.SpaceRock;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents the first zone
 */
public class ZoneOne implements WorldStage {

    Image background;

    HashMap<String, PlayerSprite> players = new HashMap<>();


    @Override
    public void loadAssets() {

        System.out.println(new File(".").getAbsolutePath());
        background = new Image("file:src/main/resources/images/zone_one.png",
                512, 512, true, true);

    }

    @Override
    public void updatePlayers(ConcurrentHashMap<String, HashSet<Action>> actionMap) {
        for (String playerID : actionMap.keySet()) {
            PlayerSprite player = players.computeIfAbsent(playerID, k -> new PlayerSprite(false));
            player.update(actionMap.get(playerID));
        }
    }

    @Override
    public void update() {
        // Update zone
    }

    @Override
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


    public void draw(Canvas canvas) {

        canvas.getGraphicsContext2D().drawImage(background, 0, 0);
        canvas.getGraphicsContext2D().drawImage(background, 512, 0);
        canvas.getGraphicsContext2D().drawImage(background, 0, 512);
        canvas.getGraphicsContext2D().drawImage(background, 512, 512);

        for (PlayerSprite player : players.values())
            player.draw(canvas);

        HUD.draw(canvas);

    }



    private void packageInsert(byte[] b, int position, int toInsert) {
        for (int i = 0; i  < 4; i++) {
            b[i + position] = (byte) (toInsert >> 24-(i*8));
        }
    }


}
