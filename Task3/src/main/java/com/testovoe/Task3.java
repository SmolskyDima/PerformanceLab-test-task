package com.testovoe;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Task3 {
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.out.println("Usage: java Task3 <value.json> <test.json> <report.json>");
            return;
        }

        String valueFilePath = args[0];
        String testFilePath = args[1];
        String reportFilePath = args[2];

        ObjectMapper objectMapper = new ObjectMapper();

        Map<Integer, String> valueMap = new HashMap<>();
        JsonNode values = objectMapper.readTree(new File(valueFilePath));
        for (JsonNode valueNode : values.get("values")) {
            int id = valueNode.get("id").asInt();
            String value = valueNode.get("value").asText();
            valueMap.put(id, value);
        }

        JsonNode testStructure = objectMapper.readTree(new File(testFilePath));

        fillValues(testStructure, valueMap);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportFilePath), testStructure);
    }

    private static void fillValues(JsonNode node, Map<Integer, String> valueMap) {
        if (node.isArray()) {
            for (JsonNode testNode : node) {
                int id = testNode.get("id").asInt();
                if (valueMap.containsKey(id)) {
                    ((ObjectNode) testNode).put("value", valueMap.get(id));
                }
                if (testNode.has("values")) {
                    fillValues(testNode.get("values"), valueMap);
                }
            }
        } else if (node.isObject()) {
            for (JsonNode testNode : node.get("values")) {
                int id = testNode.get("id").asInt();
                if (valueMap.containsKey(id)) {
                    ((ObjectNode) testNode).put("value", valueMap.get(id));
                }
                if (testNode.has("values")) {
                    fillValues(testNode.get("values"), valueMap);
                }
            }
        }
    }
}


