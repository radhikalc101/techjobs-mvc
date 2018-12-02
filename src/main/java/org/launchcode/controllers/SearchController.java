package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results", method = RequestMethod.POST)
    public String search(Model model, @RequestParam String searchType,@RequestParam String searchTerm) {
        ArrayList<HashMap<String, String>> results;
        model.addAttribute("columns", ListController.columnChoices);
        if(searchType.equals("all")){
            if(searchTerm == null || searchTerm.equals("")) {
                results = JobData.findAll();
            } else {
                results = JobData.findAllByValue(searchTerm);
            }
        } else {
            results = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("jobs", results);

        return "search";
    }

}
