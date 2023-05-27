package com.example.steamstats;

import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

// Steam provides various APIs with links that differ from each other. This was the cleanest method that I could
// think of for now to deal with this. I plan on changing this in the future if I learn any better ways to handle this

public class JSONObjectFactory {

    private URL createUserSummaryURL(String steamID) throws MalformedURLException {
        return new URL(new StringBuilder("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=")
                .append(System.getenv("STEAM_API_KEY"))
                .append("&steamids=")
                .append(steamID)
                .append("&format=json")
                .toString());
    }

    private URL createGameStatsURL(String steamID) throws MalformedURLException {
        return new URL(new StringBuilder("https://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001/?key=")
                .append(System.getenv("STEAM_API_KEY"))
                .append("&steamid=")
                .append(steamID)
                .append("&format=json")
                .toString());
    }

    private String buildJsonUrl(URL url) throws IOException {
        StringBuilder jsonURL = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNextLine()) {
            jsonURL.append(scanner.nextLine());
        }
        scanner.close();
        return jsonURL.toString();
    }

    // TODO: Redo method to avoid returning null and clean up abstraction issue / Maybe split up into buildObject and getReponse
    public JSONObject buildJSONObject(String steamID, String type) throws IOException {
        switch(type.toUpperCase()) {
            case("USER"):
                return new JSONObject(buildJsonUrl(createUserSummaryURL(steamID)));
            case("GAME"):
                return new JSONObject(buildJsonUrl(createGameStatsURL(steamID)));
        }
        return null;
    }

    public JSONObject getResponse(String steamID, String type) throws IOException {
        return buildJSONObject(steamID, type).getJSONObject("response");
    }

}
