import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0 (from about 37 seconds)

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class C_ReadSWAPI {

    public static void main(String args[]) throws ParseException {
        C_ReadSWAPI reader = new C_ReadSWAPI();
    }

    public C_ReadSWAPI() throws ParseException {
        pull();
    }

    public void pull() throws ParseException {
        String output = "";
        String jsonString="";
        try {

            URL url = new URL("https://swapi.dev/api/people/4/"); /** Your API's URL goes here */
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.print("OUTPUT from Server: ");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                jsonString += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // turn your string into a JSON object using a parser
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonString);
        System.out.println("JSON: " + json);

        // get a single value out of the json
        String height = (String) json.get("height");
        System.out.println("HEIGHT: " + height);

        // get a json array out of the json
        JSONArray filmsArray = (JSONArray) json.get("films");
        int n = filmsArray.size();
        System.out.println("FILMS: ");
        for (int i = 0; i < n; i++) {
            String film = (String) filmsArray.get(i);
            System.out.println(film);
        }

    }

}

