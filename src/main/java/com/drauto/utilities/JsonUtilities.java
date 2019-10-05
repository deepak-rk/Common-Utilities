package com.drauto.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtilities {

    private static final Logger log = LoggerFactory.getLogger(JsonUtilities.class);

    /**
     * @param jsonPath path of the jsonFile
     * @return {@link JSONObject} from the given path
     */
    public static JSONObject readJSONObject(String jsonPath) {

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = null;
        try {

            jsonObject = (JSONObject) jsonParser.parse(new FileReader(jsonPath));

        } catch (FileNotFoundException e) {
            log.error("FileNotFoundException occured", e);
        } catch (IOException e) {
            log.error("IOException occured", e);
        } catch (ParseException e) {
            log.error("ParseException occured", e);
        }
        return jsonObject;
    }

}
