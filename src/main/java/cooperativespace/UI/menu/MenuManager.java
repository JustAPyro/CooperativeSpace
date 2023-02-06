package cooperativespace.UI.menu;

import cooperativespace.client.Client;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MenuManager {
    private final static Logger logger = LogManager.getLogger(Client.class);
    private final boolean verbose = true;

    private boolean isActive;
    private Canvas canvas;
    private GraphicsContext gc;

    public MenuManager(Canvas canvas, String menuLocation) {
        logger.info("..... Creating Menu Manager .....");
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();

        try (FileReader reader = new FileReader(menuLocation)) {
            logger.info("Found/opened JSON menu file");

            JSONParser jsonParser = new JSONParser();
            JSONObject menus = (JSONObject) jsonParser.parse(reader);
            logger.info("Parsed menu file");

            if (verbose) // If we're running with menus in verbose take note of what menus were loaded
                logger.info("Loaded the following menus: " + menus.keySet());

        } catch (IOException e) {
            logger.error(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }

    public void activate() {
        isActive = true;
    }

    public void deactivate() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public void draw() {
        if (!isActive) // Sanity check, this shouldn't happen but we want to know if it does
            logger.error("Manu Manager requesting to draw when deactivated.");

        gc.fillRect(0, 0, 50, 50);

    }
}
