package eccs.com.modules.controldashboard.sidebar.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import eccs.com.modules.controldashboard.sidebar.query.SidebarQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SidebarServiceImpl implements SidebarService {

    private final SidebarQuery sidebarQuery;
    private final ObjectMapper objectMapper;

    @Override
    public Object getMenu(Integer id) {
        List<Map<String, Object>> rows = sidebarQuery.findMenuByIdEmpleado(id);
        if (rows.isEmpty()) return rows;
        try {
            String json = (String) rows.get(0).get("app_menu");
            return objectMapper.readValue(json, Object.class);
        } catch (Exception e) {
            return rows;
        }
    }
}
