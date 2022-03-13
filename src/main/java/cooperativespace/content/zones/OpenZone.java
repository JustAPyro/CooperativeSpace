package cooperativespace.content.zones;

import cooperativespace.content.planets.Planet;
import cooperativespace.stage.WorldStage;


public class OpenZone extends WorldStage {

    public OpenZone(boolean isReal) {
        setBackground(isReal, "file:src/main/resources/images/openworld.png");

        /* Spawn the home planet */
        spawnPlanet(new Planet(
            isReal,
            "file:src/main/resources/images/planets/planet_3.png",
            300, 300,
            128
        ));
    }

}

