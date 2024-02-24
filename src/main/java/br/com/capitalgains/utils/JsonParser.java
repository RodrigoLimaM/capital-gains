package br.com.capitalgains.utils;

import java.io.IOException;
import java.util.List;
import br.com.capitalgains.domain.OperationInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    private JsonParser() {

    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static List<OperationInfo> parseInput(String input) throws IOException {
        return OBJECT_MAPPER.readValue(input, new TypeReference<>() {
        });
    }

    public static String parseOutput(Object output) throws IOException {
        return OBJECT_MAPPER.writeValueAsString(output);
    }
}
