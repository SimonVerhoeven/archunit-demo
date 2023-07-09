package dev.simonverhoeven.archunitdemo.slicingmodule.thirdslice;

import dev.simonverhoeven.archunitdemo.slicingmodule.firstslice.FirstService;
import org.springframework.stereotype.Service;

@Service
public class ThirdService {

    public ThirdService(FirstService firstService) {
        // Here we're getting a cycle since FirstService itself depends on ThirdService through SecondService
    }
}
