package com.example.steamstats.SteamWebAPI;

import org.json.*;
import java.io.IOException;

public class SteamUserStats {
    private JSONObject response;

    // Cleanup
    public SteamUserStats(String steamID) throws IOException {
        response = new JSONObjectFactory().getResponse(steamID, "USER");
    }

    public String getSteamUserName() throws IOException {
        return response.getJSONArray("players").getJSONObject(0).getString("personaname");
    }
}
