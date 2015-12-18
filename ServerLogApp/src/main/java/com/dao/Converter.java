package com.dao;

/**
 * Created by invincible_g_d on 12/18/15.
 */

import com.entity.LogEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Converter {
    private static final Logger LOGGER = LogManager.getLogger();


    public static List<String> toJSON(Map<Integer,LogEvent> logEvents) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Set<Map.Entry<Integer,LogEvent>> logSet = logEvents.entrySet();
        List<String> logsList = new ArrayList<>();
        for (Map.Entry<Integer,LogEvent> map:logSet) {
            String logEventsJson = mapper.writeValueAsString(map);
            logsList.add(logEventsJson);
        }

        LOGGER.info("json created!");
        return logsList;
    }



}