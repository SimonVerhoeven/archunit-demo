package dev.simonverhoeven.archunitdemo.slicingmodule.secondslice;

import dev.simonverhoeven.archunitdemo.slicingmodule.thirdslice.ThirdService;
import org.springframework.stereotype.Service;

@Service
public class SecondService {

    public SecondService(ThirdService thirdService) {

    }
}
