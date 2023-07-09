package dev.simonverhoeven.archunitdemo.slicingmodule.firstslice;

import dev.simonverhoeven.archunitdemo.slicingmodule.secondslice.SecondService;
import org.springframework.stereotype.Service;

@Service
public class FirstService {

    public FirstService(SecondService secondService) {

    }
}
