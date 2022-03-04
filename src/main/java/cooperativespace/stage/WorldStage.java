package cooperativespace.stage;

import cooperativespace.core.Action;
import javafx.scene.canvas.Canvas;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public interface WorldStage {

    public void loadAssets();

    public void updatePlayers(ConcurrentHashMap<String, HashSet<Action>> actionMap);

    public void update();

    public byte[] packageState();

    public void unpackState(byte[] packedState);

    public void draw(Canvas canvas);

}