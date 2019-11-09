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
public class SearchController extends TechJobsController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("searchType", "all");
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    // After looking up the search results via the JobData class, you'll need to
    // pass them into the search.html view via the model. You'll also need to pass
    // ListController.columnChoices to the view, as the existing search handler does

    @RequestMapping(value = "results")
    public String search(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {

        // load data, if not already loaded
//        loadData();

        ArrayList<HashMap<String, String>> someJobs;

        if (searchType.equals("all")) {
            someJobs = JobData.findByValue(searchTerm);
        }
        else {
            someJobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("someJobs", someJobs);
        model.addAttribute("searchType", searchType);
        return "search";

    }
}

