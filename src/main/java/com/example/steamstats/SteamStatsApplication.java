package com.example.steamstats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
// TODO: fix whatever needs to be fixed and add comments

@SpringBootApplication
@Controller
public class SteamStatsApplication {

    @RequestMapping("/")
    @GetMapping
    String index(Model model, String steamID) throws Exception {
        return "index";
    }

    @RequestMapping("/stats/{steamID}")
    public String test(@PathVariable("steamID") String steamID, Model model) throws Exception {
        SteamJsonApiClient test = new SteamJsonApiClient(steamID);
        model.addAttribute("steamID", steamID); // Replace this with Steam Username when added
        model.addAttribute("games", test.playedWithinLastTwoWeeksStringBuilder());
        return "stats";
    }

    public static void main(String[] args) {
        SpringApplication.run(SteamStatsApplication.class, args);
    }

}
