package capturetheflag;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * A utility class for loading a Map from a JSON file.
 */
public class MapLoader {

    /**
     * Loads a Map from a JSON file.
     *
     * @param filename The name of the JSON file to load.
     * @return The Map loaded from the JSON file.
     */
    public static Map loadMapFromJson(String filename) {
        Map map = new Map();

        JSONParser parser = new JSONParser();

        try {
            String currentDir = System.getProperty("user.dir");
            String filePath = currentDir + "/" + filename;

            FileReader reader = new FileReader(filePath);

            Object obj = parser.parse(reader);
            JSONObject jsonMap = (JSONObject) obj;

            map.loadFromJsonFile(filePath);

            System.out.println("Map loaded successfully from " + filename);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return map;
    }
}
