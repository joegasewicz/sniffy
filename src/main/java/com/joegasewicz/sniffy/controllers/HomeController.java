package com.joegasewicz.sniffy.controllers;

import com.joegasewicz.sniffy.entities.ApplicationEntity;
import com.joegasewicz.sniffy.services.ApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private ApplicationService applicationService;

    public HomeController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<ApplicationEntity> applicationEntities = applicationService.getAll();
        model.addAttribute("applications", applicationEntities);
        return "index";
    }
}
