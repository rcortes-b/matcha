package me.rcortesb.browsing.controller;

import me.rcortesb.browsing.service.BrowseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/browse")
public class BrowseController {
    final private BrowseService browseService;

    public BrowseController(BrowseService browseService) {
        this.browseService = browseService;
    }


}
