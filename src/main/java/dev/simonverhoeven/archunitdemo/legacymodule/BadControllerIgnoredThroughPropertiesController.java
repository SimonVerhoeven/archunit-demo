package dev.simonverhoeven.archunitdemo.legacymodule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/somethingBad")
public class BadControllerIgnoredThroughPropertiesController {
    @Autowired
    private LegacyService legacyService;
}
