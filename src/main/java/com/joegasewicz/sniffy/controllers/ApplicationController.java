package com.joegasewicz.sniffy.controllers;

import com.joegasewicz.sniffy.components.Requester;
import com.joegasewicz.sniffy.entities.ApplicationEntity;
import com.joegasewicz.sniffy.services.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ApplicationController {

    private final ApplicationService applicationService;
    private final Requester requester;

    public ApplicationController(ApplicationService applicationService, Requester requester) {
        this.applicationService = applicationService;
        this.requester = requester;
    }

    @GetMapping("/applications/new")
    public String applicationsNew(@RequestParam(value = "app_created", required = false) String appCreated, Model model) {
        if (appCreated != null && appCreated.equals("true")) {
            model.addAttribute("appCreated", true);
        }
       return  "application-new";
    }

    @PostMapping("/applications/new")
    public String applicationsCreate(@ModelAttribute ApplicationEntity applicationEntity) {
        applicationService.createApplication(applicationEntity);
        return "redirect:/applications/new?app_created=true";
    }

    @PostMapping("/applications/update-poll-duration/{appId}")
    public String postUpdatePolling(@PathVariable long appId, @ModelAttribute ApplicationEntity applicationEntity) {
        applicationService.updatePollStatus(appId, applicationEntity.getPollRate());
        return "redirect:/applications/" + appId;
    }

    @PostMapping("/applications/start-polling/{appId}")
    public String postStartPolling(@PathVariable long appId) {
        ApplicationEntity applicationEntity = applicationService.get(appId);
        requester.get(appId, applicationEntity.getUrl(), applicationEntity.getPollRate());
        return "redirect:/applications/" + appId;
    }

    @GetMapping("/applications/{appId}")
    public String getApp(@PathVariable long appId, Model model) {
        ApplicationEntity app = applicationService.get(appId);
        model.addAttribute("app", app);
        if (app.getPollStatus() != null && app.getPollStatus().equalsIgnoreCase("running")) {
            model.addAttribute("pollStatusRunning", true);
        }
        if(app.getStatus().equalsIgnoreCase("running")) {
            model.addAttribute("statusRunning", true);
        }
        if (app.getPollStatus() != null) {
            switch (app.getPollRate()) {
                case 0 -> model.addAttribute("pollRateTime", "-");
                case 1 -> model.addAttribute("pollRateTime", "Every 60 seconds");
                case 60 -> model.addAttribute("pollRateTime", "Once per hour");
                case 24 * 60 -> model.addAttribute("pollRateTime", "Once every 24 hours");
            }
        }
        return "applications";
    }
}
