package com.testovoe;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Task3 {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Usage: java TestReportGenerator <value.json> <test.json> <report.json>");
            return;
        }

        String valueFilePath = args[0];
        String testFilePath = args[1];
        String reportFilePath = args[2];

        ObjectMapper objectMapper = new ObjectMapper();

        // Read value.json and create a map of id to value
        Map<Integer, String> valueMap = new HashMap<>();
        JsonNode values = objectMapper.readTree(new File(valueFilePath));
        for (JsonNode valueNode : values.get("values")) {
            int id = valueNode.get("id").asInt();
            String value = valueNode.get("value").asText();
            valueMap.put(id, value);
        }
    }
}


