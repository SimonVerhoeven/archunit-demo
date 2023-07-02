package dev.simonverhoeven.archunitdemo.legacymodule;

import org.springframework.stereotype.Service;

import java.util.Vector;

@Service
public class LegacyService {
    private Vector<String> data = new Vector<>();
//    private Vector<String> dataNew = new Vector<>();

    public Vector<String> getData() {
        return data;
    }
}
