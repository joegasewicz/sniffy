package com.joegasewicz.sniffy.controllers;

import com.joegasewicz.sniffy.entities.ApplicationEntity;
import com.joegasewicz.sniffy.services.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
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
}
