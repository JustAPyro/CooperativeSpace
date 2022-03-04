package cooperativespace.stage;

import cooperativespace.core.Action;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public interface WorldStage {

    public void loadAssets();

    public void updatePlayers(ConcurrentHashMap<String, HashSet<Action>> actionMap);

    public void update();

    public byte[] packageState();

}