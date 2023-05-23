package com.example.steamstats;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.net.URL;
import org.json.*;

// TODO:
//  -Clean / add / remove last few methods
//  -find shorter variable names for last few methods
//  -change and add exception handling
//  -avoid returning null for jsonObjectBuilder method or add null exception handling
//  -reformat how the lastFewGames string is built
//  -add more comments and implement steamAccountName
//  -add steamAccount (abstract class) and an interface for API methods
//  -add a database support previous months

public class SteamJsonApiClient {
    private String steamAccountName;
    private final String steamID;

    public SteamJsonApiClient(String steamID) {
        this.steamID = steamID;
    }

    private URL urlBuilder() throws MalformedURLException {
        String urlStringBuilder = new StringBuilder()
                .append("https://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001/?key=")
                .append(System.getenv("STEAM_API_KEY"))
                .append("&steamid=")
                .append(steamID)
                .append("&format=json")
                .toString();
        return new URL(urlStringBuilder);
    }

    private String jsonUrlBuilder(URL url) throws IOException {
        StringBuilder jsonURL = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNextLine()) {
            jsonURL.append(scanner.nextLine());
        }
        scanner.close();
        return jsonURL.toString();
    }

    private JSONObject jsonObjectBuilder() {
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(jsonUrlBuilder(urlBuilder()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return jsonObj;
    }

    private JSONArray getPlayedWithinLastTwoWeeksJsonArray(JSONObject object) {
        return object
                .getJSONObject("response")
                .getJSONArray("games");
    }

    public String playedWithinLastTwoWeeksStringBuilder() {
        JSONObject apiCallResults = jsonObjectBuilder();
        StringBuilder playedWithinLastTwoWeeks = new StringBuilder();
        String game;
        for (int i = 0; i < getPlayedWithinLastTwoWeeksJsonArray(apiCallResults).length(); i++) {
            game = getPlayedWithinLastTwoWeeksJsonArray(apiCallResults).getJSONObject(i).getString("name");
            playedWithinLastTwoWeeks
                    .append(game)
                    .append(", ");
        }
        return playedWithinLastTwoWeeks.toString();
    }
}