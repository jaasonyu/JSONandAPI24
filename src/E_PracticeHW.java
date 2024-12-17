import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class E_PracticeHW {

    public static void main(String[] args) throws ParseException {
        E_PracticeHW pokemon = new E_PracticeHW();
    }

    public E_PracticeHW() throws ParseException {
        pull();
    }

    public void pull() throws ParseException {
        String output = "";
        String jsonString="";
        try {

            URL url = new URL("https://pokeapi.co/api/v2/pokemon/pikachu/"); /** Your API's URL goes here */
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
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
        JSONObject pikachu = (JSONObject) parser.parse(jsonString);
        System.out.println("Pikachu's JSON: " + pikachu);

        /* TODO : print out the name of each ability that Pikachu has */
        // feel free to paste this URL into your browser to explore the JSON: https://pokeapi.co/api/v2/pokemon/pikachu/
        JSONArray abilities = (JSONArray) pikachu.get("abilities"); //a for abilities
        System.out.println("ABILITIES: " + abilities);
        //print each ally on its own line (using a for loop)
        for (int i = 0; i < abilities.size(); i++){
                JSONObject ability = (JSONObject) abilities.get(i);
                JSONObject power = (JSONObject) ability.get("ability");
                String name = (String) power.get("name");
                System.out.println("NAME of ABILITY: " + name);
        }

        /* TODO : print out Pikachu's held items! */
        JSONArray held_items = (JSONArray) pikachu.get("held_items");
        for(int c = 0; c < held_items.size(); c++){
            JSONObject item = (JSONObject) held_items.get(c);
            JSONObject object = (JSONObject) item.get("item");
            String name = (String) object.get("name");
            System.out.println("NAME of HELD ITEM: " + name);
        }
    }
}