package eccs.com.core.middleware;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JsonParserMiddleware {

    private final ObjectMapper objectMapper;

    public Object parseField(List<Map<String, Object>> rows, String field) {
        if (rows.isEmpty()) return rows;
        try {
            String json = (String) rows.get(0).get(field);
            return objectMapper.readValue(json, Object.class);
        } catch (Exception e) {
            return rows;
        }
    }
}
