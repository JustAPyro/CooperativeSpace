package cooperativespace.hub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Hub {

    // This logger is used to print/save messages depending on the settings
    private static final Logger logger = LogManager.getLogger(Hub.class);

    private String name;

    public static void main(String[] args) {
        int defaultPort = 4444;
        String defaultHub = "C:\\Users\\Luke\\Programming\\Java\\CooperativeSpace\\hub";
        new Hub(defaultHub, defaultPort);
    }

    public Hub(String directory, int port) {

        // This block attempts to load data about this hub from the directory
        logger.info("Attempting to load hub at \"" + directory + "\"");
        try (FileReader fr = new FileReader(directory + "/state.json")) {
            JSONObject jsonState = (JSONObject) new JSONParser().parse(fr);
            name = (String) jsonState.get("name");
        } catch (FileNotFoundException error) {
            logger.error("Could not find save-state at " + directory + "/state.json\nException: " + error + "\nTerminating...");
            System.exit(-1);
        } catch (ParseException error) {
            logger.error("Could not parse state.json\n"+error+"\nERROR CRITICAL: Terminating...");
            System.exit(-1);
        } catch (IOException error) {
            logger.error("I/O Exception while loading hub\n" + error);
            System.exit(-1);
        }


        logger.info("Hub " + name + " loaded, attempting to start on port " + port);

        // Try to open a server on the port number
        try (ServerSocket server = new ServerSocket(port)) {

            logger.info("Hub started, waiting for connections...");
            Socket clientSocket = server.accept();
            logger.info("boom");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }



}
