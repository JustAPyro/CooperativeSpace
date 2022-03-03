package cooperativespace.stage;

import cooperativespace.sprites.PlayerSprite;
import cooperativespace.sprites.Sprite;

import java.util.HashSet;

public class ZoneOne implements WorldStage {

    HashSet<Sprite> players = new HashSet<>();

    @Override
    public void loadAssets() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update() {

        for (Sprite player : players) {
            player.update();
        }

    }
}
