package com.example.steamstats.SteamWebAPI;

import org.json.*;

import java.io.IOException;
import java.util.HashMap;

public class SteamGameStats {
    private JSONObject response;
    private JSONArray games;

    public SteamGameStats(String steamID) throws IOException {
        response = new JSONObjectFactory().getResponse(steamID, "GAME");
        games = response.getJSONArray("games");
    }

    public HashMap<String, Double> getGameStats() {
        String game;
        double mins;
        HashMap<String, Double> gamesHashMap = new HashMap<>();
        for (int i = 0; i < games.length(); i++) {
            game = games.getJSONObject(i).getString("name");
            mins = games.getJSONObject(i).getDouble("playtime_2weeks");
            gamesHashMap.put(game, mins);
        }
        return gamesHashMap;
    }
}
