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

    /**
     * Usar cuando PostgreSQL retorna el resultado dentro de un campo
     * cuyo nombre es igual al de la función, por ejemplo:
     *
     * "response": [{ "fn_get_data_empresa": "{...json...}" }]
     *
     * Toma el primer (y único) campo del primer row y lo parsea a Object.
     */
    public Object parseFunction(List<Map<String, Object>> rows) {
        if (rows.isEmpty() || rows.get(0) == null) return null;
        try {
            Object value = rows.get(0).values().iterator().next();
            if (value == null) return null;
            return objectMapper.readValue((String) value, Object.class);
        } catch (Exception e) {
            return rows;
        }
    }
}
