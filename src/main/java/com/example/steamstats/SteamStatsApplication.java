package com.example.steamstats;

import com.example.steamstats.SteamWebAPI.SteamGameStats;
import com.example.steamstats.SteamWebAPI.SteamUserStats;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

// TODO: Clean up code, Current code is being rushed, planning to create a Steam Account class to hold all data
// Planning to also rework classes/methods to follow Robert C Martins Clean Code book

@SpringBootApplication
@Controller
public class SteamStatsApplication {

    @RequestMapping("/")
    @GetMapping
    String index(Model model, String steamID) throws Exception {
        return "index";
    }

    @RequestMapping("/search")
    String search() {
        return "search";
    }

    @RequestMapping("/stats/{steamID}")
    public String test(@PathVariable("steamID") String steamID, Model model) throws Exception {
        SteamUserStats test = new SteamUserStats(steamID);
        SteamGameStats t = new SteamGameStats(steamID);
        model.addAttribute("steamName", test.getSteamUserName()); // Replace this with Steam Username when added
        model.addAttribute("games", t.getGameStats());
        test.getSteamUserName();
        return "stats";
    }

    public static void main(String[] args) {
        SpringApplication.run(SteamStatsApplication.class, args);
    }

}